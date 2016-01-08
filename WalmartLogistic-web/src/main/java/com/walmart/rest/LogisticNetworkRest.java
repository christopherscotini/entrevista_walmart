/**
 * 
 */
package com.walmart.rest;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.walmart.business.MapService;
import com.walmart.exception.BusinessException;
import com.walmart.model.MapEntity;
import com.walmart.model.RouteMapEntity;
import com.walmart.rest.json.ErrorJSON;
import com.walmart.rest.json.MapJSON;
import com.walmart.rest.json.MapSegment;

/**
 * Classe que expoe o serviço de cadastro de Malha Logistica.
 */
@ApplicationScoped
@Path("/network")
public class LogisticNetworkRest {

	@Inject
	private MapService mapService;

	/**
	 * Servico responsavel por cadastrar os dados da malha logistica
	 * 
	 * @param map
	 *            um objeto do tipo {@link MapJSON} com o nome do mapa e os
	 *            dados da malha logistica, origem, destino e distancia.
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void insert(MapJSON map) {
		List<RouteMapEntity> routes = new ArrayList<RouteMapEntity>();
		for (int i = 0; i < map.getNetwork().size(); i++) {
			routes.add(new RouteMapEntity(map.getNetwork().get(i).getOrigin(),
					map.getNetwork().get(i).getDestination(), 
					map.getNetwork().get(i).getDistance()));

		}
				
		mapService.persistMap(new MapEntity(map.getName(), routes));
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMaps() {
		List<MapJSON> response = new ArrayList<MapJSON>();
		try {
			List<MapEntity> maps = mapService.findAll();
			
			for (int i = 0; i < maps.size(); i++) {
				MapJSON obj = new MapJSON();
				obj.setName(maps.get(i).getName());
				List<MapSegment>routes = new ArrayList<MapSegment>();
				for (int j = 0; j < maps.get(i).getRoutes().size(); j++) {
					RouteMapEntity route = maps.get(i).getRoutes().get(j);
					routes.add(new MapSegment(route.getOrigin(), route.getDestination(), route.getDistance()));
				}
				obj.setNetwork(routes);
				response.add(new MapJSON(obj.getName(), routes));
			}
		} catch (BusinessException ex) {
			return Response.status(Status.NOT_FOUND).entity(new ErrorJSON(Status.NOT_FOUND.ordinal(), ex.getMessage()) ).build();
		} catch (Exception ex) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(new ErrorJSON(Status.INTERNAL_SERVER_ERROR.ordinal(), ex.getMessage())).build();
		}

		return Response.status(Status.OK).entity(response).build();
	}

}
