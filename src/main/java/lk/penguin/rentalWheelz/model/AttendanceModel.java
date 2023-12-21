package lk.penguin.rentalWheelz.model;

import lk.penguin.rentalWheelz.db.DbConnection;
import lk.penguin.rentalWheelz.dto.AttendanceDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AttendanceModel {

    public String generateAttendID(Connection connection) throws SQLException {
        // Execute a query to get the next value from an auto-incremented column
        String sql = "SELECT AUTO_INCREMENT FROM information_schema.TABLES WHERE TABLE_SCHEMA = 'rental_wheelz' AND TABLE_NAME = 'attendance'";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            if (resultSet.next()) {
                int nextValue = resultSet.getInt("AUTO_INCREMENT");
                return String.valueOf(nextValue);
            }
        }

        throw new SQLException("Unable to generate attendID");
    }
    public String generateAttendanceId() {
        String id = "";
        for (int i = 0; i < 10; i++) {
            int randomInt = (int) (Math.random() * 10);
            id += Integer.toString(randomInt);
        }
        return id;
    }


    public void saveAttendance(AttendanceDto attendanceDto) throws SQLException {
        Connection connection= DbConnection.getInstance().getConnection();
        String sql = "INSERT INTO attendance (attendID, date, empName) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, attendanceDto.getAttendID());
            preparedStatement.setString(2, attendanceDto.getDate());
            preparedStatement.setString(3, attendanceDto.getEmpName());

            preparedStatement.executeUpdate();
        }
    }
}
