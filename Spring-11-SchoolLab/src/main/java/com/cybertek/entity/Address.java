package com.cybertek.entity;

import com.cybertek.enums.AddressType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.client.RestTemplate;

import javax.persistence.*;
import java.util.Map;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "address")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "teacher"}, ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Address extends BaseEntity{

    private String street;
    private String city;
    private String state;
    private String country;
    private String postalCode;

    @Enumerated(EnumType.STRING)
    private AddressType addressType;

    @OneToOne(mappedBy = "address")
    @JsonBackReference
    private Student student;

    //ignored on field level
    @OneToOne(mappedBy = "address")
    @JsonIgnore
    private Parent parent;

    //ignored on class level
    @OneToOne(mappedBy = "address")
    private Teacher teacher;

    private Integer currentTemperature;

    public Integer consumeTemperature(String city) {

        RestTemplate restTemplate = new RestTemplate();

        final String BASE_URL = "http://api.weatherstack.com/current?access_key=02a009b8e3922c395677a1e85406aca6&query=";
        String url = BASE_URL + city;

        Object currentWeather = restTemplate.getForObject(url, Object.class);

        Map<String, Object> getWeather = (Map<String, Object>) currentWeather;
        Map<String, Object> getTemperature = (Map<String, Object>) getWeather.get("current");

        return Integer.parseInt(getTemperature.get("temperature").toString());

    }
}
