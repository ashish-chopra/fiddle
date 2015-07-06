package com.fun.apps.dao;

import java.util.List;

import com.fun.apps.model.Marker;

public abstract class MarkerDAO {
	
	public abstract List<Marker> findAll();
	public abstract boolean insertMarker(Marker marker);
	public abstract boolean updateMarker(Marker marker);
	public abstract boolean deleteMarker(Marker marker);

}
