package com.fun.apps.service;

import java.util.List;

import com.fun.apps.model.Marker;

public class MarkerService {

	private static volatile MarkerService instance = null;
	private DBConnection connection;
	private MarkerDAO markerDAO;
	
	public MarkerService() {
		connection = DBConnection.getInstance();
	}
	
	public List<Marker> getAllMarkers() {
		return markerDAO.getAllMarkers();
	}

}
