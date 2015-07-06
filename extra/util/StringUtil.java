/**
* Created on | 5 Nov 2009
 * Author     | Prabhjot Singh Lamba
**/

package util;

public class StringUtil
{
	public static String getJSPString(String inputString)
	{
	   if(isEmpty(inputString))  return inputString;
	   String newString = inputString.replaceAll("<", "&lt;");
	   newString = newString.replaceAll(">","&gt;");
	   newString = newString.replaceAll("\"","&quot;");
	   newString = newString.replaceAll("&","&amp");
	   return newString;
	}

	public static String parseJSPString(String inputString)
	{
	   if(isEmpty(inputString))  return inputString;
	   String newString = inputString.replaceAll("&lt;","<");
	   newString = newString.replaceAll("&gt;",">");
	   newString = newString.replaceAll("&quot;","\"");
	   newString = newString.replaceAll("&amp","&");
	   return newString;
	}

	public static String getSQLString(String inputString)
	{
	    if(isEmpty(inputString))  return inputString;
		String newString = inputString.replaceAll("'","''");
		return newString;
	}

	public static String parseSQLString(String inputString)
	{
		if(isEmpty(inputString))  return inputString;
		String newString = inputString.replaceAll("''","'");
		return newString;
	}

	public static boolean isEmpty(Object inputString)
	{
		if(inputString==null || inputString.toString().length()==0) return true;
		else return false;
	}
}