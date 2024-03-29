package student_op;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class student_op {
	 public static String driverName="org.apache.hive.jdbc.HiveDriver";
	  public static String url="jdbc:hive2://192.168.217.200:10000/default";
	  public static String userName="root";
	  public static String password="123456";
	  public static String sql="";
	  public static ResultSet res;
	  public static void main(String[] args) throws ClassNotFoundException, SQLException {
			// TODO Auto-generated method stub
			Class.forName(driverName);
			Connection conn=DriverManager.getConnection(url, userName,password);
			Statement stmt=conn.createStatement();
			String tableName="student1";
			String sql="create table "+tableName+"(id int,name string) row format delimited fields terminated by ',";
			stmt.execute(sql);
			//查看当前数据库中的所有表
			sql="show tables";
			res=stmt.executeQuery(sql);
			while(res.next()) {
				System.out.println(res.getString(1));
			}
			//查看student1表的详细信息
			sql="desc +tableName";
			res=stmt.executeQuery(sql);
			System.out.println("fieldsname"+"\t"+"datatype");
			while(res.next()) {
				System.out.println(res.getString(1)+"\t"+res.getString(2));
			}
			//加载数据到表student1
			sql="load data local inpath '/student.txt' into table student1";
			stmt.execute(sql);
			
			//查询student1表中的所有数据
			sql="select * from"+tableName;
			res=stmt.executeQuery(sql);
			while(res.next()) {
				System.out.println(res.getString(1)+"\t"+res.getString(2));
				}
			//查询studet1表的记录的行数
			sql="select count(*) from "+tableName;
			res=stmt.executeQuery(sql);
			while(res.next()) {
				System.out.println(res.getInt(1));
			}
			//删除student1表
			sql="drop table "+tableName;
			 
			}
}
