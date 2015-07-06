package data;

import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import exception.ColumnNotFoundException;
import exception.NoDataException;

public class DataSet implements java.io.Serializable
{
	private static final long serialVersionUID = -8251293714790352696L;
	private static Log logger = LogFactory.getLog(DataSet.class);
	private Vector header = null;
	private Vector rows = null;
	private String name = null;


	// DataSet constructor
	public DataSet()
	{
		rows = new Vector();
	}

	/**
 	* Creates a DataSet with specified number of rows
 	* @param numRows int
 	*/
	public DataSet(int numRows)
	{
		rows = new Vector(numRows);
	}

	/**
 	 * Returns the column index of the specified column name in the header if found else -1
	 * @param colName String Column name
 	 * @return int Column Index for the input Column Name
 	**/
	public int getColIndex(String colName)
	{
		int count = 0;
		int size = header.size();
		while(count < size)
		{
			if(header.elementAt(count).equals(colName.toUpperCase()))	return count;
			count++;
		}
		return -1;
	} 

	/**
 	 * Returns the header of the DataSet
 	 * @return Vector Header containing Column names
 	**/
	public Vector getColNames()
	{
		return header;
	}

	/**
 	 * Gets name of DataSet
 	 * @return String DataSet name
 	**/
	public String getName()
	{
		return this.name;
	}

	/**
 	 * Returns a row given a row number
	 * @param row int The row index
 	 * @return java.util.Vector Containing row elements
 	 * @exception NoDataException If row does not exists
 	**/
	public Vector getRowAt(int row) throws NoDataException
	{
		Vector allRows = getRows();
		if(allRows.size() <= row)
			throw new NoDataException();
		else
			return (Vector)allRows.elementAt(row);
	}

	/**
 	 * Returns all rows within the DataSet
	 * @return java.util.Vector
 	 * @exception com.gec.exception.DataException The exception description.
 	**/
	public final Vector getRows() throws NoDataException
	{
		return rows;
	}

	/**
 	 * Returns all the row elements within specified column
	 * @param colName java.lang.String
 	 * @return Vector
 	 * @exception NoDataException If row is size of 0
 	 * @exception ColumnNotFoundException If column does not exists
 	**/
	public Vector getRowsForCol(String colName) throws NoDataException, ColumnNotFoundException
	{
		Vector resultData=null;
		//If No Rows Present.
		if(0==rows.size())
		{
			throw new NoDataException();
		}
		int colIndex = header.indexOf(colName.toUpperCase());
		if ( -1 == colIndex )
		{
			throw new ColumnNotFoundException();
		}
		else
		{
			// declare the container to hold the data subset
			resultData = new Vector(rows.size());
			// extract the data from the rows and the append it onto the resultData var..!
			int size = rows.size();
			Vector singleRow = null;
			Object  temp = null;
			for (int rCount=0; rCount < size; rCount++)
			{
				singleRow = (Vector)(rows.elementAt(rCount));
				temp = (Object)singleRow.elementAt(colIndex);
				resultData.addElement(temp); 
			}
		}
		return resultData;
	}
	
	/**
 	 * Returns the cell value given row number and column name if found else null.
	 * @param row int
 	 * @param col int
 	 * @return Object
 	 * @exception NoDataException Access of invalid index
 	**/ 

	public Object getValueAt(int row , int col) throws NoDataException
	{
		Vector allRows = getRows();
		if(allRows.size() <= row)
		{
			logger.info("XX->NoDataException ColName : " + header.elementAt(col) + "Row : " + row);
			throw new NoDataException("Trying to access invalid index");
		}
		else
		{
			Vector v = (Vector)allRows.elementAt(row);
			if(v.size() <= col)
			{
				logger.info("XX->NoDataException ColName : " + header.elementAt(col) + "Row : " + row);
				throw new NoDataException("Trying to access invalid index");
			}
			else
				return v.elementAt(col);
		}
	}

	/**
 	 * Returns the cell value given row number and column name if found else null.
 	 * @param row int
 	 * @param colName java.lang.String
 	 * @return Object
 	 * @exception NoDataException Access of invalid index
 	**/ 
	public Object getValueAt(int row , String colName) throws NoDataException
	{
		int colIndex = getColIndex(colName);
		if(colIndex >= 0)
		{
			return getValueAt(row,colIndex);
		}
		return null;
	}

	/**
 	 * Removes a row given the row number
 	 * @param index int
  	**/ 

	public void removeRowAt(int index)
	{
		rows.removeElementAt(index);
	}

	/**
 	 * Sets the header of the DataSet
 	 * @param colNames Vector
 	 **/	

	public void setColNames (Vector colNames)
	{
		Vector vTemp = new Vector(colNames.size());
		int size = colNames.size();
		String temp = null;
		for (int i =0; i < size; i++ )
		{
			temp = (String)colNames.elementAt(i);
			vTemp.addElement(temp.toUpperCase());
		}
		header=vTemp;
	}

	/**
 	 * Sets the name of the DataSet
	 * @param name java.lang.String
 	**/
	public void setName(String name) 
	{
		this.name=name;
	}

	/**
 	 * Adds a row to the DataSet
 	 * @param row java.util.Vector
 	**/
	public void setRow(Vector row)
	{
		rows.addElement(row);
	}

	/**
 	 * Adds a row to the DataSet given an index
 	 * @param row int
 	 * @param row java.util.Vector
 	**/
	public void setRowAt(int row , Vector v) throws NoDataException
	{
		Vector allRows = getRows();
		if(allRows.size() <= row)
			throw new NoDataException();
		else
			allRows.setElementAt(v,row);
	}

	/**
 	 * Sets the number of rows for the DataSet
 	 * @param numRows int
 	**/
	public void setRows(int numRows)
	{
		rows.setSize(numRows);
	}

	/**
 	 * Sets a value given a row and col number
 	 * @param row int
 	 * @param col int
 	 * @param value Object
 	 * @exception NoDataException Accessing invalid index
 	**/	
	public void setValueAt(int row , int col , Object value) throws NoDataException
	{
		Vector allRows = getRows();
		if(allRows.size() <= row)
			throw new NoDataException("Trying to access invalid index");
		else
		{
			Vector v = (Vector)allRows.elementAt(row);
			if(v.size() <= col)
				throw new NoDataException("Trying to access invalid index");
			else
				v.setElementAt(value,col);
		}
	}

	/**
	 * Sets the value of cell specified by row index and column name by object that is passed as an input.
	 * @param row - int representing row index of the cell
	 * @param colName - String representing column name.
	 * @param value - java.lang.Object representing value to be set at specified cell.
	 * @exception NoDataException - is thrown while trying to access invalid row index.
	 * @exception ColumnNotFoundException  - is thrown if specified column does not exist in the DataSet.
	**/
	public void setValueAt(int row , String colName  , Object value) throws ColumnNotFoundException,NoDataException
	{
		int colIndex;
		if( ! header.contains(colName.toUpperCase()) )
			throw new ColumnNotFoundException("Specified Column Does Not Exist");
		else
			colIndex = header.indexOf(colName.toUpperCase());

		setValueAt(row , colIndex , value);
		
	}
}