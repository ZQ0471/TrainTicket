package com.hdu.train.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NonStopStationDTO {
    /**
     * 车次号
     */
    private String trainNo;
    /**
     * 站点名
     */
    private String startStation;
    /**
     * 站点名
     */
    private String endStation;
    /**
     * 出发时间
     */
    private String leaveTime;
    /**
     * 到达时间
     */
    private String arriveTime;
}
