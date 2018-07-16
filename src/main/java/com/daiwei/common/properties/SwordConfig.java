package com.daiwei.common.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 *
 * @author  david:
 * @date 创建时间：2017年8月23日 下午10:01:34
 * @version 1.0
 * @parameter
  * @since 
 * @return 
 */
@Data
@ConfigurationProperties(locations = "classpath:application.properties", prefix = "sword")
@Component
public class SwordConfig {

    private String ctx;

    private String uploadPath;

    private String author;
    
    private boolean operationLog ;
    
    private boolean redisOpen ;
    
    private boolean taskOpen ;
}
