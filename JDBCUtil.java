


//assingment based on 26 nov 2022 lecture java version 17 database used mysql workbench 8.0 code is working on my machine
package in.ineuron.jdbcUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtil
{
		
	
	JDBCUtil()
		{
			
		}
	
		public static Connection getJdbcConnection() throws SQLException
		{
			Connection connection = null;

			// 2. Establish the Connection
			String url = "jdbc:mysql://localhost:3306/preparedstatement";
			String user = "root";
			String password = "Root,12345";

			connection = DriverManager.getConnection(url, user, password);
			if (connection != null)
				return connection;

			return connection;
		    
		}
		public static void closeConnection(ResultSet resultSet, Statement statement, Connection connection)
				throws SQLException 
		{

			// 6. closing the resources used
			if (resultSet != null) 
			{
				resultSet.close();
			}
			if (statement != null) 
			{
				statement.close();

			}
			if (connection != null) 
			{
				connection.close();
			}

		}

}




