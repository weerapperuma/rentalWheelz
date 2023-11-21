package lk.penguin.rentalWheelz.model;

import lk.penguin.rentalWheelz.db.DbConnection;
import lk.penguin.rentalWheelz.dto.RentDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

}
