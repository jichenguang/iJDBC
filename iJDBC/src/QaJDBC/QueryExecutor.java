package QaJDBC;

import java.awt.BorderLayout;  
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;  
import java.io.FileInputStream;  
import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.ResultSet;  
import java.sql.ResultSetMetaData;  
import java.sql.Statement;  
import java.util.Properties;  
import java.util.Vector;  
  
import javax.swing.JButton;  
import javax.swing.JFrame;  
import javax.swing.JLabel;  
import javax.swing.JPanel;  
import javax.swing.JScrollPane;  
import javax.swing.JTable;  
import javax.swing.JTextField;  
import javax.swing.table.DefaultTableModel;

import iReadText.Readini;  

/**
 * 一个sql查询器，使用了properties文件
 * @author 700sfriend
 *
 */
public class QueryExecutor {
	JFrame jf = new JFrame("查询执行器");  
    private JScrollPane scrollPane;  
    private DefaultTableModel model;  
    private JButton execBn=new JButton("查询");  
      
    private JTextField sqlField=new JTextField(40);        
    private static Connection conn;  
    private static Statement stmt;  
    private static ResultSet rs;  
      
    static {  
        try {  
            /*ini文件记录了数据库的信息*/  
            Readini ttxx = new Readini();
            String path = ttxx.checkTestTxt();
            System.out.println(path);
            FileInputStream in=new FileInputStream(path);         
            Properties prop=new Properties(); 
            prop.load(in);  
            in.close();  
            String driver =prop.getProperty("driver");  
            String url=prop.getProperty("url");  
            String user=prop.getProperty("user");  
            String pass=prop.getProperty("pass");  
            Class.forName(driver);  
            conn=DriverManager.getConnection(url,user,pass);  
            stmt=conn.createStatement();  
              
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
          
    }  
    class ExecListener implements ActionListener{  
        @Override  
        public void actionPerformed(ActionEvent e) {  
            // TODO Auto-generated method stub  
            if(scrollPane!=null){  
                jf.remove(scrollPane);  
            }  
            try {  
                rs=stmt.executeQuery(sqlField.getText());  
                System.out.println();  
                ResultSetMetaData rsmd=rs.getMetaData();  
                Vector<String> columnNames=new Vector<String>();  
                System.out.println("cn"+rsmd.getColumnCount());  
                Vector data=new Vector();  
                for (int i = 0; i < rsmd.getColumnCount(); i++) {  
                    columnNames.add(rsmd.getColumnName(i+1));  
                }  
                while(rs.next()){  
                    Vector v=new Vector();  
                    for (int i = 0; i < rsmd.getColumnCount(); i++) {  
                        v.add(rs.getString(i+1));  
                    }  
                    data.add(v);  
                }  
                model = new DefaultTableModel(data,columnNames);  
                JTable table=new JTable(model);  
                scrollPane=new JScrollPane(table);  
                jf.add(scrollPane);  
                jf.validate();  
                  
            } catch (Exception e2) {  
                e2.printStackTrace();  
            }  
        }  
    }  
    public void init(){  
        JPanel top=new JPanel();  
        top.add(new JLabel("输入查询语句:"));  
        top.add(sqlField);  
        top.add(execBn);  
        execBn.addActionListener(new ExecListener());  
        sqlField.addActionListener(new ExecListener());  
          
        jf.add(top,BorderLayout.NORTH);  
        jf.setSize(640,480);  
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        jf.setVisible(true);  
    }  
      
    public static void main(String[] args) {  
        new QueryExecutor().init();  
    }  
  
}  



