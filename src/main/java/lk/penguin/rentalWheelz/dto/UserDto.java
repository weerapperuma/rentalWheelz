package lk.penguin.rentalWheelz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDto {
    private String u_id;
    private String u_name;
    private String password;
    private String emp_id;
    private String email;
}
