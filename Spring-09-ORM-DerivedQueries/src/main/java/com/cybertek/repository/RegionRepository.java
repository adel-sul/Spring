package com.cybertek.repository;

import com.cybertek.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {
    /*
       SEE DepartmentRepository
    */

    // TODO: Display All regions in Canada
    List<Region> findAllByCountry(String country);

    // TODO: Display All regions in Canada without duplicates
    List<Region> findDistinctByCountry(String country);

    // ----- Matching Conditions -----
    // TODO: Display All regions with country name includes 'United'
    List<Region> findAllByCountryContains(String pattern);

    // ----- Sorting -----
    // TODO: Display All regions with country name includes 'United' in order
    List<Region> findAllByCountryContainsOrderByCountry(String pattern);

    // ----- Limiting Query Results -----
    // TODO: Display Top 2 regions in country
    List<Region> findTop2ByCountry(String pattern);
}
