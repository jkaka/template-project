package com.kaka.project.config.properties;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * 系统配置参数
 */
@EnableApolloConfig
@RefreshScope
@Component
public class AppProperties {
    /**
     * token 头部 key
     */
    @Value("${token.header}")
    private String tokenHeader;

    /**
     * token 密钥
     */
    @Value("${token.secret}")
    private String secret;

    /**
     * token 失效时间
     */
    @Value("${token.expiration}")
    private Long expiration;

    /**
     * token 刷新时间
     */
    @Value("${token.refresh-expiration}")
    private Long refreshExpiration;

    @Value("${spring.profiles.active}")
    private String active;

    @Value("${environment.env-date}")
    private String envDate;

    @Value("${environment.sys-version}")
    private String sysVersion;

    @Value("${app.server.port}")
    private Integer serverPort;

    public String getTokenHeader() {
        return tokenHeader;
    }

    public void setTokenHeader(String tokenHeader) {
        this.tokenHeader = tokenHeader;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public Long getExpiration() {
        return expiration;
    }

    public void setExpiration(Long expiration) {
        this.expiration = expiration;
    }

    public Long getRefreshExpiration() {
        return refreshExpiration;
    }

    public void setRefreshExpiration(Long refreshExpiration) {
        this.refreshExpiration = refreshExpiration;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getEnvDate() {
        return envDate;
    }

    public void setEnvDate(String envDate) {
        this.envDate = envDate;
    }

    public String getSysVersion() {
        return sysVersion;
    }

    public void setSysVersion(String sysVersion) {
        this.sysVersion = sysVersion;
    }

    public Integer getServerPort() {
        return serverPort;
    }

    public void setServerPort(Integer serverPort) {
        this.serverPort = serverPort;
    }
}