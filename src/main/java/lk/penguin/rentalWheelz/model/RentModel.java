package lk.penguin.rentalWheelz.model;

import lk.penguin.rentalWheelz.db.DbConnection;
import lk.penguin.rentalWheelz.dto.CustomerDto;
import lk.penguin.rentalWheelz.dto.RentDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
}
