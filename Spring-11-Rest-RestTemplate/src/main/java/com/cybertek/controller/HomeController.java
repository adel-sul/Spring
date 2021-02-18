package com.cybertek.controller;

import com.cybertek.entity.User;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@RestController
public class HomeController {

    // 1. Define the endpoint url (ResttemplateApplication next)
    final String URI = "https://jsonplaceholder.typicode.com/users";

    private RestTemplate restTemplate;

    public HomeController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // 3. Consume the REST Service
    @GetMapping
    public User[] readAllUsers() {

        // getForEntity() method does not support request headers. Use exchange() method if headers are necessary.
        ResponseEntity<User[]> responseEntity = restTemplate.getForEntity(URI, User[].class);
        return responseEntity.getBody();
    }

    @GetMapping(value = "/{id}")
    public Object readUser(@PathVariable("id") Integer id) {

        String URL = URI + "/{id}";
        return restTemplate.getForObject(URL, Object.class, id);
    }

    @GetMapping("/test")
    public ResponseEntity<Object> consumePostFromDummyApi() {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.set("app-id","lTE5abbDxdjGplutvTuc");

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<Object> response = restTemplate.exchange("https://dummyapi.io/data/api/user?limit=10", HttpMethod.GET, entity, Object.class);

        return response;
    }
}
