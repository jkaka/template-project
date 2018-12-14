package com.kaka.project.biz.service.token;




import java.util.Date;

/**
 * Created by springyu on 17-7-4.
 */
public interface KakaTokenService {

    /**
     * 从token中获取用户名
     *
     * @param token
     * @return
     */
    String getUsernameFromToken(String token);

    /**
     * 获得token创建时间
     *
     * @param token
     * @return
     */
    Date getCreatedDateFromToken(String token);

    /**
     * 获得token失效时间
     *
     * @param token
     * @return
     */
    Date getExpirationDateFromToken(String token);

    /**
     * 验证token是否失效
     *
     * @param token
     * @param userDetails
     * @return
     */
   // Boolean validateToken(String token, UserDetails userDetails);

    /**
     * token 是否失效
     *
     * @param token
     * @return true 有效 false 失效
     */
    Boolean validateToken(String token);


}
