package com.spider.utils;

import com.alibaba.fastjson.JSONObject;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Created by michael on 2017/10/25.
 */
public class LocalCache {
    private static final Log LOG = LogFactory.getLog(LocalCache.class);

    private static final Cache<String, String> cache = CacheBuilder
            .newBuilder()
            .recordStats()
            .initialCapacity(5000)
            .maximumSize(100000)
            .expireAfterWrite(1, TimeUnit.DAYS)
            .removalListener(new RemovalListener<String, String>() {
                @Override
                public void onRemoval(RemovalNotification<String, String> removalNotification) {
                    LOG.info(JSONObject.toJSONString(cache.stats()));
                }
            })
            .build();

    public static void put(String key, String value) {
        LOG.info("LocalCache,put:{key:"+key+",value:"+value+"}");
        cache.put(key, value);
    }

    /**
     *
     * @param key
     * @return key not exits return null
     */
    public static String getIfPresent(String key){
        String value = cache.getIfPresent(key);
        LOG.info("LocalCache,get:{key:"+key+",value:"+value+"}");
        return value;
    }

    public static void invalidate(String key){
        cache.invalidate(key);
        LOG.info("invalidate,key:"+key);
    }
}
