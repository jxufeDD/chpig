package com.example.egguser;

/**
 * @author 叶鸣镝
 * <p>
 * description:
 */

import java.sql.Connection;
import java.sql.DriverManager;


public class DatabaseConnection {
    public Connection databaseLink;

    public Connection getConnection(){
        String databaseName="xsgl";
        String databaseUser="root";
        String databasePassword="42391523";
        String url="jdbc:mysql://localhost/"+ databaseName;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");//运行时动态加载指定的类，这样就可以将该类的静态代码块执行，从而注册该驱动程序。
            databaseLink=DriverManager.getConnection(url,databaseUser,databasePassword);
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
        return databaseLink;
    }
}
