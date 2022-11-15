import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JDBC_Programs {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		Class.forName("com.mysql.jdbc.Driver");
		String database = "jdbc:mysql://localhost:3306/JDBC_Connectivity";
		String user = "root";
		String password = "Mohit";
		
		Connection connection = DriverManager.getConnection(database, user, password);
		Statement statement = connection.createStatement();
		
		Scanner Sc = new Scanner(System.in);
		
		int choice = 0;
		
		do {
			System.out.println();
			System.out.println("------------Menu ----------------");
			System.out.println("1. Insert into Table");
			System.out.println("2. Delete all rows from table");
			System.out.println("3. Show records ");
			System.out.println("4. Delete specific row from table");
			System.out.println("5. Exit");
			System.out.println("---------------- ----------------");
			System.out.println();
			
			System.out.print("Enter your choice : ");
			choice = Sc.nextInt();
			
			switch(choice) {
				case 1:
					System.out.println("Insert INTO table  ");
					
					System.out.print("Enter id : ");
					int id = Sc.nextInt();
					
					System.out.print("Enter name : ");
					String name = Sc.next();
					
					System.out.print("Enter salary : ");
					int salary = Sc.nextInt();
					
					System.out.print("Enter department name : ");
					String department = Sc.next();
					
					String command = String.format("INSERT INTO Employee VALUES "
							+ "( %d, '%s', %d, '%s' )", id, name, salary, department);
					
					statement.executeUpdate(command);
					System.out.println("Record Inserted Successfully");
					
					break;
				
			//---------------------------------------------------------------	
					
				case 2:
					System.out.println("Deleting  all records from table");
					
					command = "DELETE FROM Employee";
					statement.executeUpdate(command);
					
					System.out.println("All Records Deleted Successfully");
					
					break;
					
			//---------------------------------------------------------------
					
				case 3:
					System.out.println("Displaying all records ");
					
					command="SELECT * from Employee"; 
					ResultSet resultSet =statement.executeQuery(command);

					String heading = String.format("%-10s | %-10s | %-10s | %-10s |","id","name","salary","department");
					int numberOfRecords = 0;
					
					//-------------------------------------------------------------------
					System.out.println("------------------------------------------------- |");
					System.out.println(heading);
					System.out.println("------------------------------------------------- |");
					
					while(resultSet.next())
					{
						numberOfRecords++;
						id=resultSet.getInt("id");
						name= resultSet.getString("name");
						salary= resultSet.getInt("salary");
						department = resultSet.getString("department");
						
		
					      String s = String.format("%-10s | %-10s | %-10s | %-10s |",id, name, salary, department);
					      System.out.println(s);
					}
					
					System.out.println("------------------------------------------------- |");
					System.out.println("Total Number of Records are : " + numberOfRecords);
			
			//-------------------------------------------------------------------
					
					resultSet.close();
					break;
			//-------------------------------------------------------------------
				case 4:
					System.out.println("Deleting A Record");
					
					System.out.print("Enter the id corresponding to record you want to delete : ");
					int idToDelete = Sc.nextInt();
					
					command = "DELETE FROM Employee WHERE id = " + idToDelete;
					statement.executeUpdate(command);
					
					System.out.println("Record deleted if id corresponding to a record exists");
					
					break;
			//------------------------------------------------------------------
				case 5:
					System.out.println("Bye");
					choice = -1;
					break;

				default:
					System.out.println("Please enter correct choice !! ");
			}
			
		}while(choice != -1);
		
		
		Sc.close();
		statement.close();
		connection.close();
	}
}