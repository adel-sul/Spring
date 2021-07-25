package com.cybertek.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// Execute All The Context - Beans, Dependencies, DB. Used For Integration Testing
//@SpringBootTest
// Execute And Test Just WelcomeController.class class
@WebMvcTest(WelcomeController.class)
class WelcomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void welcome() throws Exception {
        // call endpoint
        RequestBuilder request = MockMvcRequestBuilders.get("/welcome").accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(request).andReturn();
        // verify "welcome" message
        assertEquals("welcome", result.getResponse().getContentAsString());

    }

    @Test
    void welcome2() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/welcome").accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(request).
                andExpect(status().isOk()).
                andExpect(content().string("welcome")).
                andReturn();
    }
}