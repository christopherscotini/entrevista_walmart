package com.walmart.business.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.walmart.business.MapService;
import com.walmart.business.exception.NoDataFoundException;
import com.walmart.model.MapEntity;
import com.walmart.model.RouteMapEntity;
import com.walmart.repository.MapRepository;

@Stateless
public class MapServiceImpl implements MapService{
	
	@Inject
	private MapRepository repo = null;
	
	@Override
	public MapEntity findMapByName(String name) {
		MapEntity mapaEntity = repo.findMapByName(name);
	    if(mapaEntity == null){
	    	throw new NoDataFoundException(name);
	    }
		List<RouteMapEntity> routes = new ArrayList<RouteMapEntity>();
	    for (RouteMapEntity route: mapaEntity.getRoutes()) {
	      routes.add(new RouteMapEntity(route.getOrigin(), route.getDestination(), route.getDistance()));
	    }
	    return new MapEntity(mapaEntity.getName(), routes);
	}
	
	@Override
	public void persistMap(MapEntity map) {
		List<RouteMapEntity> routes = new ArrayList<RouteMapEntity>();
	    for (RouteMapEntity route : map.getRoutes()) {
	      routes.add(new RouteMapEntity(route.getOrigin(), route.getDestination(), route.getDistance()));
	    }
	    repo.insert(new MapEntity(map.getName(), routes));
	}
	
}
