package com.yeahwell.epu.common.db;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yeahwell.epu.common.util.DateUtil;

public class MySQLBackupImpl {
	
	private static final Logger logger = LoggerFactory.getLogger(MySQLBackupImpl.class);
	
	private static String host = "localhost";

	private static String username = "root";
	
	private static String password = "yanjiawei";

	//需要备份的数据库名
	private static String dbname = "1kmile201405"; 
	
	//private static String charset;
	
	static{
//		host = "";
//		username = "";
//		password = "";
//		dbname = "";
	}
	
	public static boolean backup(String toPath) {
		try {
			Runtime runtime = Runtime.getRuntime();
			String cmd = "mysqldump -h" + host + " -u" + username +" -p" + password + " "+ dbname;
			logger.info("备份sql语句" + cmd);
			// 调用 mysql 的 cmd:
			Process process = runtime.exec("cmd /c " + cmd);
			// 把进程执行中的控制台输出信息写入.sql文件，即生成了备份文件。注：如果不对控制台信息进行读出，则会导致进程堵塞无法运行
			// 控制台的输出信息作为输入流
			InputStream inputStream = process.getInputStream();
			// 设置输出流编码为utf8。这里必须是utf8，否则从流中读入的是乱码
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf8");
			String inStr;
			StringBuffer sb = new StringBuffer("");
			String outStr;
			// 组合控制台输出信息字符串
			BufferedReader br = new BufferedReader(inputStreamReader);
			while ((inStr = br.readLine()) != null) {
				sb.append(inStr + "\r\n");
			}
			outStr = sb.toString();
			// 要用来做导入用的sql目标文件：
			toPath += DateUtil.formatDateTime2(null) +"-1kmile.sql";
			FileOutputStream fout = new FileOutputStream(toPath);
			OutputStreamWriter writer = new OutputStreamWriter(fout, "utf8");
			writer.write(outStr);
			// 注：这里如果用缓冲方式写入文件的话，会导致中文乱码，用flush()方法则可以避免
			writer.flush();
			// 别忘记关闭输入输出流
			inputStream.close();
			inputStreamReader.close();
			br.close();
			writer.close();
			fout.close();
			logger.info("备份成功");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("备份失败");
		}
		return true;
	}

	public static boolean load(String filePath) {
		try {
			Runtime rt = Runtime.getRuntime();
			String cmd = "mysql -u" + username + " -p" + password + " " + dbname;
			logger.info("恢复命令" + cmd);
			Process process = rt.exec(cmd);
			OutputStream outStream = process.getOutputStream();// 控制台的输入信息作为输出流
			String inStr;
			StringBuffer sb = new StringBuffer("");
			String outStr;
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream(filePath), "utf8"));
			while ((inStr = br.readLine()) != null) {
				sb.append(inStr + "\r\n");
			}
			outStr = sb.toString();
			OutputStreamWriter writer = new OutputStreamWriter(outStream, "utf8");
			writer.write(outStr);
			// 注：这里如果用缓冲方式写入文件的话，会导致中文乱码，用flush()方法则可以避免
			writer.flush();
			outStream.close();
			br.close();
			writer.close();
			logger.info("恢复成功");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("恢复失败");
		}
		return true;
	}
	
	public static void main(String[] args) {
		MySQLBackupImpl.backup("./sql/");
	}

}
