package com.caching.caching.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.caching.caching.entity.CloudVendor;
import com.caching.caching.response.ResponseHandler;
import com.caching.caching.service.CloudVendorService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/cloudvendor")
public class CloudVendorController {

	 CloudVendorService cloudVendorService;

	    public CloudVendorController(CloudVendorService cloudVendorService) {
	        this.cloudVendorService = cloudVendorService;
	    }

	    // Read Specific Cloud Vendor Details from DB
	    @GetMapping("/{vendorId}")
	    @ApiOperation(value ="Cloud vendor id", notes="Provide cloud vendor details",
	                    response = ResponseEntity.class)
	    public ResponseEntity<Object> getCloudVendorDetails(@PathVariable("vendorId") String vendorId)
	    {
	       return ResponseHandler.responseBuilder("Requested Vendor Details are given here",
	                HttpStatus.OK, cloudVendorService.getCloudVendor(vendorId));
	    }

	    // Read All Cloud Vendor Details from DB
	    @GetMapping("/")
	    public List<CloudVendor> getAllCloudVendorDetails()
	    {
	        return cloudVendorService.getAllCloudVendors();
	    }

	    @PostMapping("/")
	    public String createCloudVendorDetails(@RequestBody CloudVendor cloudVendor)
	    {
	        cloudVendorService.createCloudVendor(cloudVendor);
	        return "Cloud Vendor Created Successfully";
	    }

	    @PutMapping("/")
	    public String updateCloudVendorDetails(@RequestBody CloudVendor cloudVendor)
	    {
	        cloudVendorService.updateCloudVendor(cloudVendor);
	        return "Cloud Vendor Updated Successfully";
	    }

	    @DeleteMapping("/{vendorId}")
	    public String deleteCloudVendorDetails(@PathVariable("vendorId") String vendorId)
	    {
	        cloudVendorService.deleteCloudVendor(vendorId);
	        return "Cloud Vendor Deleted Successfully";
	    }
}
