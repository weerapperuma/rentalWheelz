package lk.penguin.rentalWheelz.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmployeeTM {
    private String empId;
    private String empName;
    private String empEmail;
    private String empPosition;
    private String empAddress;
    private String empContact;

}
