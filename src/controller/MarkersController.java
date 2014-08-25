package controller;


import java.util.ArrayList;

import data.DBManager;
import exception.DBException;
import exception.DataException;

import contentModel.MarkersCM;
import valueObject.MarkersVO;

public class MarkersController {

	public ArrayList<MarkersVO> getAllMarkersData() throws DataException , DBException
	{
		DBManager db = DBManager.init();
		db.doConnect();
		MarkersCM markersCM = new MarkersCM(db);
		
		ArrayList<MarkersVO> list ;	
		
		try
		{
			list = markersCM.getAllMarkersData();
		}
		catch(DataException e)
		{
			throw e;
		}
		finally
		{
			try{
			db.doDisconnect();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return list;
	}
}
