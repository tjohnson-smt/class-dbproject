package myname;

import java.io.IOException;
import java.sql.*;
import javax.naming.*;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class StateList {
   // use an ArrayList because we don't know how many states there are
   private List<State> stateList = new ArrayList<State>();
   
   private String country_id = null;
	
   private DataSource ds;
   private Connection conn;
   private PreparedStatement prepStatement;
    
   public StateList() {
       try {
           // Get the DataSource from the context
           Context initContext  = new InitialContext();
           Context envContext  = (Context)initContext.lookup("java:/comp/env");
           ds = (DataSource)envContext.lookup("jdbc/fs");
       } catch (NamingException e) {
           e.printStackTrace();
       }
   }

   public List<State> getResults(int active, int country) throws IOException {
       ResultSet resultSet = null;
       
       try {
    	   StringBuilder sql = new StringBuilder();
    	   sql.append("SELECT * FROM states WHERE active = ? AND country_id = ?");
    	   
           // Get Connection and create the Prepared Statement
           conn = ds.getConnection();
           prepStatement = conn.prepareStatement(sql.toString());
           prepStatement.setInt(1, active);
           prepStatement.setInt(2, country);
           resultSet = prepStatement.executeQuery();
           
           // Create an ArrayList of states
           while (resultSet.next()) {
        	   State state = new State();
        	   state.setStateID(resultSet.getString(1));
        	   state.setStateCode(resultSet.getString(2));
        	   state.setStateName(resultSet.getString(3));
        	   
        	   stateList.add(state);
        	   
               //System.out.println(resultSet.getString(1) + resultSet.getString(2) + resultSet.getString(3));
           }
       } catch (SQLException e) {
           e.printStackTrace();
       } finally {
    	   // Close everything in reverse order
           try {
        	   if(null != resultSet) resultSet.close();
           } catch (SQLException e) {
        	   e.printStackTrace();
           }
           
           try {
        	   if(null != prepStatement) prepStatement.close();
           } catch (SQLException e) {
        	   e.printStackTrace();
           }
           
           try {
        	   if(null != conn) conn.close();
           } catch (SQLException e) {
        	   e.printStackTrace();
           }
       }
       
       return stateList;
   }

/**
 * @return the country_id
 */
public String getCountry_id() {
	return country_id;
}

/**
 * @param country_id the country_id to set
 */
public void setCountry_id(String country_id) {
	this.country_id = country_id;
}
}
