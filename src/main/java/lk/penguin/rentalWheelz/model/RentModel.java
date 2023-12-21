package lk.penguin.rentalWheelz.model;

import lk.penguin.rentalWheelz.db.DbConnection;
import lk.penguin.rentalWheelz.dto.CustomerDto;
import lk.penguin.rentalWheelz.dto.EmployeeDto;
import lk.penguin.rentalWheelz.dto.RentDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RentModel {
    public boolean saveRent(RentDto rentDto){
        try {
            Connection connection= DbConnection.getInstance().getConnection();
            String sql="INSERT INTO rent VALUES (?,?,?,?,?)";
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1,rentDto.getRentId());
            //pstm.setString(1,);
            pstm.setString(3,rentDto.getCustId());
            pstm.setString(2, String.valueOf(rentDto.getTotalAmount()));
            pstm.setString(4,rentDto.getStartingDate());
            pstm.setString(5,rentDto.getEndingDate());

            int i = pstm.executeUpdate();
            if(i>0){
                return true;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public ArrayList<String> getAllCustomerId() throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT cust_id FROM customer ORDER BY LENGTH(cust_id),cust_id";

        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        ArrayList<String> list = new ArrayList<>();

        while (resultSet.next()) {
            list.add(resultSet.getString(1));
        }
        return list;
    }

    public ArrayList<String> getAllCarList() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT car_name FROM car ORDER BY LENGTH(car_name),car_name";

        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        ArrayList<String> list = new ArrayList<>();

        while (resultSet.next()) {
            list.add(resultSet.getString(1));
        }
        return list;
    }

    public List<RentDto> getAllRentals() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM rent";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        List<RentDto> list = new ArrayList<>();
        while (resultSet.next()) {
            // Assuming that getString(6) retrieves the 'cardetails' column
            /*Array cardetailsArray = null;
            String[] cardetails = (String[]) cardetailsArray.getArray();*/

            list.add(new RentDto(
                    resultSet.getString(1),
                    resultSet.getString(3),
                    Double.parseDouble(resultSet.getString(2)),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    new ArrayList<>()
            ));
        }
        return list;
    }



}
