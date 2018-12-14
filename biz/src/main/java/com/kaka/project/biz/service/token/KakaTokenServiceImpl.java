package com.kaka.project.biz.service.token;

import com.kaka.common.utils.LogUtil;
import com.kaka.common.utils.TokenUtil;
import com.kaka.project.config.properties.AppProperties;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.Date;


/**
 * Created by springyu on 17-7-4.
 */
@Service
public class KakaTokenServiceImpl implements KakaTokenService {
    private final LogUtil logger = new LogUtil(this.getClass());
    private static final long serialVersionUID = -3301605591108950415L;

    private static final String CLAIMS_CLIENT_ID = "clientId";
    private static final String CLAIMS_CLIENT_ID_VALUE = "browser";
    private static final String CLAIMS_UID = "uid";

    @Autowired
    private AppProperties appProperties;


    /**
     * 获取当前登入用户名
     *
     * @param token
     * @return
     */
    @Override
    public String getUsernameFromToken(String token) {
        return TokenUtil.getUsernameFromToken(token, appProperties.getSecret());
    }

    /**
     * 获取token创建时间
     *
     * @param token
     * @return
     */
    @Override
    public Date getCreatedDateFromToken(String token) {
        return TokenUtil.getCreatedDateFromToken(token, appProperties.getSecret());
    }

    /**
     * 获取token失效时间
     *
     * @param token
     * @return
     */
    @Override
    public Date getExpirationDateFromToken(String token) {
        return TokenUtil.getExpirationDateFromToken(token, appProperties.getSecret());
    }


    /**
     * 生成失效时间
     *
     * @return
     */
    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + appProperties.getExpiration() * 1000);
    }

    /**
     * 生成失效时间
     *
     * @return
     */
    private Date generateRefreshExpirationDate() {
        return new Date(System.currentTimeMillis() + appProperties.getRefreshExpiration() * 1000);
    }


    /**
     * token 是否失效
     *
     * @param token
     * @return true 有效 false 失效
     */
    @Override
    public Boolean validateToken(String token) {
        return !TokenUtil.isTokenExpired(token, appProperties.getSecret());
    }

    /**
     * public Boolean validateToken(String token, UserDetails userDetails) {
     * UserDTO userVO = (UserDTO) userDetails;
     * final String username = getUsernameFromToken(token);
     * final Date created = getCreatedDateFromToken(token);
     * //final Date expiration = getExpirationDateFromToken(token);
     * return (username.equals(userDetails.getUsername())
     * && !TokenUtil.isTokenExpired(token,appConfig.getSecret()) && !TokenUtil.isCreatedBeforeLastPasswordReset(created, userVO.getLastPasswordResetDate()));
     * }
     **/

    public static void main(String[] args) {
        String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImlzcyI6bnVsbCwiZXhwIjoxNTAwNDc2MTY4LCJpYXQiOjE0OTk4NzEzNjg1NzF9.4Ivc_2y-VhKetQUrOutOK9Ht_D20pNAMzTsK89lKmFJVYgOryEPbnKIm8oWsvOzUpPMkYRcGGLlmngimqaJrsg";
        KakaTokenServiceImpl aa = new KakaTokenServiceImpl();
        //"mySecret",604800L
//        System.out.println(aa.getClaimsFromToken(token));
    }
}
