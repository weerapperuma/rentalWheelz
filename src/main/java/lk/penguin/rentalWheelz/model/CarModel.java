package lk.penguin.rentalWheelz.model;

import lk.penguin.rentalWheelz.db.DbConnection;
import lk.penguin.rentalWheelz.dto.CarDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class CarModel {
    public boolean saveCar(CarDto carDto){
        try {
            Connection connection= DbConnection.getInstance().getConnection();
            String sql="INSERT INTO rent VALUES (?,?,?)";
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1,carDto.getCarId());
            pstm.setString(2,carDto.getCarCategory());
            pstm.setString(3, carDto.getCarStatus());

            int i = pstm.executeUpdate();
            if(i>0){
                return true;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
    public boolean updateAvailability(ArrayList<String[]> carDetails){
        try {
            Connection connection=DbConnection.getInstance().getConnection();
            String sql="UPDATE car SET status=? WHERE car_id=? ";


            PreparedStatement pstm = connection.prepareStatement(sql);
            for(int i=0;i<carDetails.size();i++){
                pstm.setString(1,carDetails.get(i)[2]);
                pstm.setString(2,carDetails.get(i)[0]);
                int j = pstm.executeUpdate();
                if(j==0){
                    return false;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}