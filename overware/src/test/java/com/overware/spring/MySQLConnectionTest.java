package com.overware.spring;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

public class MySQLConnectionTest {
	private static final String DRIVER="com.mysql.jdbc.Driver";
	//private static final String URL="jdbc:mysql://127.0.0.1:3306/book_ex?useSSL=false&";  mySQL 버전이 낮은거면
	private static final String URL="jdbc:mysql://127.0.0.1:3306/book_ex?useSSL=false&serverTimezone=Asia/Seoul"; //6.x 버전이상이면
	private static final String USER="root";
	private static final String PW="1234";
	
	@Test
	//데이터 베이스 접속 테스트 (정상)
	public void testConnection() throws Exception{
		Class.forName(DRIVER);
		
		try(Connection con=DriverManager.getConnection(URL,USER,PW)){
			System.out.println(con);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
