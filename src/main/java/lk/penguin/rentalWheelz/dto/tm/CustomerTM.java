package lk.penguin.rentalWheelz.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomerTM {
    private String custId;
    private String custName;
    private String custAddress;
    private String custContact;
    private String custUserID;
}
