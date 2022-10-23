package com.location.velo.res;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/locations")
public class LocationRestApi {


	@Autowired
	LocationIService locIService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<LocationEntity> createLocation(@RequestBody LocationEntity location){
		return new ResponseEntity<>(locIService.addLocation(location),HttpStatus.OK);
	}
	
	@PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<LocationEntity> updateLocation(@PathVariable(value="id") int id ,
			@RequestBody LocationEntity location){
		return new ResponseEntity<>(locIService.updateLocation(id, location),HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public boolean deleteLocation(@PathVariable(value="id") int id){
		return locIService.deleteLocation(id);
	}
	
	
	
	@GetMapping(value ="/user/{id}")
	@ResponseStatus(HttpStatus.OK)
	public List<LocationEntity> fetchLocationByUserId(@PathVariable(value="id") int id ){
		return locIService.getLocationsByUser(id);
	}
	
	@GetMapping(value ="/velo/{id}")
	@ResponseStatus(HttpStatus.OK)
	public List<LocationEntity> fetchLocationByVeloId(@PathVariable(value="id") int id ){
		return locIService.getLocationsByTrottinette(id);
	}
	
	@GetMapping(value ="/listLocation")
	@ResponseStatus(HttpStatus.OK)
	public List<LocationDTO> fetchListLocation(){
		return locIService.getListLocations();
	}
	
}
