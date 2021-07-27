package com.cybertek.controller;

import com.cybertek.entity.Student;
import com.cybertek.service.StudentService;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// we will need to manually inject all dependencies
@WebMvcTest(StudentController.class)
class StudentControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    StudentService studentService;

    @Test
    void getStudent_service() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/student").accept(MediaType.APPLICATION_JSON)).
                andExpect(status().isOk()).
                andExpect(content().json("{ \"id\": 0, \"firstName\": \"Mike\", \"lastName\": \"Smith\", \"age\": 20 }"))
               .andReturn();
    }

    @Test
    void jsonAssert() throws JSONException {
        String expected = "{ \"id\": 0, \"firstName\": \"Mike\", \"lastName\": \"Smith\", \"age\": 20 }";
        String actual = "{ \"id\": 0, \"firstName\": \"Mike\", \"lastName\": \"Smith\", \"age\": 20 }";

        // strict = true - all fields need to match = false - can match partial json
        JSONAssert.assertEquals(expected, actual, false);
    }

    @Test
    void jsonAssertWithoutEscapeCharacters() throws JSONException {
        String expected = "{ id: 0, firstName: Mike, lastName: Smith, age: 20 }";
        String actual = "{ id: 0, firstName: Mike, lastName: Smith, age: 20 }";

        // strict = true - all fields need to match = false - can match partial json
        JSONAssert.assertEquals(expected, actual, false);
    }

    @Test
    void getStudent_data() throws Exception {
        when(studentService.getStudentData()).thenReturn(Arrays.asList(
                new Student("adel", "suleymanov", 25),
                new Student("maryna", "lugova", 24)));

        mockMvc.perform(MockMvcRequestBuilders.get("/data").accept(MediaType.APPLICATION_JSON)).
                andExpect(status().isOk()).
                andExpect(content().json("[{\"id\":0,\"firstName\":\"adel\",\"lastName\":\"suleymanov\",\"age\":25},{\"id\":0,\"firstName\":\"maryna\",\"lastName\":\"lugova\",\"age\":24}]")).
                andReturn();

    }
}