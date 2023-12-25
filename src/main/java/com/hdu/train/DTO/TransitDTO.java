package com.hdu.train.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransitDTO {
    private String firstTrain;
    private String lastTrain;
    private String firstStation;
    private String transitStation;
    private String lastStation;
    private String time1;
    private String time2;
    private String time3;
    private String time4;

}
