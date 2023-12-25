package com.hdu.train.controller;

import com.hdu.train.DTO.NameDTO;
import com.hdu.train.entity.Train;
import com.hdu.train.result.Result;
import com.hdu.train.service.ITrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/train")
public class TrainController {
    @Autowired
    private ITrainService iTrainService;

    @GetMapping("/list")
    public Result TrainList(@RequestParam Integer pageSize,Integer currentPage){
        List<Train> trainList = iTrainService.getTrainList(pageSize,currentPage);
        System.out.println(trainList);
        return Result.ok().data("items",trainList).data("total",trainList.size());
    }
    @GetMapping("/trainName")
    public Result TrainName(){
        List<NameDTO> trainNameList = iTrainService.getTrainName();
        return Result.ok().data("number",trainNameList);
    }

}
