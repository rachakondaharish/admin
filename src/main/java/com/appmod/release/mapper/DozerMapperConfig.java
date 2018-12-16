package com.appmod.release.mapper;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.dozer.DozerBeanMapper;

@Component
public class DozerMapperConfig {
	
	@Bean
    public DozerBeanMapper dozerMapper() throws Exception {
        final DozerBeanMapper dozerMapperBean = new DozerBeanMapper();
        return dozerMapperBean;
    }

}
