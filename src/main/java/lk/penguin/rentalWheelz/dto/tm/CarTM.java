package lk.penguin.rentalWheelz.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CarTM {
    private String carId;
    private String carCategory;
    private String carStatus;
}
