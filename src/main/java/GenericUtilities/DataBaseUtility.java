package GenericUtilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DataBaseUtility {

	
		/**
		 * This method will connect to the DataBase
		 * @param url
		 * @param userName
		 * @param password
		 * @return
		 * @throws SQLException
		 */
		public Connection connectToDataBase(String url, String userName, String password) throws SQLException {
			
			Connection conn = DriverManager.getConnection(url, userName, password);
			System.out.println("***** Connected to DataBase *****");
			return conn;
		
	}
		
		/**
		 * This method will disconnected to the DataBase
		 * @param conn
		 * @throws SQLException
		 */
		public void closeConnectionToDataBase(Connection conn) throws SQLException {
			conn.close();
			System.out.println("***** Connection closed to DataBase *****");
		}
		
		public ResultSet getdataFromDataBase(Connection conn, String tableName) throws SQLException {
			
			Statement statement = conn.createStatement();
			ResultSet resultSet ;
			try {
				 resultSet = statement.executeQuery("select * from "+tableName);
			}catch(Exception e) {
				throw new RuntimeException("Failed to read data from DB", e);
			}
			return  resultSet;
		}
		
		/**
		 * This method will return the value based on the condition
		 * @param conn
		 * @param condition
		 * @return
		 * @throws SQLException
		 */
		public String getValueFromDataBaseUsingCondition(Connection conn, String condition) throws SQLException {
			
			Statement statement = conn.createStatement();
			ResultSet resultSet ;
			try {
				 resultSet = statement.executeQuery(condition);
			}catch(Exception e) {
				throw new RuntimeException("Failed to read data from DB", e);
			}
			return  resultSet.toString();
			
		}
		
		/**
		 * This method will convert the ResultSet to map based on query
		 * @param conn
		 * @param query
		 * @return
		 */
		public 	Map<String, Object> getDataAsMapInDataBase(Connection conn, String query){
			Map<String, Object> rowMap = null;
			try (
			        PreparedStatement ps = conn.prepareStatement(query);
			        ResultSet resultSet = ps.executeQuery()
			    ) {
			        ResultSetMetaData meta = resultSet.getMetaData();
			        int columnCount = meta.getColumnCount();

			        if (resultSet.next()) {
			            rowMap = new LinkedHashMap<>();  // preserves column order
			            for (int i = 1; i <= columnCount; i++) {
			                String columnName = meta.getColumnName(i);
			                Object value = resultSet.getObject(i);
			                rowMap.put(columnName, value);
			            }
			        }

			    } catch (Exception e) {
			        throw new RuntimeException("Failed to execute query: " + query, e);
			    }

			    return rowMap;  // null if no matching row
		}
	

		/**
		 * This method will return the value from map based on key
		 * @param map
		 * @param key
		 * @return
		 */
		public Object getvalueFromDataBaseMap(Map map, String key) {
			
			return map.get(key);
		}
		
		/**
		 * This method will delete the record based on the condition and returns the boolean
		 * @param conn
		 * @param tableName
		 * @param condition
		 * @return
		 * @throws SQLException
		 */
		public boolean deleteDataFromDataBase(Connection conn, String tableName,String condition) throws SQLException {
			try {
				conn.createStatement().execute("DELETE FROM "+tableName+" "+ condition + ";");
			}catch(Exception e) {
				return false;
			};
			
			return true;
			
		}
		
		/**
		 * This method will return the template of table
		 * @param conn
		 * @param tableName
		 * @return
		 */
		public Map<String, String> getTableMetadata(Connection conn, String tableName) {
		    Map<String, String> metadataMap = new LinkedHashMap<>();

		    String query = "SELECT * FROM " + tableName + " LIMIT 1"; 
		    // We just need metadata, not actual data

		    try (PreparedStatement ps = conn.prepareStatement(query);
		         ResultSet rs = ps.executeQuery()) {

		        ResultSetMetaData meta = rs.getMetaData();
		        int columnCount = meta.getColumnCount();

		        for (int i = 1; i <= columnCount; i++) {
		            String columnName = meta.getColumnName(i);
		            String columnType = meta.getColumnTypeName(i);
		            metadataMap.put(columnName, columnType);
		        }

		    } catch (Exception e) {
		        throw new RuntimeException("Failed to read metadata for " + tableName, e);
		    }

		    return metadataMap;
		}

		/**
		 * This method will help to insert the data into DataBase with help of query
		 * @param conn
		 * @param query
		 * @return
		 */
		public boolean insertIntoTable(Connection conn,String query) {
		    try {
		    	
		    	Statement statement = conn.createStatement();
		    	statement.execute(query);
		    } catch (Exception e) {
		    	return false;
		    }
		    return true;
		}

		

}
