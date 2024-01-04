package com.hdu.train.mapper;

import com.hdu.train.DTO.NonStopStationDTO;
import com.hdu.train.DTO.TransitDTO;
import com.hdu.train.entity.Station;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ZhangQi
 * @since 2023-12-23
 */
public interface StationMapper extends BaseMapper<Station> {

    @Select("SELECT s1.train_no AS firstTrain,s1.station_name AS firstStation,\n" +
            "s2.station_name AS transitStation,s4.station_name AS lastStation,\n" +
            "s1.leave_time AS time1,s2.arrive_time AS time2,s3.leave_time AS time3,\n" +
            "s4.arrive_time AS time4,s4.train_no AS lastTrain\n" +
            "FROM station s1\n" +
            "INNER JOIN station s2 ON s1.train_no = s2.train_no\n" +
            "INNER JOIN station s3 ON s2.station_name = s3.station_name\n" +
            "INNER JOIN station s4 ON s3.train_no = s4.train_no\n" +
            "WHERE s1.station_name = #{startStation}\n" +
            "  AND s4.station_name = #{endStation}\n" +
            "  AND s1.station_order < s2.station_order\n" +
            "  AND s3.station_order < s4.station_order\n" +
            "  AND s1.train_no <> s4.train_no")
    List<TransitDTO> searchStation(@Param("startStation")String startStation, @Param("endStation")String endStation);
    @Select("SELECT s1.train_no AS trainNo,s1.station_name AS startStation,\n" +
            "s2.station_name AS endStation,\n" +
            "s1.leave_time AS leaveTime,s2.arrive_time AS arriveTime\n" +
            "FROM station s1\n" +
            "INNER JOIN station s2 ON s1.train_no = s2.train_no\n" +
            "WHERE s1.station_name = #{startStation}\n" +
            "  AND s2.station_name = #{endStation}\n" +
            "  AND s1.station_order < s2.station_order")
    List<NonStopStationDTO> findStation(@Param("startStation")String startStation, @Param("endStation")String endStation);
}
