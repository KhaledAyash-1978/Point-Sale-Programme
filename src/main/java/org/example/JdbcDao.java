package org.example;

import java.sql.*;

public class JdbcDao {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/posjavafx?useSSL=false";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "";
    private static final String SELECT_QUERY = "SELECT * FROM users WHERE username = ? AND password = ?";

    public boolean validate(String username, String password) {
        // step 1 : establish a connection
        try {
            Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
            // step 2 : create a statement using connection object
            PreparedStatement statement = connection.prepareStatement(SELECT_QUERY);
            statement.setString(1, username);
            statement.setString(2, password);
            System.out.println(statement);
            ResultSet rs = statement.executeQuery();
            if(rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            // print Sql exception
            printSQLException(ex);
        }
        return false;
    }



    /* Function for table Manager */

    public Connection getConnection(){
        try{
            Connection connection = DriverManager.getConnection(DATABASE_URL,DATABASE_USERNAME,DATABASE_PASSWORD);
            return connection;
        }catch(SQLException ex){
            printSQLException(ex);
        }
        return null;
    }


    public static void printSQLException(SQLException ex){
        for(Throwable e : ex){
            if(ex instanceof SQLException){
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException)e).getSQLState());
                System.err.println("Error Code: " + ((SQLException)e).getErrorCode());
                System.err.println("Message: " + ex.getMessage());
                Throwable t = ex.getCause();
                while(t != null){
                    System.err.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }


}

