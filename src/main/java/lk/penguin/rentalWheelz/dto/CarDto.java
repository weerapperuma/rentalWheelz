package lk.penguin.rentalWheelz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CarDto {
    private String carId;
    private String carCategory;
    private String carStatus;


}