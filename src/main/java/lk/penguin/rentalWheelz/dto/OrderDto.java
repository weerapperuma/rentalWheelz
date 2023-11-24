package lk.penguin.rentalWheelz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class OrderDto {
    private String oId;
    private String sId;
    private String Date;
}
