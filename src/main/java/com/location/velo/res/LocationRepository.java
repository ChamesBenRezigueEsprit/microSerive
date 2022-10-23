package com.location.velo.res;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;



public interface LocationRepository extends JpaRepository <LocationEntity,Integer>{

	List<LocationEntity> findByUserId(int id);
	List<LocationEntity> findByTrottinetteId(int id);


}
