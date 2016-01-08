package com.walmart.utils;

public class Edge {

	private Vertex from;
	private Vertex to;
	private Double distance;

	public Edge(Vertex from, Vertex to, Double distance) {
		this.from = from;
		this.to = to;
		this.distance = distance;
	}

	public Vertex getFrom() {
		return from;
	}

	public Vertex getTo() {
		return to;
	}

	public Double getDistance() {
		return distance;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((distance == null) ? 0 : distance.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Edge other = (Edge) obj;
		if (distance == null) {
			if (other.distance != null)
				return false;
		} else if (!distance.equals(other.distance))
			return false;
		return true;
	}

}
