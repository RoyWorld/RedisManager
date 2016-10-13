package com.redis.util.resolver;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import java.util.Locale;
import java.util.Map;

/**
 * Created by Administrator on 2015/11/26.
 */
public class MixedViewResolver implements ViewResolver {
    private Map<String,ViewResolver> resolvers;

    public void setResolvers(Map<String,ViewResolver> resolvers){
        this.resolvers = resolvers;
    }


    @Override
    public View resolveViewName(String viewName, Locale locale) throws Exception {
        int n = viewName.lastIndexOf(".");
        if(n != -1){
            String suffix = viewName.substring(n+1);
            ViewResolver resolver = resolvers.get(suffix);
            if(resolver == null){
                throw new RuntimeException("No ViewResolver For" + suffix);
            }
            return resolver.resolveViewName(viewName, locale);
        }else {
            ViewResolver resolver = resolvers.get("html");
            return resolver.resolveViewName(viewName, locale);
        }

    }
}
