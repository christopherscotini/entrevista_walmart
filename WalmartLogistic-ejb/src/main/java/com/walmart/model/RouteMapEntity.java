package com.walmart.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MAPA_CAMINHO")
public class RouteMapEntity implements Serializable {

	private static final long serialVersionUID = -3990287719846114115L;

	@Id
	@GeneratedValue
	@Column(name = "MACA_CD_ID_PK")
	private int id;

	@Column(name = "MACA_DS_ORIGEM")
	private String origin;

	@Column(name = "MACA_DS_DESTINO")
	private String destination;

	@Column(name = "MACA_VL_DISTANCIA")
	private double distance;

	public RouteMapEntity() {
	}

	public RouteMapEntity(String origem, String destino, double distancia) {
		this.origin = origem;
		this.destination = destino;
		this.distance = distancia;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		if (origin == null) {
			throw new IllegalArgumentException("origem is null");
		}
		this.origin = origin;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		if (destination == null) {
			throw new IllegalArgumentException("destino is null");
		}
		this.destination = destination;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		if (distance < 0) {
			throw new IllegalArgumentException("distancia negativa");
		}
		this.distance = distance;
	}

	
}
