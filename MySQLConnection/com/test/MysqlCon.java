package com.test;
import java.sql.*;  
class MysqlCon
{  
	public static void main(String args[]){
		
	try{  
	Class.forName("com.mysql.jdbc.Driver");  
	Connection con=DriverManager.getConnection(  
	"jdbc:mysql://mysqlserver:3306/mydb","mydb","mydb");  
	//here sonoo is database name, root is username and password  

	DatabaseMetaData md = con.getMetaData();
	ResultSet rs = md.getTables(null, null, "%", null);
	while (rs.next()) {
	  //System.out.println(rs.getString(1)+"--"+rs.getString(2)+"--"+rs.getString(3));
	}

	Statement stmt=con.createStatement();  
	ResultSet rs1=stmt.executeQuery("select id,application_name,timestamp,response_time,username,user_roles,url_accessed,request_type,dashboard_name,widgets,filters,params,source_type,source_name,client_ip_address,date_created from user_audit_logs"); 
	System.out.println("id|application_name|dashboard_name|username|user_roles|client_ip_address|params|widgets|filters|date_created");
	while(rs1.next())  
	System.out.println(rs1.getInt("id")+"|"+rs1.getString("application_name")+"|"+rs1.getString("dashboard_name")+"|"+rs1.getString("username")+"|"+rs1.getString("user_roles")+"|"+rs1.getString("client_ip_address")+"|"+rs1.getString("params")+"|"+rs1.getString("widgets")+"|"+rs1.getString("filters")+"|"+rs1.getTimestamp("date_created"));  
	con.close();  

	}catch(Exception e){ System.out.println(e);}  

	}

	
}  