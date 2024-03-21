package com.villatter.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfiguration implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/check/cekPenduduk");
        registry.addViewController("/dashboard").setViewName("forward:/dashboard/index");
        registry.addViewController("/penduduk").setViewName("forward:/penduduk/index");
        registry.addViewController("/pengajuan").setViewName("forward:/pengajuan/index");
        registry.addViewController("/selesai").setViewName("forward:/selesai/index");
    }
}