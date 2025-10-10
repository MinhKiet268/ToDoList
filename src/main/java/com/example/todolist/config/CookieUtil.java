package com.example.todolist.config;

import com.example.todolist.exception.CookieNameNotFoundException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CookieUtil {

    @Value("${cookie.expiration}")
    private int cookieExpiry; // 7 days

    @Value("${jwt.expiration}")
    private int accessTokenExpiry; // 10 minutes

    @Value("${cookie.refreshtoken.name}")
    private String refreshTokenName;

    @Value("${cookie.accesstoken.name}")
    private String accessTokenName;

    @Value("${cookie.accesstoken.path}")
    private String accessTokenPath;

    @Value("${cookie.refreshtoken.path}")
    private String refreshTokenPath;

    public String getCookieFromRequest(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie: cookies) {
                if (name.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        throw new CookieNameNotFoundException(name + " cookie not found");
    }

    public void responseAccessTokenCookie(HttpServletResponse response, String token) {
        Cookie cookie = new Cookie(accessTokenName, token);
        cookie.setHttpOnly(true);
        cookie.setSecure(true); // Set to true if using HTTPS
        cookie.setPath(accessTokenPath);
        cookie.setMaxAge(accessTokenExpiry);
        response.addCookie(cookie);
    }

    public void responseRefreshTokenCookie(HttpServletResponse response, String token) {
        Cookie cookie = new Cookie(refreshTokenName, token);
        cookie.setHttpOnly(true);
        cookie.setSecure(true); // Set to true if using HTTPS
        cookie.setPath(refreshTokenPath);
        cookie.setMaxAge(cookieExpiry);
        response.addCookie(cookie);
    }

    public void deletCookie(HttpServletResponse response, String name, String path) {
        Cookie cookie = new Cookie(name, null); //
        cookie.setHttpOnly(true); // Prevent client-side scripts from accessing the cookie
        cookie.setSecure(true); // Ensure the cookie is sent over HTTPS only
        cookie.setPath(path); // Make the cookie available to the entire application
        cookie.setMaxAge(0); // Delete the cookie
        response.addCookie(cookie);
    }
}
