package com.cybertek.bootstrap;

import com.cybertek.entity.Address;
import com.cybertek.entity.Person;
import com.cybertek.repository.AddressRepository;
import com.cybertek.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataGenerator implements CommandLineRunner {

    @Autowired
    PersonRepository personRepository;
    @Autowired
    AddressRepository addressRepository;

    @Override
    public void run(String... args) throws Exception {

        Person p1 = new Person("Mike", "Smith");
        Person p2 = new Person("John", "Doe");
        Person p3 = new Person("Tom", "Hanks");

        Address a1 = new Address("277 Joseph St", "Pittsburgh", "PA", "15227");
        Address a2 = new Address("11 Any St", "Miami", "FL", "12345");
        Address a3 = new Address("2 Some Dr", "Cleveland", "OH", "54321");

//      CASE-1
//      p1.setAddresses(Arrays.asList(a1, a2));

        personRepository.save(p1);
        personRepository.save(p2);

        a1.setPerson(p1);
        a2.setPerson(p1);
        a3.setPerson(p2);

        addressRepository.save(a1);
        addressRepository.save(a2);
        addressRepository.save(a3);
    }
}
