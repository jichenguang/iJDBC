package QaJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import iReadText.readText;

public class CheckSql2 {

			static Logger log = Logger.getLogger(CheckSql2.class);
			//数据库的连接驱动，一般是不需要变的
		     final static String DRIVER="com.microsoft.sqlserver.jdbc.SQLServerDriver";
		    //数据库连接的URL,1433为默认的数据库端口号，700Store.Order为当前你要操作的数据库名
			 final static String URL="jdbc:sqlserver://192.168.188.11:1433;DatabaseName=700Store.Order";
			    //登录账户
			 final static String DBNAME="sa";
			    //登录的密码
			 final static String DBPASS="700Bike520";
			 
				     static Connection con=null;    
				     static ResultSet rs=null;
				     static Statement stmt = null;
				     static readText rt = new readText();
					 private static String iSQLSentence = rt.checkTestTxt();
			 
			 
		     /**
		      * 加载驱动
		      */
			 public static void testCheckSqlDriver(){
				 try{
						Class.forName(DRIVER);//加载驱动
					}catch(ClassNotFoundException event){
			//				System.out.print("无法创建驱动程式实体!");
							log.error("无法创建驱动程式实体!");
					}				 
			 }
			 
			 	/**
				 * 获取数据库连接
				 */
				public static Connection getConnection() {
					Connection con = null;
					log.debug("开始连接数据库");
					try{
						con=DriverManager.getConnection(URL, DBNAME, DBPASS);
						con.setAutoCommit(true);
						iCheckSQL(con, iSQLSentence);
					}catch(SQLException e){
						e.printStackTrace();
						log.error("数据库连接失败！");
					}
					log.debug("数据库连接成功");
					return con;
				}
				
				
				
				/**
				 * 数据库查询语句
				 * @param con
				 * @throws SQLException
				 */
				private static void iCheckSQL(Connection con,String SQLSentence) throws SQLException {
					// TODO Auto-generated method stub
					System.out.println("已经连接到数据库...");
					String sql = SQLSentence;
					stmt=con.createStatement();
					ResultSet rs=stmt.executeQuery(sql);
					
					ResultSetMetaData rsmd = rs.getMetaData(); 
					int numberOfColumns = rsmd.getColumnCount(); //得到数据集的列数  


					     	while(rs.next())
					     {   
					     		String name=rs.getString(1); 
					     	    System.out.println(name);
					       	 }					  
					   stmt.close();
					       	con.close(); 					    
				}


				/**
				 * 关闭数据库连接，注意关闭的顺序
				 */
				public void close(ResultSet rs, Statement stmt, Connection conn) {
					if(rs!=null){
						try{
							rs.close();
							rs=null;
						}catch(SQLException e){
							e.printStackTrace();
							log.error("关闭ResultSet失败");
						}
					}
					if(stmt!=null){
						try{
							stmt.close();
							stmt=null;
						}catch(SQLException e){
							e.printStackTrace();
							log.error("关闭PreparedStatement失败");
						}
					}
					if(conn!=null){
						try{
							conn.close();
							conn=null;
						}catch(SQLException e){
							e.printStackTrace();
							log.error("关闭Connection失败");
						}
					}
				}				
				
			 @Test
				/**
				 * main方法
				 * @param args
				 */
//				public static void main(String[] args) {
				public void iSQLConnect(){
					System.out.println("开始创建链接");
					CheckSql2 itd = new CheckSql2();
					itd.testCheckSqlDriver();
					itd.getConnection();
					itd.close(rs, stmt, con);
				}
	
}
