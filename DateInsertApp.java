package in.ineuron.dynamicinput;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import in.ineuron.jdbcUtil.JDBCUtil;

public class DateInsertApp 
{

	//private static Object SimpleDateFormate;

	public static void main(String[] args) throws ParseException //throws SQLException
	{
		Connection connection = null;
		PreparedStatement pstmt = null;
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("enter the name : ");
		String name = scanner.next();
		
		System.out.println("enter the address : ");
		String addr = scanner.next();
		
		System.out.println("enter the gender : ");
		String gender = scanner.next();
		
		System.out.println("enter the dob (dd-mm-yyyy) :");
		String sdob = scanner.next();
		
		//SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		//java.util.Date uDate = sdf.parse(sdob);
		

		
		
		System.out.println("enter the dojoining (mm-dd-yyyy) :");
		String sdoj = scanner.next();
		//SimpleDateFormat sdf1 = new SimpleDateFormat("MM-dd-yyyy");
		//java.util.Date uDate1 = sdf.parse(sdoj);
		
		
		
		System.out.println("enter the domarrig (yyyy-MM-dd) :");
		String sdom = scanner.next();
		//uDate=sdf.parse(sdom);
		
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
		java.util.Date udob = sdf1.parse(sdob);
		long time = udob.getTime();
		java.sql.Date sqldob = new java.sql.Date(time);
		
		//doj
		SimpleDateFormat sdf2 = new SimpleDateFormat("MM-dd-yyyy");
		java.util.Date udoj = sdf2.parse(sdoj);
		long time1 = udoj.getTime();
		java.sql.Date sqldoj = new java.sql.Date(time1);
		
		
		
		//dom
		java.sql.Date sqldom = java.sql.Date.valueOf(sdom);
		
		
		System.out.println("String name is  :: " + name);
		System.out.println("String addr is  :: " + addr);
		System.out.println("String gender is  :: " + gender);
		System.out.println("String dob is  :: " + sdob);
		System.out.println("String dob is  :: " + sdoj);
		System.out.println("String dob is  :: " + sdom);
		//System.out.println("Util date is   :: " + uDate);
		//System.out.println("SQL  date is   :: " + sqlDate);
		
		String sqlInsertQuery = "insert into userdata(name,addr,gender,dob,doj,dom) values (?,?,?,?,?,?)";//(`name`,`dob`,'doj','dom')
		try 					//String.format("insert into student(sname,sage,saddr) values ('%s',%d,'%s')",sname,sage,saddr);
		{
			connection = JDBCUtil.getJdbcConnection();
			if(connection!=null) 
			
				pstmt = connection.prepareStatement(sqlInsertQuery);
			
		

			if (pstmt != null) {
				pstmt.setString(1, name);
				pstmt.setString(2, addr);
				pstmt.setString(3, gender);
				pstmt.setDate(4, sqldob);
				pstmt.setDate(5, sqldoj);
				pstmt.setDate(6, sqldom);

				int rowAffected = pstmt.executeUpdate();

				System.out.println("No of rows affected is:: " + rowAffected);
			}
			
		}
		
		
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				JDBCUtil.closeConnection(null, pstmt, connection);
				if(scanner!=null) 
				{
					scanner.close();
				}
			}
			catch(SQLException e) 
			{
				e.printStackTrace();
			}
			if(scanner!=null) 
			{
				scanner.close();
			}
		}

	}

}
