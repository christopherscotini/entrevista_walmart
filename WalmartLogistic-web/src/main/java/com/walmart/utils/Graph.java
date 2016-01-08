package com.walmart.utils;

import java.util.List;

public class Graph {
  
  private List<Vertex> vertexList;
  private List<Edge> edgeList;
  
  public Graph(List<Vertex> vertices, List<Edge> arestas) {
    this.vertexList = vertices;
    this.edgeList = arestas;
  }

  public List<Vertex> getVertices() {
    return vertexList;
  }

  public List<Edge> getArestas() {
    return edgeList;
  }

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((edgeList == null) ? 0 : edgeList.hashCode());
	result = prime * result
			+ ((vertexList == null) ? 0 : vertexList.hashCode());
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
	Graph other = (Graph) obj;
	if (edgeList == null) {
		if (other.edgeList != null)
			return false;
	} else if (!edgeList.equals(other.edgeList))
		return false;
	if (vertexList == null) {
		if (other.vertexList != null)
			return false;
	} else if (!vertexList.equals(other.vertexList))
		return false;
	return true;
}

  
  
}
