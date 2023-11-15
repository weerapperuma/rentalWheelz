package lk.penguin.rentalWheelz.model;

import lk.penguin.rentalWheelz.db.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserModel {
    public static boolean verifyCredentials(String u_name,String password){
        try {
            DbConnection instance = DbConnection.getInstance();
            Connection connection = instance.getConnection();

            String sql="SELECT password FROM user WHERE u_name=?";

            PreparedStatement pstm = connection.prepareStatement(sql);

            pstm.setString(1,u_name);

            ResultSet resultSet = pstm.executeQuery();

            if(resultSet.next()){
                if(password.equals(resultSet.getString(1))){
                    return true;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
