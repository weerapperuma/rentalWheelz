package lk.penguin.rentalWheelz.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SupplierTM {
    private String supId;
    private String supName;
    private String supAvailableCar;
    private String supOrderId;
}
