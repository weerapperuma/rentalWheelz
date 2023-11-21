package lk.penguin.rentalWheelz.model;

import lk.penguin.rentalWheelz.db.DbConnection;
import lk.penguin.rentalWheelz.dto.CarDto;
import lk.penguin.rentalWheelz.dto.CustomerDto;
import lk.penguin.rentalWheelz.dto.EmployeeDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public CarDto searchCar(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM car WHERE car_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);

        ResultSet resultSet = pstm.executeQuery();

        CarDto dto = null;

        if(resultSet.next()) {
            String carId = resultSet.getString(1);
            String carCategory = resultSet.getString(2);
            String carStatus=resultSet.getString(3);

            dto = new CarDto(carId, carCategory, carStatus);
        }

        return dto;
    }

    public boolean updateCar(final CarDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE car SET car_id = ?, car_name = ?, status = ? WHERE car_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getCarId());
        pstm.setString(2, dto.getCarCategory());
        pstm.setString(3, dto.getCarStatus());

        // Set the parameter for the WHERE clause (car_id)
        pstm.setString(4, dto.getCarId());

        return pstm.executeUpdate() > 0;
    }

    public boolean saveNewCar(CarDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO car VALUES(?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getCarId());
        pstm.setString(2, dto.getCarCategory());
        pstm.setString(3, dto.getCarStatus());

        boolean isSaved = pstm.executeUpdate() > 0;

        return isSaved;
    }

    public boolean deleteCar(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM car WHERE car_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);

        return pstm.executeUpdate() > 0;
    }

    public List<CarDto> getAllCars() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM car";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        List<CarDto> list = new ArrayList<>();
        while (resultSet.next()){
            list.add(new CarDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3)
            ));
        }
        return list;
    };
}