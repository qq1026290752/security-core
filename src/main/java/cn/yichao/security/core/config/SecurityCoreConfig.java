package cn.yichao.security.core.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import cn.yichao.security.core.properties.SecurityPeoperties; 
/**
 * 配置前缀生效
 * @author w4837
 *
 */
@Configuration
@EnableConfigurationProperties (SecurityPeoperties.class)
public class SecurityCoreConfig {

}
