package com.hs.auth.utils;

import cn.hutool.core.util.IdUtil;
import com.hs.authservice.model.UserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class TokenUtil {

    public static final String TOKEN_PREFIX = "token:";

    // Token 过期时间：24小时（可配置）
    private static final long TOKEN_EXPIRE_TIME = 86400L;

    private final RedisTemplate<String, Object> redisTemplate;

    /**
     * 生成 Token 并存储到 Redis
     */
    public String generateToken(UserModel user) {
        // 生成 UUID 作为 Token
        String token = IdUtil.fastUUID();

        // 缓存中存入json String
        ObjectMapper objectMapper = new ObjectMapper();

        String userStr = objectMapper.writeValueAsString(user);

        // 存储 Token 到 Redis，关联用户信息，设置过期时间

        redisTemplate.opsForValue().set(TOKEN_PREFIX + token, userStr, TOKEN_EXPIRE_TIME, TimeUnit.SECONDS);
        return token;
    }

    /**
     * 根据 Token 获取用户信息
     */
    public UserModel getUserByToken(String token) {
        if (token == null || token.isEmpty()) {
            return null;
        }

        ObjectMapper objectMapper = new ObjectMapper();
        Object user = redisTemplate.opsForValue().get(TOKEN_PREFIX + token);
        if(null == user){
            return null;
        }
        UserModel userModel = objectMapper.readValue((String)user, UserModel.class);

        return userModel;
    }

    /**
     * 验证 Token 是否有效
     */
    public boolean validateToken(String token) {
        return getUserByToken(token) != null;
    }

    /**
     * 删除 Token（登出时使用）
     */
    public void deleteToken(String token) {
        if (token != null && !token.isEmpty()) {
            redisTemplate.delete(TOKEN_PREFIX + token);
        }
    }

    /**
     * 刷新 Token 过期时间（可选，如用户操作时续期）
     */
    public void refreshToken(String token) {
        if (validateToken(token)) {
            redisTemplate.expire(TOKEN_PREFIX + token, TOKEN_EXPIRE_TIME, TimeUnit.SECONDS);
        }
    }
}
