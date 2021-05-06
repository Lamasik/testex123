package com.testex.testex.configuration;

import com.testex.testex.classes.FileCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileCacheConfiguration {
    @Bean
    public FileCache getFileCache() {
        return FileCache.getInstance();
    }
}
