package iReadText;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * 创建文件，读取文件
 * @author 700sfriend
 *
 */

public class readText{

		/**
		 * 日志对象
		 */
		static Logger log = LogManager.getLogger(readText.class.getName()); 	
	     
	    /**
	     * 指定文件路径，创建文件
	     * @return 
	     * @throws IOException
	     */
//	    public static void main(String[] args){
	    public  String checkTestTxt() {	    	
	    	/* 设置 文件 的生产路径*/
	    	log.info(System.getProperty("user.dir"));
			// locatorMap = ReadExcelUtil.getLocatorMap();
			String path = System.getProperty("user.dir")
					+ "/src/iReadText/"
					+ this.getClass().getSimpleName() + ".txt";
			log.info(path);
	    	
	    	/*创建文件的流程*/
	        File file = new File(path);
	        try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.error("没创建成功");
			}
	        System.out.println(txt2String(file));
	        return txt2String(file);
	    }
	    
	    /**
	     * 读取txt文件的内容
	     * @param file 想要读取的文件对象
	     * @return 返回文件内容
	     */	
	    public static String txt2String(File file){
	        String result = "";
	        try{
	            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
	            String s = null;
	            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
	                result = result + "\n" +s;
	            }
	            br.close();    
	        }catch(Exception e){
	            e.printStackTrace();
	        }
	        return result;
	    }
	}