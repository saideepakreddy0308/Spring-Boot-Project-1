package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.configs;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;

public class MapperConfig {

    @Bean // define bean
    // here, this is public method, and also the one that would return the factory method of object mapper
    public ModelMapper getModelMapper(){
        return new ModelMapper();
        // now easily, inject in service, where ever we want to
    }
}
