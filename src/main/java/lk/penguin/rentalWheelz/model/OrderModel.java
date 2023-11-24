package lk.penguin.rentalWheelz.model;

import lk.penguin.rentalWheelz.db.DbConnection;
import lk.penguin.rentalWheelz.dto.OrderDto;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderModel {
    @SneakyThrows
    public boolean save(OrderDto dto) {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "INSERT INTO sup_order VALUES (?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,dto.getOId());
        pstm.setString(2, dto.getDate());

        return pstm.executeUpdate() >0;
    }
    @SneakyThrows

    public boolean Osave(OrderDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "INSERT INTO sup_order_detail VALUES (?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,dto.getOId());
        preparedStatement.setString(2,dto.getSId());
        return preparedStatement.executeUpdate() >0;
    }


}
