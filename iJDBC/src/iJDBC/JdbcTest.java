package iJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

/**
 * 负责链接数据库的类
 * @author 700sfriend
 *
 */
public class JdbcTest {
	/**
	 * @author 700sfriend
	 * testng的@Test注解写到方法名前
	 */
	@Test
//	public static void main(String[] args) throws SQLException {
	public void  ttxx(){ 
			// 使用log4j记录日志
			Logger logger = Logger.getLogger(BaseDao.class);
		    //数据库的连接驱动，一般是不需要变的
		     final String DRIVER="com.microsoft.sqlserver.jdbc.SQLServerDriver";
		    //数据库连接的URL,1433为默认的数据库端口号，700Store.Order为当前你要操作的数据库名
			 final String URL="jdbc:sqlserver://192.168.188.11:1433;DatabaseName=700Store.Order";
			    //登录账户
			 final String DBNAME="sa";
			    //登录的密码
			 final String DBPASS="700Bike520";
		
		     Connection con=null;    
		     ResultSet rs=null;
		     Statement stmt = null;
		     
		     try{
					Class.forName(DRIVER);//加载驱动
					}
					catch(ClassNotFoundException event){
		//				System.out.print("无法创建驱动程式实体!");
						logger.error("无法创建驱动程式实体!");
						}
		     
		     	try{
		         con = DriverManager.getConnection(URL,DBNAME,DBPASS);
		         logger.error("@!@@!@@!@!");
		         stmt=con.createStatement();
		         String sql="select * from or_Order";
		         rs=stmt.executeQuery(sql);
		         while(rs.next()){
		            System.out.println(rs.getString("OrderId"));
		         }
		     }catch(Exception e){
		         try {
					con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		         try {
					rs.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		         try {
					stmt.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		         e.printStackTrace();
		     }finally{
		            if(rs!=null){
		              try {
						rs.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		            }
		            if(stmt==null){
		            try {
						stmt.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		            }
		            if(con!=null){
		                  try {
							con.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		            }
		    }
		  }

}
	
