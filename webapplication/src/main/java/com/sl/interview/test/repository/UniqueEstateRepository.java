package com.sl.interview.test.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sl.interview.test.entity.UniqueEstate;

import java.util.List;

@Repository
public interface UniqueEstateRepository extends JpaRepository<UniqueEstate, Long> {
    List<UniqueEstate> findByVendorId(Long vendorId);
    List<UniqueEstate> findByLocationAndBedroomsGreaterThanEqual(String location, int bedrooms);
}
