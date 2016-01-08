package com.walmart.business;

import com.walmart.model.MapEntity;

public interface MapService {
	
	public MapEntity findMapByName(String name); 
	public void persistMap(MapEntity map); 

}
