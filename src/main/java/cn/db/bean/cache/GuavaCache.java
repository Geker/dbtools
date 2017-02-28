package cn.db.bean.cache;

import java.util.concurrent.ExecutionException;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

public class GuavaCache {
    public static void main(String[] args) throws ExecutionException {
        CacheLoader<String, Object> loader = new CacheLoader<String, Object>() {

            @Override
            public Object load(String key) throws Exception {
                System.out.println("gen: " + key);
                return key + "-value";
            }

        };
        LoadingCache<String, Object> cache = CacheBuilder.newBuilder().build(loader);
        System.out.println(cache.get("hello"));
        System.out.println(cache.get("hello"));
        cache.invalidate("hello");
        // cache.refresh("hello");
        System.out.println(cache.get("hello"));

        System.out.println(cache.stats());


    }
}
