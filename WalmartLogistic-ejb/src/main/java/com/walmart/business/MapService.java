package com.walmart.business;

import java.util.List;

import com.walmart.model.MapEntity;

public interface MapService {
	
	public MapEntity findMapByName(String name); 
	public void persistMap(MapEntity map);
	public List<MapEntity> findAll(); 

}
