package com.java.xingfu.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author 掘金
 * @date 2018/11/21
 * @desc
 */
@Data
@ConfigurationProperties(prefix = "cas")
public class CasProperties {

    private String clientHostUrl;

    private String serverHostUrl;
}
