package com.walmart.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "MAPA")
public class MapEntity implements Serializable {

	private static final long serialVersionUID = 4814486752446911113L;

	@Id
	@Column(name = "MAPA_CD_ID_PK")
	private String name;

	@OneToMany(targetEntity = RouteMapEntity.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<RouteMapEntity> routes;

	public MapEntity() {
	}

	public MapEntity(String name, List<RouteMapEntity> routes) {
		this.name = name;
		this.routes = routes;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name == null) {
			throw new IllegalArgumentException("nome is null");
		}
		this.name = name;
	}

	public List<RouteMapEntity> getRoutes() {
		return routes;
	}

	public void setRoutes(List<RouteMapEntity> routes) {
		this.routes = routes;
	}


}
