/**
 * 
 */
package com.walmart.rest;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import com.walmart.business.MapService;
import com.walmart.model.MapEntity;
import com.walmart.model.RouteMapEntity;
import com.walmart.rest.json.MapJSON;

/**
 * Classe que expoe o serviço de cadastro de Malha Logistica.
 */
@ApplicationScoped
@Path("/insertNetwork")
public class LogisticNetworkRest {
  
  @Inject
  private MapService mapService;
  
  /**
   * Servico responsavel por cadastrar os dados da malha logistica
   * 
   * @param map um objeto do tipo {@link MapJSON} com o nome do mapa e os dados
   *  da malha logistica, origem, destino e distancia.
   */
  @Consumes(MediaType.APPLICATION_JSON)
  @POST
  public void insert(MapJSON map) {
    List<RouteMapEntity> routes = new ArrayList<RouteMapEntity>();
    for (String[] line : map.getNetwork()) {
      try {
        String origin = line[0];
        String destination = line [1];
        double distance = Double.valueOf(line[2]);
        routes.add(new RouteMapEntity(origin, destination, distance));
      } catch (NumberFormatException ex) {
        // TODO tratar erro 
        return;
      }
    }
    mapService.persistMap(new MapEntity(map.getName(), routes));
  }

}
