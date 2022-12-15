package com.tenmilesquare.ryan.ehcache;

import org.ehcache.Cache;
import org.ehcache.CachePersistenceException;
import org.ehcache.PersistentCacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.MemoryUnit;

public class CacheTutorial {

    private final static String CACHE_NAME = "musicCache";

    public static void main(String[] args) throws CachePersistenceException {
        // The cache manager is closable resource. Clean it up after we are done
        try (PersistentCacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder()
                .with(CacheManagerBuilder.persistence("/tmp/ehcache-data"))
                .withCache(CACHE_NAME,
                        CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class,
                                Music.class, ResourcePoolsBuilder
                                        .heap(3).disk(1, MemoryUnit.GB)))
                .build(true)) {

            Music music1 = new Music("ISRC11234566", "Plush", "Stone Temple Pilots", "Atlantic");
            Music music2 = new Music("ISRC1234567", "Tumble in the Rough", "Stone Temple Pilots", "Atlantic");
            Music music3 = new Music("ISRC11234568", "Black", "Pearl Jam", "Epic");
            Music music4 = new Music("ISRC11234569", "Jeremy", "Pearl Jam", "Epic");
            Music music5 = new Music("ISRC11234579", "Deep", "Pearl Jam", "Epic");

            Cache<String, Music> musicCache = cacheManager.getCache(CACHE_NAME, String.class, Music.class);
            musicCache.put(music1.getId(), music1);
            musicCache.put(music2.getId(), music2);
            musicCache.put(music3.getId(), music3);
            musicCache.put(music4.getId(), music4);
            musicCache.put(music5.getId(), music5);

            Music music1Cache = musicCache.get(music1.getId());
            System.out.println("Music 1: " + music1Cache.toString());
            System.out.println("Logic equal 1: " + music1Cache.toString().equals(music1.toString()));

            Music music5Cache = musicCache.get(music5.getId());
            System.out.println("Music 5: " + music5Cache.toString());
            System.out.println("Logic equal 5: " + music5Cache.toString().equals(music5.toString()));

            musicCache.forEach(entry -> System.out.println(entry.getValue().toString()));

        }
    }
}
