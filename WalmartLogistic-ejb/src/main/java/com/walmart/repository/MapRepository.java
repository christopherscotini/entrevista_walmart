/**
 * 
 */
package com.walmart.repository;

import com.walmart.model.MapEntity;
import com.walmart.repository.dao.JpaGenericDao;

public class MapRepository extends JpaGenericDao<MapEntity, Long> {

	public MapEntity findMapByName(String nome) {
		MapEntity map = null;
		map = getEntityManager().find(MapEntity.class, nome);
		if (map == null) {
			return null;
		}
		return map;
	}

}
