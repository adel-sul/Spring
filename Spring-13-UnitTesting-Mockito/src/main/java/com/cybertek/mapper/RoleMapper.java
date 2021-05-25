package com.cybertek.mapper;

import com.cybertek.dto.RoleDTO;
import com.cybertek.entity.Role;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {

    private ModelMapper modelMapper;

    public RoleMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Role convertToEntity(RoleDTO roleDto) {
        return modelMapper.map(roleDto, Role.class);
    }

    public RoleDTO convertToDto(Role role) {
        return modelMapper.map(role, RoleDTO.class);
    }
}
