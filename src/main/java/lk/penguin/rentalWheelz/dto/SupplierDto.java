package lk.penguin.rentalWheelz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SupplierDto {
    private String supId;
    private String supName;
    private String supAvailableCar;
    private String supOrderId;
}
