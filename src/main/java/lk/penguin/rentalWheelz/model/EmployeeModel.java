package lk.penguin.rentalWheelz.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.penguin.rentalWheelz.db.DbConnection;
import lk.penguin.rentalWheelz.dto.CustomerDto;
import lk.penguin.rentalWheelz.dto.EmployeeDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeModel {


    public List<EmployeeDto> getAllEmployees() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM employee";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        List<EmployeeDto> list = new ArrayList<>();
        while (resultSet.next()){
            list.add(new EmployeeDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6)
            ));
        }
        return list;
    };

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

    public boolean deleteEmployee(String id) throws SQLException {
        Connection connection=DbConnection.getInstance().getConnection();
        String sql="DELETE FROM employee WHERE emp_id=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,id);

        int i = pstm.executeUpdate();
        return (i>0);
    }

    public boolean updateEmployee(final EmployeeDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql="UPDATE employee SET emp_id=?,e_name=?,email=?,position=?,address=?,contact=? WHERE emp_id=?";

        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1,dto.getEmpId());
        pstm.setString(2,dto.getEmpName());
        pstm.setString(3,dto.getEmail());
        pstm.setString(4,dto.getPosition());
        pstm.setString(5,dto.getAddress());
        pstm.setString(6,dto.getContact());
        pstm.setString(7,dto.getEmpId());

        return pstm.executeUpdate()>0;
    }

    public EmployeeDto searchEmployee(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql="SELECT * FROM employee WHERE emp_id=?";

        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,id);

        ResultSet resultSet = pstm.executeQuery();

        EmployeeDto dto=null;
        if(resultSet.next()){
            String empId=resultSet.getString(1);
            String empName=resultSet.getString(2);
            String email=resultSet.getString(3);
            String position=resultSet.getString(4);
            String address=resultSet.getString(5);
            String contact=resultSet.getString(6);

            dto=new EmployeeDto(empId,empName,email,position,address,contact);
        }
        return dto;
    }

    public ObservableList<String> LoadId() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT sup_id FROM supplier";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        ObservableList<String> Ids = FXCollections.observableArrayList();

        while (resultSet.next()){
            String x = resultSet.getString(1);
            Ids.add(x);
        }
        return Ids;
    }
}
