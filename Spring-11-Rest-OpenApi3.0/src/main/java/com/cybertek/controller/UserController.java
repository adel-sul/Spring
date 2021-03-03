package com.cybertek.controller;

import com.cybertek.entity.UserAccount;
import com.cybertek.repository.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@Tag(name = "User", description = "User API")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping()
    @Operation(summary = "Read all Users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "SUCCESS", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UserAccount.class))}),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content),
            @ApiResponse(responseCode = "404", description = "NOT FOUND", content = @Content)
    })
    public List<UserAccount> readAll() {
        return userRepository.findAll();
    }
}
