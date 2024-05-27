package com.sl.interview.test.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class UniqueEstate {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String location;
    private int size;
    private int bedrooms;
    private int bathrooms;
    private String imageUrl;
    private String nearbyHospitals;
    private String nearbyColleges;
    private Long vendorId;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getBedrooms() {
		return bedrooms;
	}
	public void setBedrooms(int bedrooms) {
		this.bedrooms = bedrooms;
	}
	public int getBathrooms() {
		return bathrooms;
	}
	public void setBathrooms(int bathrooms) {
		this.bathrooms = bathrooms;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getNearbyHospitals() {
		return nearbyHospitals;
	}
	public void setNearbyHospitals(String nearbyHospitals) {
		this.nearbyHospitals = nearbyHospitals;
	}
	public String getNearbyColleges() {
		return nearbyColleges;
	}
	public void setNearbyColleges(String nearbyColleges) {
		this.nearbyColleges = nearbyColleges;
	}
	public Long getVendorId() {
		return vendorId;
	}
	public void setVendorId(Long vendorId) {
		this.vendorId = vendorId;
	}
	@Override
	public String toString() {
		return "UniqueEstate [id=" + id + ", title=" + title + ", location=" + location + ", size=" + size
				+ ", bedrooms=" + bedrooms + ", bathrooms=" + bathrooms + ", imageUrl=" + imageUrl
				+ ", nearbyHospitals=" + nearbyHospitals + ", nearbyColleges=" + nearbyColleges + ", vendorId="
				+ vendorId + "]";
	}

    
}
