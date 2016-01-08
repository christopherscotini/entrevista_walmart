package com.walmart.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.walmart.model.MapEntity;
import com.walmart.model.RouteMapEntity;
import com.walmart.utils.exception.CalculateDistanceException;

/**
 * Implementação do cálculo de menor distância. Baseado no algortimo de custo mínimo de E. Dijkstra.
 * 
 * Artigo utilizado para implementacao:
 * 
 * @see <a
 *      href="http://www.inf.ufsc.br/grafos/temas/custo-minimo/dijkstra.html">http://www.inf.ufsc.br/grafos/temas/custo-minimo/dijkstra.html</a>
 * 
 */

public class CalculateDistanceUtil {

	private final List<Edge> edgeList;
	private Set<Vertex> nodesClosed;
	private Set<Vertex> nodesOpened;
	private Map<Vertex, Vertex> previous;
	private Map<Vertex, Double> distance;
	Map<String, Vertex> vertex;

	/**
	 * Instancia o algoritmo de custo minimo dado um grafo.
	 */
	public CalculateDistanceUtil(MapEntity map) {
		vertex = new HashMap<String, Vertex>();
		List<Edge> arestas = new ArrayList<Edge>();

		for (RouteMapEntity route : map.getRoutes()) {
			Vertex origin = vertex.get(route.getOrigin());
			Vertex destination = vertex.get(route.getDestination());
			if (origin == null) {
				origin = new Vertex(route.getOrigin());
				vertex.put(route.getOrigin(), origin);
			}
			if (destination == null) {
				destination = new Vertex(route.getDestination());
				vertex.put(route.getDestination(), destination);
			}
			arestas.add(new Edge(origin, destination, route.getDistance()));
		}
	
		this.edgeList = new ArrayList<Edge>(arestas);
	}

	public void execute(String s) throws CalculateDistanceException {
		Vertex start = vertex.get(s);
		
		if (start == null) {
			throw new CalculateDistanceException(String.format("Destino '%s' nulo ou nao encontrado no mapa.", s));
		}
		
		nodesClosed = new HashSet<Vertex>();
		nodesOpened = new HashSet<Vertex>();
		distance = new HashMap<Vertex, Double>();
		previous = new HashMap<Vertex, Vertex>();

		distance.put(start, 0d);
		nodesOpened.add(start);
		while (nodesOpened.size() > 0) {
			Vertex node = getMinDistance(nodesOpened);
			nodesClosed.add(node);
			nodesOpened.remove(node);
			findShortDistance(node);
		}
		
	}

	private void findShortDistance(Vertex node) {
		List<Vertex> nosAdjacentes = getNeighbors(node);
		for (Vertex destination : nosAdjacentes) {
			if (getShortDistance(destination) > getShortDistance(node) + getDistance(node, destination)) {
				distance.put(destination, getShortDistance(node) + getDistance(node, destination));
				previous.put(destination, node);
				nodesOpened.add(destination);
			}
		}
	}

	private double getDistance(Vertex node, Vertex destination) {
		for (Edge edge : edgeList) {
			if (edge.getFrom().equals(node) && edge.getTo().equals(destination)) {
				return edge.getDistance();
			}
		}
		throw new RuntimeException("Erro inesperado!");
	}

	private List<Vertex> getNeighbors(Vertex no) {
		List<Vertex> neighbors = new ArrayList<Vertex>();
		for (Edge aresta : edgeList) {
			if (aresta.getFrom().equals(no) && !isOpened(aresta.getTo())) {
				neighbors.add(aresta.getTo());
			}
		}
		return neighbors;
	}

	private Vertex getMinDistance(Set<Vertex> vertices) {
		Vertex min = null;
		for (Vertex vertice : vertices) {
			if (min == null) {
				min = vertice;
			} else {
				if (getShortDistance(vertice) < getShortDistance(min)) {
					min = vertice;
				}
			}
		}
		return min;
	}

	private boolean isOpened(Vertex vertex) {
		return nodesClosed.contains(vertex);
	}

	private double getShortDistance(Vertex destination) {
		Double d = distance.get(destination);
		if (d == null) {
			return Double.MAX_VALUE;
		} else {
			return d;
		}
	}

	/**
	 * Obtem o melhor caminho(menor custo)
	 */
	public LinkedList<Vertex> getRoute(String w) throws CalculateDistanceException {
		
		LinkedList<Vertex> route = new LinkedList<Vertex>();
		Vertex destination = vertex.get(w);
		
		if (destination == null) {
			throw new CalculateDistanceException(String.format("Destino '%s' nulo ou nao encontrado no mapa.", w));
		}
		
		Vertex step = destination;
		if (previous.get(step) == null) {
			return null;
		}
		
		route.add(step);
		while (previous.get(step) != null) {
			step = previous.get(step);
			route.add(step);
		}

		Collections.reverse(route);
		
		return route;
	}

	/**
	 * Obtem a distancia para determinado vertice.
	 */
	public double getDistance(String w) throws CalculateDistanceException {
		Vertex destination = vertex.get(w);
		if (destination == null) {
			throw new CalculateDistanceException(String.format("Destino '%s' nulo ou nao encontrado no mapa.", w));
		}
		if (distance.containsKey(destination)) {
			return distance.get(destination);
		}
		throw new RuntimeException("Erro inesperado.");
	}

}
