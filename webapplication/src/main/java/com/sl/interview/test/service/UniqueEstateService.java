package com.sl.interview.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sl.interview.test.entity.UniqueEstate;
import com.sl.interview.test.entity.UserAccount;
import com.sl.interview.test.repository.UniqueEstateRepository;
import com.sl.interview.test.repository.UserRepository;

import java.util.List;

@Service
public class UniqueEstateService {

    @Autowired
    private UniqueEstateRepository uniqueEstateRepository;
    
    @Autowired
    private UserRepository userRepository;

    public List<UniqueEstate> fetchVendorProperties(Long vendorId) {
        return uniqueEstateRepository.findByVendorId(vendorId);
    }

    public List<UniqueEstate> fetchAllProperties() {
        return uniqueEstateRepository.findAll();
    }

    public UniqueEstate fetchPropertyById(Long id) {
        return uniqueEstateRepository.findById(id).orElse(null);
    }

    public List<UniqueEstate> filterProperties(String location, int minBedrooms) {
        return uniqueEstateRepository.findByLocationAndBedroomsGreaterThanEqual(location, minBedrooms);
    }

    public UniqueEstate addNewProperty(UniqueEstate estate) {
        return uniqueEstateRepository.save(estate);
    }

    public UniqueEstate modifyProperty(UniqueEstate estate) {
        return uniqueEstateRepository.save(estate);
    }

    public void removeProperty(Long id) {
        uniqueEstateRepository.deleteById(id);
    }

    public UserAccount authenticateUser(String email, String password) {
        UserAccount user = userRepository.findByUserEmail(email);
        if (user != null && user.getUserPassword().equals(password)) {
            return user;
        }
        return null;
    }

	public UserAccount createUser(UserAccount user) {
		UserAccount save = userRepository.save(user);
		return save;
	}
}
