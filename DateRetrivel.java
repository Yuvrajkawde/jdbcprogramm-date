package in.ineuron.dynamicinput;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import in.ineuron.jdbcUtil.JDBCUtil;

public class DateRetrivel {

	public static void main(String[] args) 
	{
		


		// resource used
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		Scanner scanner = new Scanner(System.in);

		System.out.print("Enter the name:: ");
		String name = scanner.next();

		String sqlSelectQuery = "select name,addr,gender,dob,doj,dom from userdata where name = ?";

		try {

			connection = JDBCUtil.getJdbcConnection();

			if (connection != null)
				pstmt = connection.prepareStatement(sqlSelectQuery);

			if (pstmt != null) {
				pstmt.setString(1, name);
				resultSet = pstmt.executeQuery();
			}
			if (resultSet != null) {

				if (resultSet.next()) {
					
					String userName = resultSet.getString(1);
					String addr = resultSet.getString(2);
					String gender = resultSet.getString(3);
					java.sql.Date userDob = resultSet.getDate(4);
					java.sql.Date userDoj = resultSet.getDate(5);
					java.sql.Date userDom = resultSet.getDate(6);
					System.out.println("SQLDate present in database is :: "+userDob);
					// formatting the output as the user choice(based on locale)
					SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
					String date = sdf.format(userDob);
					SimpleDateFormat sdf1 = new SimpleDateFormat("MM-dd-yyyy");
					String date1 = sdf1.format(userDoj);
					SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-dd-MM");
					String date2 = sdf2.format(userDom);
					System.out.println("NAME IS :: " + userName);
					System.out.println("ADDRESS IS:: "+ addr);
					System.out.println("GENDER IS:: "+ gender);
					System.out.println("DOB  IS :: " + date);
					System.out.println("DOJ  IS :: " + date1);
					System.out.println("DOM  IS :: " + date2);
				
				} else {
					System.out.println("Record not available for the given name: " + name);
				}

			}

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				JDBCUtil.closeConnection(resultSet, pstmt, connection);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (scanner != null) {
				scanner.close();
			}
		}


	}

}
