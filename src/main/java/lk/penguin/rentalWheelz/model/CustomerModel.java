package lk.penguin.rentalWheelz.model;

import lk.penguin.rentalWheelz.db.DbConnection;
import lk.penguin.rentalWheelz.dto.CustomerDto;
import lk.penguin.rentalWheelz.dto.EmployeeDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerModel {

    public boolean savedCustomer(CustomerDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "INSERT INTO customer VALUES(?,?,?,?,?)";

        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1,dto.getCustId());
        pstm.setString(2,dto.getCustName());
        pstm.setString(3,dto.getCustAddress());
        pstm.setString(4,dto.getCustContact());
        pstm.setString(5,dto.getCustUserID());

        int i = pstm.executeUpdate();
        return(i>0);
    }

    public boolean deleteCustomer(String id) throws SQLException {
        Connection connection=DbConnection.getInstance().getConnection();
        String sql="DELETE FROM customer WHERE cust_id=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,id);

        int i = pstm.executeUpdate();
        return (i>0);
    }

    public List<CustomerDto> getAllCustomers() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM customer";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        List<CustomerDto> list = new ArrayList<>();
        while (resultSet.next()){
            list.add(new CustomerDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            ));
        }
        return list;
    };

    public boolean updateCustomer(final CustomerDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql="UPDATE customer SET cust_id=?,name=?,address=?,contact_no=?,u_id=? WHERE cust_id=?";

        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1,dto.getCustId());
        pstm.setString(2,dto.getCustName());
        pstm.setString(3,dto.getCustAddress());
        pstm.setString(4,dto.getCustContact());
        pstm.setString(5,dto.getCustUserID());
        pstm.setString(6,dto.getCustId());

        return pstm.executeUpdate()>0;
    }

    public CustomerDto searchCustomer(String custId) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql="SELECT * FROM customer WHERE cust_id=?";

        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,custId);

        ResultSet resultSet = pstm.executeQuery();

        CustomerDto dto=null;
        if(resultSet.next()){
            String custID=resultSet.getString(1);
            String custName=resultSet.getString(2);
            String custAddress=resultSet.getString(3);
            String custContact=resultSet.getString(4);
            String custUserID=resultSet.getString(5);

            dto=new CustomerDto(custID,custName,custAddress,custContact,custUserID);
        }
        return dto;
    }
}
