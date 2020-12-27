package com.cybertek.repository;

import com.cybertek.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {

    //Display All regions in Canada
    List<Region> findAllByCountry(String country);

    //Display All regions in Canada without duplicates
    List<Region> findDistinctByCountry(String country);

    //Display All regions with country name includes 'United'
    List<Region> findAllByCountryContains(String pattern);

    //Display All regions with country name includes 'United' in order
    List<Region> findAllByCountryContainsOrderByCountry(String pattern);

    //Display top 2 regions in country
    List<Region> findTop2ByCountry(String pattern);
}
