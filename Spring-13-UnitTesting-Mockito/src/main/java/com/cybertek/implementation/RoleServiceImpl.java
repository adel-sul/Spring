package com.cybertek.implementation;

import com.cybertek.dto.RoleDTO;
import com.cybertek.entity.Role;
import com.cybertek.mapper.RoleMapper;
import com.cybertek.repository.RoleRepository;
import com.cybertek.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<RoleDTO> listAllRoles() {
        List<Role> roles =  roleRepository.findAll();

        // converting entities to com.cybertek.dto objects using stream
        return roles.stream().map(role -> {return roleMapper.convertToDto(role);}).collect(Collectors.toList());
    }

    @Override
    public RoleDTO findById(Integer id) {
        Role role = roleRepository.findById(id).get(); // get() converts Optional<Role> to Role object
        return roleMapper.convertToDto(role);
    }
}
