package com.example.template.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.scripting.ScriptSource;
import org.springframework.scripting.support.ResourceScriptSource;

@Configuration
public class RedisConfig {
    @Bean(name = "normalRedisTemplate")
    public RedisTemplate<String, Object> normalRedisTemplate(LettuceConnectionFactory lettuceConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());

        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());

        redisTemplate.setConnectionFactory(lettuceConnectionFactory);

        return redisTemplate;
    }

    @Bean(name = "repositoryRedisTemplate")
    public RedisTemplate<?, ?> repositoryRedisTemplate(LettuceConnectionFactory lettuceConnectionFactory) {
        RedisTemplate<byte[], byte[]> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(lettuceConnectionFactory);
        return redisTemplate;
    }

    @Bean
    public StringRedisTemplate stringRedisTemplate(LettuceConnectionFactory lettuceConnectionFactory) {
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();

        stringRedisTemplate.setConnectionFactory(lettuceConnectionFactory);

        return stringRedisTemplate;
    }

    @Bean
    public RedisScript<Boolean> checkAndSetScript() {
        DefaultRedisScript<Boolean> redisScript = new DefaultRedisScript<Boolean>();
        ScriptSource scriptSource = new ResourceScriptSource(new ClassPathResource("scripts/checkandset.lua"));
        redisScript.setScriptSource(scriptSource);
        redisScript.setResultType(Boolean.class);
        return redisScript;
    }
}
