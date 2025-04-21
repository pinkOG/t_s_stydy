package com.example.finalwork02.config;




import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Key;
import java.util.Base64;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class MyInterceptor implements HandlerInterceptor {

    // 用于签名和验证JWT的秘钥
    private static final String SECRET_KEY = "your-256-bit-secret-your-256-bit-secret";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取请求头中的 Authorization 令牌
        String authToken = request.getHeader("Authorization");

        // 检查令牌是否存在以及是否有效
        if (authToken == null || !isValidToken(authToken)) {
            // 如果令牌无效，返回 401 未授权状态码
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Unauthorized");
            return false; // 请求终止
        }

        // 如果令牌有效，继续处理请求
        System.out.println("Pre Handle method is Calling");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("Post Handle method is Calling");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) throws Exception {
        System.out.println("Request and Response is completed");
    }

    //
    private boolean isValidToken(String token) {
        try {
            // 解析JWT
            Claims claims = Jwts.parser()
                    .setSigningKey(Base64.getDecoder().decode(SECRET_KEY))
                    .parseClaimsJws(token)
                    .getBody();

            // 根据需要验证声明内容
            String subject = claims.getSubject();
            String issuer = claims.getIssuer();
            if (subject == null || issuer == null || !issuer.equals("trustedIssuer")) {
                return false;
            }

            // 其他复杂的验证逻辑可以放在这里
            System.out.println("Token is valid. Subject: " + subject + ", Issuer: " + issuer);
            return true;
        } catch (ExpiredJwtException e) {
            System.out.println("Token is expired");
        } catch (UnsupportedJwtException e) {
            System.out.println("Token is unsupported");
        } catch (MalformedJwtException e) {
            System.out.println("Token is malformed");
        } catch (SignatureException e) {
            System.out.println("Signature validation failed");
        } catch (IllegalArgumentException e) {
            System.out.println("Token is null or empty");
        }
        return false;
    }
}