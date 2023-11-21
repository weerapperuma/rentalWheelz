package lk.penguin.rentalWheelz.model;

import lk.penguin.rentalWheelz.controller.EmployeeFormController;
import lk.penguin.rentalWheelz.db.DbConnection;
import lk.penguin.rentalWheelz.dto.EmployeeDto;
import lk.penguin.rentalWheelz.dto.RentDto;

import java.sql.Connection;
import java.sql.SQLException;

public class CarRentModel {
    RentModel rentModel=new RentModel();
    CarModel carModel=new CarModel();
    RentDetailModel rentDetailModel=new RentDetailModel();
    public boolean placeRent(RentDto rentDto) throws SQLException {
        Connection connection=null;
        boolean saved=false;
        try {
            connection= DbConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            boolean save = rentModel.saveRent(rentDto);
            if(save){
                boolean updateAvailability = carModel.updateAvailability(rentDto.getCardetails());

                if(updateAvailability){
                    boolean insert=rentDetailModel.save(rentDto.getRentId(),rentDto.getCardetails());
                    if(insert){
                        connection.commit();
                        saved=true;
                    }
                }
            }
        } catch (SQLException e) {
            connection.rollback();
            throw new RuntimeException(e);
        }
        finally {
            connection.setAutoCommit(true);
        }
        return saved;
    }

}
