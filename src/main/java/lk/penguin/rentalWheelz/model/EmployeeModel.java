package lk.penguin.rentalWheelz.model;

import lk.penguin.rentalWheelz.db.DbConnection;
import lk.penguin.rentalWheelz.dto.EmployeeDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmployeeModel {
    public boolean savedEmployee(EmployeeDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "INSERT INTO employee VALUES(?,?,?,?,?,?)";

        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1,dto.getEmpId());
        pstm.setString(2,dto.getEmpName());
        pstm.setString(3,dto.getEmail());
        pstm.setString(4,dto.getPosition());
        pstm.setString(5,dto.getAddress());
        pstm.setString(6,dto.getContact());

        int i = pstm.executeUpdate();
        return(i>0);
    }
}
