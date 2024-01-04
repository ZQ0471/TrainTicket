package com.hdu.train.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hdu.train.DTO.NameDTO;
import com.hdu.train.DTO.NonStopStationDTO;
import com.hdu.train.DTO.TransitDTO;
import com.hdu.train.entity.Station;
import com.hdu.train.mapper.StationMapper;
import com.hdu.train.service.IStationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ZhangQi
 * @since 2023-12-23
 */
@Service("iStationService")
public class StationServiceImpl extends ServiceImpl<StationMapper, Station> implements IStationService {

    @Override
    public List<Station> getStationList(String trainNo) {
        LambdaQueryWrapper<Station> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Station::getTrainNo,trainNo);
        wrapper.orderByAsc(Station::getStationOrder);
        return this.baseMapper.selectList(wrapper);
    }

    @Override
    public List<NameDTO> getStationName() {
        QueryWrapper<Station> wrapper = new QueryWrapper<>();
        wrapper.select("DISTINCT station_name");
        List<NameDTO> result = new ArrayList<>();
        List<Station> stations = this.baseMapper.selectList(wrapper);
        stations.forEach(station -> {
            result.add(new NameDTO(station.getStationName()));
        });
        return result;
    }

    @Override
    public List<TransitDTO> searchStation(String startStation, String endStation) {
        return this.baseMapper.searchStation(startStation,endStation);
    }

    @Override
    public List<NonStopStationDTO> findStation(String startStation, String endStation) {
        return this.baseMapper.findStation(startStation,endStation);
    }
}
