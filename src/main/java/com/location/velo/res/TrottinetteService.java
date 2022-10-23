package com.location.velo.res;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="trottinette-service")
public interface TrottinetteService {
	@GetMapping("/retrieve-Produit/{id}")
	public Trottinette findTroittinetteById(@PathVariable(name="id") Long id);

}
