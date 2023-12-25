package com.hdu.train.controller;

import com.hdu.train.DTO.NameDTO;
import com.hdu.train.DTO.TransitDTO;
import com.hdu.train.entity.Station;
import com.hdu.train.result.Result;
import com.hdu.train.service.IStationService;
import com.hdu.train.util.TimeCompare;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ZhangQi
 * @since 2023-12-23
 */
@RestController
@RequestMapping("/station")
public class StationController {
    @Autowired
    private IStationService iStationService;
    @Autowired
    private TimeCompare timeCompare;
    @GetMapping("/list")
    public Result StationList(@RequestParam("trainNo") String trainNo){
        List<Station> stationList = iStationService.getStationList(trainNo);
        System.out.println(stationList);
        return Result.ok().data("items",stationList).data("total",stationList.size());
    }
    @GetMapping("/stationName")
    public Result StationName(){
        List<NameDTO> stationNameList = iStationService.getStationName();
        return Result.ok().data("number",stationNameList);
    }
    @GetMapping("/search")
    private Result SearchStation(@RequestParam String start_station, String end_station){
        List<TransitDTO> transitDTOS = iStationService.searchStation(start_station,end_station);
        List<TransitDTO> trains = new ArrayList<>();
        transitDTOS.forEach(item->{
            if(timeCompare.Compare(item.getTime2(),item.getTime3())){
                trains.add(item);
            }
        });
        return Result.ok().data("list",trains);
    }

}
