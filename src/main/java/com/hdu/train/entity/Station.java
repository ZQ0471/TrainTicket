package com.hdu.train.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author ZhangQi
 * @since 2023-12-23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Station implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 车次
     */
    private String trainNo;


    /**
     * 站点名
     */
    private String stationName;

    /**
     * 出发时间
     */
    private String leaveTime;

    /**
     * 到达时间
     */
    private String arriveTime;

    /**
     * 次序
     */
    private Integer stationOrder;


    @Override
    public String toString() {
        return "Station{" +
            "trainNo=" + trainNo +
            ", stationName=" + stationName +
            ", leaveTime=" + leaveTime +
            ", arriverTime=" + arriveTime +
            ", stationOrder=" + stationOrder +
        "}";
    }
}
