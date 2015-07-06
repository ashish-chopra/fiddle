package contentModel;

import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import util.StringUtil;
import valueObject.MarkersVO;
import data.DBManager;
import data.DataSet;
import exception.DataException;

public class MarkersCM {
	
	private static Log logger = LogFactory.getLog(MarkersCM.class);

	private DBManager db;
	
	public MarkersCM(DBManager db)
	{		
		this.db = db;		
	}

	public ArrayList<MarkersVO> getAllMarkersData() throws DataException {
		
		logger.info("Inside getAllMarkersData method ");
		ArrayList<MarkersVO> list = new ArrayList<MarkersVO>();
		
		
		String query = 	" SELECT id, member_name, travel_title, travel_experience, visited_location, travel_time, location_latitude, location_longitude " +
						" FROM teesra_parivar_travel_details ";
		
		logger.info("Query : "+query);
		DataSet ds = null;
		try{
			ds = db.getDataSetFromSQL(query);
		}
		catch(Exception e){
			logger.error("Exception while fetching data from database: \n"+e);
		}
		
		if(ds != null && ds.getRows().size() >0){
			
			logger.info("No. of rows in result : "+ds.getRows().size());
			
			for(int i=0; i<ds.getRows().size(); i++){
				
				MarkersVO marker = new MarkersVO();
				if(!StringUtil.isEmpty(ds.getValueAt(i, 0))){
					marker.setId(Integer.parseInt(ds.getValueAt(i, 0).toString()));
				}
				if(!StringUtil.isEmpty(ds.getValueAt(i, 1))){
					marker.setUser(ds.getValueAt(i, 1).toString());
				}
				if(!StringUtil.isEmpty(ds.getValueAt(i, 2))){
					marker.setTitle(ds.getValueAt(i, 2).toString());
				}
				if(!StringUtil.isEmpty(ds.getValueAt(i, 3))){
					marker.setExperience(ds.getValueAt(i, 3).toString());
				}
				if(!StringUtil.isEmpty(ds.getValueAt(i, 4))){
					marker.setLocation(ds.getValueAt(i, 4).toString());
				}
				if(!StringUtil.isEmpty(ds.getValueAt(i, 5))){
					marker.setTimeOfVisit(ds.getValueAt(i, 5).toString());
				}
				if(!StringUtil.isEmpty(ds.getValueAt(i, 6))){
					marker.setLatitude(ds.getValueAt(i, 6).toString());
				}
				if(!StringUtil.isEmpty(ds.getValueAt(i, 7))){
					marker.setLongitude(ds.getValueAt(i, 7).toString());
				}
				
				logger.info(marker);
				list.add(marker);
			}
		}
		
		logger.info("no of rows returing from getAllMarkersData function : "+list.size());
		return list;
	}
}
