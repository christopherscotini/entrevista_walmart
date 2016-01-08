package com.walmart.rest.json;

import java.util.Arrays;

/**
 * @author Diego Santos
 * 
 */
public class MapJSON {

	private String name;
	private String[][] network;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String[][] getNetwork() {
		return network;
	}

	public void setNetwork(String[][] network) {
		this.network = network;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + Arrays.hashCode(network);
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
		MapJSON other = (MapJSON) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (!Arrays.deepEquals(network, other.network))
			return false;
		return true;
	}

}
