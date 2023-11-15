package lk.penguin.rentalWheelz.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private static DbConnection dbConnection;
    private Connection connection;

    private DbConnection() throws SQLException{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/rental_wheelz",
                    "penguinDB",
                    "pax_penguina"
            );
        } catch(ClassNotFoundException e) {
            throw new SQLException("Database driver not found",e);
        }

    }
    public static DbConnection getInstance() throws SQLException{
        if(dbConnection==null){
            dbConnection=new DbConnection();
        }
        return dbConnection;
    }
    public Connection getConnection(){
        return connection;
    }
}
