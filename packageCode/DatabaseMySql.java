package universal.database;
import java.sql.*;
import java.util.*;
public class DatabaseMySql{
	private String driver = "mysql";
	private String db = "";
	private String query = "";
	private String table = "";
	private ArrayList<String> columns = new ArrayList<String>();
	private Connection con = null;
	private ResultSet rs = null;
	private Statement stmt = null;

	public DatabaseMySql(String url,String db,String user,String pass) {
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(Exception e){
			System.out.println(e);
		}

		try{
			this.con = DriverManager.getConnection("jdbc:mysql://"+url+"/"+db,user,pass);
		}catch(SQLException e){
			System.out.println("Error : "+e);
		}
	}

	public DatabaseMySql select(String ... columns){
		this.columns.clear();
		if(columns.length == 0){
			this.columns.add("*");
		}else{
			for(String col:columns){
				this.columns.add(col);
			}
		}
		return this;
	}

	public DatabaseMySql from(String table){
		this.table = table;
		return this;
	}

	public ResultSet srun(){
		this.query = "SELECT ";
		int i=0;
		for(;i<columns.size()-1;i++){
			this.query = this.query+this.columns.get(i)+",";
		}
		this.query = this.query+this.columns.get(i)+" ";
		this.query = this.query + "FROM ";
		this.query = this.query + this.table;
		try{
			this.stmt = this.con.createStatement();
			this.rs = this.stmt.executeQuery(this.query);
			return this.rs;
		}catch(SQLException e){
			System.out.println(e);
			return rs;
		}
	}

	public String getQuery(){
		return this.query;
	}
	

}