package com.location.velo.res;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.stereotype.Service;



@Service
public class LocationService implements LocationIService{

	@Autowired
	private LocationRepository locationRepository;
	
	@Autowired
	private TrottinetteService trottinetteService;
	@Override
	public LocationEntity addLocation(LocationEntity LE) {
		Trottinette t1 = trottinetteService.findTroittinetteById(LE.getTrottinetteId());
		
		System.out.println("******************");
		System.out.println("id : " + t1.getId());
		System.out.println("name : "+t1.getName());
		System.out.println("******************");
		return locationRepository.save(LE);
	}

	@Override
	public LocationEntity updateLocation(int id, LocationEntity LE) {
		if(locationRepository.findById(id).isPresent()){
			LocationEntity existingLocation=locationRepository.findById(id).get();
			existingLocation.setDateStart(LE.getDateStart());
			existingLocation.setDateEnd(LE.getDateEnd());
			existingLocation.setUserId(LE.getUserId());
			existingLocation.setTrottinetteId(LE.getTrottinetteId());
			

			
			return locationRepository.save(existingLocation);
		}
		else return null;	}

	@Override
	public boolean deleteLocation(int id) {
		if(locationRepository.findById(id).isPresent()){
			locationRepository.deleteById(id);
			return true;
		}
		return false;	
		}

	@Override
	public LocationEntity getOneLocation(int id) {
		return locationRepository.findById(id).get();
	}

	@Override
	public List<LocationEntity> getAllLocation() {
		return locationRepository.findAll();
	}

	@Override
	public List<LocationEntity> getLocationsByUser(int id) {
		return locationRepository.findByUserId(id);

	}

	@Override
	public List<LocationEntity> getLocationsByTrottinette(int id) {
		return locationRepository.findByTrottinetteId(id);

	}

	@Override
	public List<LocationDTO> getListLocations() {
		List<LocationEntity> LE =locationRepository.findAll();
		List<LocationDTO> LDTO = new ArrayList<>();
		
		for(LocationEntity l :LE) {
			Trottinette T = trottinetteService.findTroittinetteById(l.getTrottinetteId());
			LocationDTO ld = new LocationDTO();
			ld.setDateStart(l.getDateStart());
			ld.setDateEnd(l.getDateEnd());
			ld.setId(l.getId());
			ld.setUserId(l.getUserId());
			ld.setTrottinette(T);
			LDTO.add(ld);
		}
		return LDTO;
	}


}
