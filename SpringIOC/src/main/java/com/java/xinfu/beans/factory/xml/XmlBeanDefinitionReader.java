package com.java.xinfu.beans.factory.xml;

import com.java.xinfu.beans.factory.config.BeanDefinitionRegistry;
import com.java.xinfu.beans.factory.config.BeanDefinition;
import com.java.xinfu.beans.factory.support.AbstractBeanDefinitionReader;
import com.java.xinfu.core.io.Resource;
import com.java.xinfu.exception.BeanDefinitionStoreException;
import com.java.xinfu.util.Assert;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.io.SAXReader;

/**
 * @author 掘金
 * @date 2018/11/14
 * @desc
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {
    protected final Log logger = LogFactory.getLog(this.getClass());

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry){
        super(registry);
    }

    @Override
    public int loadBeanDefinitions(Resource resource) throws BeanDefinitionStoreException {
        Assert.notNull(resource,"EncodedResource must not be null");
        //此处应该被日志记录
        logger.info("Loading XML bean definitions from " + resource.getDescription());
        //如果存入set成功就执行doLoadBeanDefinitions(Resource iotest)：此处与spring的操作不同，做了简化，直接进入解析
        return doLoadBeanDefinitions(resource);
    }

    private int doLoadBeanDefinitions(Resource resource) throws BeanDefinitionStoreException{
        try {
            //Spring使用了DocumentLoader得到Document对象，本项目此处进行简化（运用了DOM4J进行解析）
            Document doc = this.doLoadDocument(resource);
            //进行注册
            return this.registerBeanDefinitions(doc, resource);
        } catch (BeanDefinitionStoreException e) {
            throw e;
        } catch (Throwable e2){
            throw new BeanDefinitionStoreException(resource.getDescription(),"Unexpected exception parsing XML document from" +resource,e2);
        }
    }

    /**
     * 根据Resource载入Document对象
     */
    private Document doLoadDocument(Resource resource) throws Exception{
        //创建SAXReader对象
        SAXReader reader = new SAXReader();
        return reader.read(resource.getFile());
     }

    /**
     * 根据 Resource向BeanDefinitionRegistry注册所有解出来的BeanDefinition
     * @param doc
     * @param resource
     * @return
     */
    private int registerBeanDefinitions(Document doc, Resource resource){
        //获得注册表中注册的Bean数量
        int countBefore = this.getRegistry().getBeanDefinitionCount();
        try {
            //此处进行简化，省去了BeanDefinitionDocumentReader接口及其默认实现类，直接通过XmlParser的静态方法进行解析
            Map<String, BeanDefinition> beanDefinitons = XMLParser.parser(doc,resource);
            //这里要将BeanDefinition注册到beanDefinitionRegistry中
            for(Map.Entry<String,BeanDefinition> entry : beanDefinitons.entrySet()){
                this.getRegistry().registerBeanDefiniton(entry.getKey(),entry.getValue());
            }
        } catch (BeanDefinitionStoreException e) {
            e.printStackTrace();
        }
        //返回所解析的bean数目
        return this.getRegistry().getBeanDefinitionCount() - countBefore;
    }
}
