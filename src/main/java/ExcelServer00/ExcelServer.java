package ExcelServer00;//package com.start.ExcelServer;
//
//import com.start.ExcelBeen.excelSheet;
//import org.apache.poi.ss.usermodel.DataFormatter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import java.sql.*;
//
////@RestController
////@EnableAutoConfiguration
//@Service
//public class ExcelServer00 {
//    @Autowired
//    public excelSheet es;
//
//    Connection conn = null;
//    PreparedStatement preparedStatement = null;
//    String sql = "select count(*) from shengji t";
//
//    @Value("${spring.datasource.url}")
//    private String oracleUrl;
//    @Value("${spring.datasource.driver-class-name}")
//    private String oracleDriver;
//    @Value("${spring.datasource.username}")
//    private String userName;
//    @Value("${spring.datasource.password}")
//    private String passWord;
//
//    public void SqlOption() {
//        try {
//            Class.forName(oracleDriver);
//            conn = DriverManager.getConnection(oracleUrl, "pop", "pop");
//            preparedStatement = conn.prepareStatement(sql);
////            preparedStatement.executeUpdate();
//            ResultSet resultSet = preparedStatement.executeQuery();
//            System.out.println(resultSet.absolute(1));
//            DataFormatter formatter = new DataFormatter();
//
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//}
