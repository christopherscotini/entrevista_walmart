package com.walmart.rest;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.walmart.business.MapService;
import com.walmart.exception.BusinessException;
import com.walmart.model.MapEntity;
import com.walmart.rest.json.MinCostResponseJSON;
import com.walmart.utils.CalculateDistanceUtil;
import com.walmart.utils.Vertex;
import com.walmart.utils.exception.CalculateDistanceException;

/**
 * Classe que expoe o serviço de calculo do custo minimo.
 * 
 * @author Diego Santos
 * @since 18/08/2015
 *
 */
@ApplicationScoped
@Path("/calcBestCostDelivery")
public class CalcMinCostRest {
  
  @Inject
  private MapService mapService;
  
  /**
   * Servico responsavel pelo calculo da rota e custo minimo.
   * 
   * @param mapName nome do mapa
   * @param origin ponto de partida
   * @param destination ponto de chegada
   * @param autonomy autonomia do veiculo  
   * @param fuel valor do combustivel
   * @return um objeto CustoMinimoResponseJSON com a melhor rota e o custo.
   */
  @Produces(MediaType.APPLICATION_JSON)
  @GET
  public Response getCusto(
      @QueryParam("mapName") String mapName,
      @QueryParam("origin") String origin,
      @QueryParam("destination") String destination,
      @QueryParam("autonomy") double autonomy,
      @QueryParam("fuel") double fuel) {
    MinCostResponseJSON response = new MinCostResponseJSON();
    try {
      if (fuel == 0 || autonomy == 0) {
        return Response.status(Status.BAD_REQUEST).build();
      }
      
      MapEntity map = mapService.findMapByName(mapName);
      CalculateDistanceUtil calculateDistance = new CalculateDistanceUtil(map);
      calculateDistance.execute(origin);

      LinkedList<Vertex> track = calculateDistance.getRoute(destination);
      List<String> route = new ArrayList<String>(track.size());
      for (Vertex vertex : track) {
        route.add(vertex.getName());
      }
      
      response.setRoute((String[])route.toArray(new String[0]));
      response.setCost((fuel / autonomy) * calculateDistance.getDistance(destination));
      
    } catch (BusinessException ex) {
    	return Response.status(Status.NOT_FOUND).entity("MAPA: "+ex.getMessage()).build();
    } catch (CalculateDistanceException ex) {
    	return Response.status(Status.INTERNAL_SERVER_ERROR).build();
    }
    
    return Response.status(Status.OK).entity(response).build();
  }
}
