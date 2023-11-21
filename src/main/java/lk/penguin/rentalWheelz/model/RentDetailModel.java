package lk.penguin.rentalWheelz.model;

import lk.penguin.rentalWheelz.db.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class RentDetailModel {
    public boolean save(String id,ArrayList<String[]> carDetails){

        try {
            Connection connection= DbConnection.getInstance().getConnection();
            String sql="INSERT INTO rent_detail VALUES(?,?,?)";
            PreparedStatement pstm = connection.prepareStatement(sql);
            for(int i=0;i<carDetails.size();i++){
                pstm.setString(1,id);
                pstm.setString(2,carDetails.get(i)[0]);
                pstm.setString(3,carDetails.get(i)[1]);
                int i1 = pstm.executeUpdate();

                if(i1==0){
                    return false;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}
