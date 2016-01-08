package com.walmart.rest.json;

import java.util.Arrays;

public class MinCostResponseJSON {

	private String[] route;
	private double cost;

	public String[] getRoute() {
		return route;
	}

	public void setRoute(String[] route) {
		this.route = route;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(cost);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + Arrays.hashCode(route);
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
		MinCostResponseJSON other = (MinCostResponseJSON) obj;
		if (Double.doubleToLongBits(cost) != Double
				.doubleToLongBits(other.cost))
			return false;
		if (!Arrays.equals(route, other.route))
			return false;
		return true;
	}

}
