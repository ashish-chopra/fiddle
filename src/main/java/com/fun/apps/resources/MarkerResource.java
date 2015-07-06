package com.fun.apps.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fun.apps.model.Marker;
import com.fun.apps.service.MarkerService;

@Path("/markers")
public class MarkerResource {

	private MarkerService service = new MarkerService();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Marker> getAllMarkers() {
		return service.getAllMarkers();
	}
}
