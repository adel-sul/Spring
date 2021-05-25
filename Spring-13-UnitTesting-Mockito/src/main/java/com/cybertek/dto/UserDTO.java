package com.cybertek.dto;

import com.cybertek.utils.Gender;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDTO {

    private Integer id;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private boolean enabled;
    private String phoneNumber;
    private RoleDTO role;
    private Gender gender;

}
