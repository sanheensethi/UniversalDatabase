package universal.database;
import java.sql.*;
import java.util.*;
public class DatabaseMySql{
	private String driver = "mysql";
	private String db = "";
	private String query = "";
	private String lastQuery = "";
	private String table = "";
	private String where = "";
	private ArrayList<String> columns = new ArrayList<String>();
	private Connection con = null;
	private ResultSet rs = null;
	private Statement stmt = null;
	private int whereFlag = 0;

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

	public DatabaseMySql where(String ... where){
		if(where.length > 1){
			int i=0;
			for(;i<where.length-1;i++){
				this.where = this.where+where[i]+" AND ";
			}
			this.where = this.where + where[i];
		}else{
			this.where = this.where + where[0];
		}
		this.whereFlag = 1;
		return this;
	}

	/*public DatabaseMySql setVales(ArrayList<Object> values){
		for(int i=0;i<=values.size();i++){
			
		}
	}*/

	public PreparedStatement pInsert(String table,int unknowns,String ... ids){
		this.query = "INSERT INTO "+table;
		if(ids.length == 0){
			this.query = this.query+" VALUES (";
		}else{
			int i=0;
			this.query = this.query + "(";
			for(;i<ids.length-1;i++){
				this.query = this.query+ids[i]+",";
			}
			this.query = this.query+ids[i]+") VALUES (";
		}

		for(int i=0;i<unknowns-1;i++){
			this.query = this.query+"?,";
		}
		this.query = this.query+"?)";
		this.lastQuery = this.query;
		try{
			return this.con.prepareStatement(this.query);
		}catch(SQLException e){
			System.out.println(e);
			return null;
		}
		/*
			Reference : 
			https://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
		*/
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
			this.lastQuery = this.query;
			this.query="";
			return this.rs;
		}catch(SQLException e){
			System.out.println(e);
			return rs;
		}
	}

	public String getQuery(){
		return this.lastQuery;
	}
	

}