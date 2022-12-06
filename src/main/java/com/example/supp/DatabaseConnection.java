package com.example.supp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseConnection {
    String SQLURL = "jdbc:mysql://localhost:3306/supply?use SSl=false";
    String username = "root";
    String password = "my_Future@79";

    Connection con = null;
    DatabaseConnection(){
        try {
            con= DriverManager.getConnection(SQLURL, username, password);
            if(con!=null){
                System.out.println("Our DATA base Connection is successful");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public ResultSet executeQuery(String query){
        ResultSet res = null;
        try{
            Statement statement = con.createStatement();
            res = statement.executeQuery(query);
            return res;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return  res;
    }
    public int executeUpdate(String query){
        int res=0;
        try{
            Statement statement = con.createStatement();
            res = statement.executeUpdate(query);
            return res;
        }
        catch(Exception e){
            e.printStackTrace();;
        }
        return res;
    }
}
