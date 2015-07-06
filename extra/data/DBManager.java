/**
 * Created on | 13 Nov 2009
 * Author     | Prabhjot Singh Lamba
**/

package data;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import constants.Constants;
import exception.DBException;
import exception.DataException;

/**
 * The DBManager component has the ability to connect to the DB given an application context, 
 * and provides DB related functionalities.
**/  
public class  DBManager
{
	private static Log logger = LogFactory.getLog(DBManager.class);

	Connection con = null;
	static 
	{
		try 
		{
			//DriverManager.registerDriver (new oracle.jdbc.OracleDriver());
			
			
			logger.info("database driver loading");
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			//DriverManager.registerDriver ((Driver)Class.forName("com.sybase.jdbc3.jdbc.SybDriver").newInstance());
			logger.info("database driver loaded");
            
		}
		catch (SQLException e)
		{
			logger.error("Got SQL Exception in database driver loading ");
			e.printStackTrace();
		} 
		catch(Exception e){
			logger.error("Got Exception in database driver loading ");
			e.printStackTrace();
		}
	}

	
 	// This is the default constructor for DBManager
	private DBManager()
	{}

	/**
 	 * This method commit the changes in the database
     * @exception com.tcs.exception.DBException This exception is thrown when there is any failure in commiting to the database.
	**/
	public void doCommit() throws DBException
	{
		try
		{
			con.commit();
		}
		catch(Exception e) 
		{
			logger.info("XX-> Error Encountered in Committing in DBManager : " + e.getMessage());
			throw new DBException(e.getMessage());
		}
	}

	/**
 	 * This method is used to make database connection through DataAdapter
 	 * @param context String The Application Context
 	 * @exception com.tcs.exception.DBException This is thrown when there in any failure in DB Connection
 	**/ 
	public void doConnect() throws DBException
	{
		try
		{
		
			
			con = DriverManager.getConnection(Constants.DATA_SOURCE,Constants.DB_USER,Constants.DB_PASSWORD);
			con.setAutoCommit(false);
			System.out.println("MySQL DB Connected: ");
			
			
		}
		catch(Exception e)
		{
			logger.info("XX-> Error Encountered in Creating Connection in DBManage.doConnect : " + e.getMessage());
			throw new DBException(e.getMessage());
		}
	} 

	/**
 	 * Delete records in the database
 	 * @param sql String The SQL String
 	 * @exception com.tcs.exception.DataException This is thrown when there in any failure in DB insertion
 	 **/ 
	public void doDelete(String sql) throws DataException
	{
		Statement stmt=null;
		try
		{
			stmt = con.createStatement();		
			stmt.execute(sql);
		}
		catch (SQLException se)
		{
			logger.info("XX-> Error Encountered in doDelete in DBManager : " + se.getMessage());
			throw new DataException(se.getMessage());
		}
	}


	/**
 	 * This method is used to disconnect from the database
 	 * @exception com.tcs.exception.DBException This is thrown when there in any failure in closing DB Connection
 	**/ 
	public void doDisconnect() throws DBException
	{
		try
		{
			if(null != con)
			{
				con.close();
			}
		}
		catch(Exception e)
		{
			//PrabhjotSinghLamba
			logger.info("XX-> Error Encountered in doDisconnect in DBManager : " + e.getMessage());
			throw new DBException(e.getMessage());
		}
	}

	/**
 	 * Inserts record in the database
 	 * @param sql String The SQL String
 	 * @exception com.tcs.exception.DataException  This exception is thrown when there is any failure in DB insertion
 	**/ 
	public void doInsert(String sql) throws DataException
	{
		Statement stmt=null;
		try
		{
			
			stmt = con.createStatement();		
			stmt.execute(sql);
			stmt.close();
			logger.info("inserted"+sql);
		}
		catch (SQLException se)
		{
			logger.info("XX-> Error Encountered in doInsert in DBManager : " + se.getMessage());
			throw new DataException(se.getMessage());
		}
	}
	
	
	/**
 	 * Execute Procedures in the database (Manish Samuel)
 	 * @param sql String The SQL String
 	 * @exception com.tcs.exception.DataException  This exception is thrown when there is any failure in DB insertion
 	**/ 
	public boolean execProcedure(String sql) throws DataException
	{		
		CallableStatement cs;
		boolean result;

		try
		{
		    cs = con.prepareCall(sql);
		    result=cs.execute();		   
			logger.info("Executed"+sql);
		}
		catch (SQLException se)
		{
			logger.info("XX-> Error Encountered in execProcedure in DBManager : " + se.getMessage());
			throw new DataException(se.getMessage());
		}
		return result;
	}
	
	

