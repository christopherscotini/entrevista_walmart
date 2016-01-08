package com.walmart.rest.json;

import java.util.List;

public class MapJSON {

	private String name;
	private List<MapSegment> network;

	public MapJSON() {
		// TODO Auto-generated constructor stub
	}
	
	public MapJSON(String name, List<MapSegment> network) {
		super();
		this.name = name;
		this.network = network;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<MapSegment> getNetwork() {
		return network;
	}

	public void setNetwork(List<MapSegment> network) {
		this.network = network;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((network == null) ? 0 : network.hashCode());
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
		if (network == null) {
			if (other.network != null)
				return false;
		} else if (!network.equals(other.network))
			return false;
		return true;
	}

}
