package com.cybertek.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;

@Component
public class MapperUtil {

    private ModelMapper modelMapper;

    public MapperUtil(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public <T> T convert(Object obj, T targetObj) {
        return modelMapper.map(obj, (Type) targetObj.getClass());
    }
}