	/**
 	 * This method rolls back the database transaction
 	 * @exception com.tcs.exception.DBException This exception is thrown when rollback fails
 	**/ 
	public void doRollBack() throws DBException 
	{
		try
		{
			con.rollback();
		}
		catch(Exception e)
		{
			logger.info("XX-> Error Encountered in doRollback in DBManager : " + e.getMessage());
			throw new DBException(e.getMessage());
		}
	}

	/**
 	 * Updates record in the database
 	 * @param sql String The SQL String
 	 * @exception com.gec.exception.DataException Thrown when updation fails
 	**/ 
	public void doUpdate(String sql) throws DataException
	{
		Statement stmt=null;
		try
		{
			stmt = con.createStatement();
			logger.info("reached inside doUpdate");
			stmt.executeUpdate(sql);
			stmt.close();
		}
		catch (SQLException se)
		{
			logger.info("XX-> Error Encountered in doUpdate in DBManager : " + se.getMessage());
			throw new DataException(se.getMessage());
		}
	}
	
	/**
 	 * This method returns the results of SQL statement in the form of DataSet.
 	 * @return com.tcs.data.DataSet Contains retrieved data
 	 * @param sql String The SQL string
 	 * @exception com.tcs.exception.DataException Thrown if any problem occurs
 	**/ 
	public DataSet getDataSetFromSQL(String sql) throws DataException
	{
		ResultSet rs = null;	
		Statement stmt=null;
		DataSet ds = null;
		Vector vCol=new Vector();
		try
		{
			stmt = con.createStatement();		
			rs = stmt.executeQuery(sql);
			try
			{
				//logger.info("Fetched Row Size : " + rs.getFetchSize());
				ds = toDataSet(rs);
			}
			catch (DataException de)
			{
				de.printStackTrace();
				logger.info("getDataSetFromSQL errror... cannot convert to dataset" + ds);
				throw de;
			}
			finally
			{	
				rs.close();
				stmt.close();
			}
		}
		catch (SQLException se)
		{
			logger.info("XX-> Error Encountered in getDataSetFromSQL in DBManager : " + se.getMessage());
			se.printStackTrace();
			throw new DataException("Error_executing_SQL"+se.getMessage());
		}
		return ds;
	}
	
	/**
 	 * This method converts a ResultSet to a DataSet.
 	 * @return com.tcs.data.DataSet The resulting DataSet
 	 * @param rs java.sql.ResultSet
 	 * @exception com.tcs.exception.DataException Thrown if any problem occurs
 	**/ 
	protected DataSet toDataSet(ResultSet rs) throws DataException 
	{
		ResultSetMetaData meta=null;
		Statement stmt=null;
		DataSet ds = new DataSet();
		Vector vCol= null;
		try
		{
			meta = rs.getMetaData();
			// the column names..
			int size = meta.getColumnCount();
			vCol = new Vector(size);
			for (int ctr=0;ctr<size;ctr++ )
			{
				try
				{		
					//logger.info(meta.getColumnName(ctr+1));
					vCol.addElement(meta.getColumnName(ctr+1));
				}
				catch(SQLException se)
				{   
					se.printStackTrace();
					throw new DataException("Error_extracting_column_na"+se.getMessage());
				}
			}
		
		}
		catch(SQLException se)
		{
			se.printStackTrace();
			throw new DataException("Error_extracting_meta_data"+se.getMessage());
		}

		ds.setColNames(vCol);
	
		// now the data
		try
		{
			Vector vRow = null;
			int size = vCol.size();
			String temp = null;
			while(rs.next())
			{
				vRow = new Vector(size);
				for ( int ctrData=0;ctrData<size;ctrData++)
				{
					temp = (String)rs.getString((String)vCol.elementAt(ctrData));
					if(null==temp)
					{
						temp = new String();
					}
					vRow.addElement(temp);	
				}
				ds.setRow(vRow);
			}
		}
		catch(SQLException se)
		{
			se.printStackTrace();
			throw new DataException(se.getMessage());
		}
		return ds;
	}  

	/**
 	 * DBManager init method, returns a new instance of the DBManager.
	 * @return com.tcs.data.DBManager
 	**/ 
	public static DBManager init() 
	{
		return new  DBManager();
	}
}