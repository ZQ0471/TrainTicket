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
public class Train implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 车次
     */
    private String trainNo;

    /**
     * 类型，动车或高铁
     */
    private String trainType;

    /**
     * 起点
     */
    private String startStation;

    /**
     * 终点
     */
    private String endStation;

    /**
     * 出发时间
     */
    private String startTime;

    /**
     * 到达时间
     */
    private String endTime;


    @Override
    public String toString() {
        return "Train{" +
            "trainNo=" + trainNo +
            ", trainType=" + trainType +
            ", startStation=" + startStation +
            ", endStation=" + endStation +
            ", startTime=" + startTime +
            ", endTime=" + endTime +
        "}";
    }
}
