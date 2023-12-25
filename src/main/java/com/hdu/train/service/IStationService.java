package com.hdu.train.service;

import com.hdu.train.DTO.NameDTO;
import com.hdu.train.DTO.TransitDTO;
import com.hdu.train.entity.Station;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ZhangQi
 * @since 2023-12-23
 */
public interface IStationService extends IService<Station> {

    List<Station> getStationList(String trainNo);

    List<NameDTO> getStationName();

    List<TransitDTO> searchStation(String startStation, String endStation);
}
