package lk.penguin.rentalWheelz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmployeeDto {
    private String empId;
    private String empName;
    private String email;
    private String position;
    private String address;
    private String contact;
}
