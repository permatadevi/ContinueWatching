/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contwatch;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author USER
 */
@WebService(serviceName = "ContinueWatchingWs")
public class ContinueWatchingWs
{

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt)
    {
        return "Hello " + txt + " !";
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "displayContWatching")
    public String displayContWatching(@WebParam(name = "userid") String userid)
    {
        //TODO write your implementation code here:
         String result = null;
        //TODO write your implementation code here:
        try{  
        //step1 load the driver class  
    Class.forName("oracle.jdbc.driver.OracleDriver");  
  
    //step2 create  the connection object  
    Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:8825:rbtmcp","rbtdb","rbtdb");  

        if(con!=null)
        {
        System.out.println("OK");
        //PreparedStatement stmt=con.prepareStatement("select MDN, FEATURE_NAME from SUBS_INFO where rownum <3");  
        PreparedStatement stmt=con.prepareStatement("select  USER_ID,VIDEO_IDS from VID_USER_VIDEOS where USER_ID=? ");  
        stmt.setString(1, userid);
        ResultSet rs=stmt.executeQuery();  
        while(rs.next())
        {  
            result="user-id:"+rs.getString(1)+" ContWatchingVideoIds:"+rs.getString(2);
        }  
        }
        //step5 close the connection object  
        con.close();  

        }
        catch(Exception e)
        { System.out.println(e);
        }
        
        return result;
        
    }
}
