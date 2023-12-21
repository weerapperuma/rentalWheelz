package lk.penguin.rentalWheelz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RentDto {
    private String rentId;
    private String custId;
    private double totalAmount;
    private String startingDate;
    private String endingDate;
    private ArrayList<String []> cardetails;


}
