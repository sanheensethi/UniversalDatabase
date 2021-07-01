import java.sql.*;
import java.util.*;
import universal.database.DatabaseMySql;
class MySql{
	public static void main(String[] args){
		
		DatabaseMySql sql = new DatabaseMySql("localhost:3306","University","root","sanheen");
		String query = "";

		ResultSet rs1 = sql.select().from("students").srun();
		query = sql.getQuery();
		System.out.println("Query : "+query);

		ResultSet rs2 = sql.select("id").from("students").srun();
		query = sql.getQuery();
		System.out.println("Query : "+query);

		ResultSet rs3 = sql.select("name").from("students").srun();
		query = sql.getQuery();
		System.out.println("Query : "+query);

		ResultSet rs4 = sql.select("age").from("students").srun();
		query = sql.getQuery();
		System.out.println("Query : "+query);

		ResultSet rs5 = sql.select("id","age").from("students").srun();
		query = sql.getQuery();
		System.out.println("Query : "+query);

		ResultSet rs6 = sql.select("id","name").from("students").srun();
		query = sql.getQuery();
		System.out.println("Query : "+query);
		
		ResultSet rs = rs6;
		try{
			while(rs.next()){
				int id = rs.getInt(1);
				String name = rs.getString(2);
				//int age = rs1.getInt("age");
				System.out.print(id);
				System.out.print(" | ");
				System.out.print(name);
				//System.out.print(" | ");
				//System.out.print(age);
				System.out.println();
			}
		}catch(SQLException e){
			System.out.println(e);
		}
	}
}