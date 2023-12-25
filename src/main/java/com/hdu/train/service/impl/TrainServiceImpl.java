package com.hdu.train.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hdu.train.DTO.NameDTO;
import com.hdu.train.entity.Station;
import com.hdu.train.entity.Train;
import com.hdu.train.mapper.TrainMapper;
import com.hdu.train.service.ITrainService;
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
@Service("iTrainService")
public class TrainServiceImpl extends ServiceImpl<TrainMapper, Train> implements ITrainService {

    @Override
    public List<Train> getTrainList(Integer pageSize, Integer currentPage) {
        LambdaQueryWrapper<Train> wrapper = new LambdaQueryWrapper<>();
        new Page<Train>(currentPage,pageSize);
        Page<Train> page = new Page<>();
        page.setCurrent(currentPage);
        page.setSize(pageSize);
        return this.baseMapper.selectPage(page,wrapper).getRecords();
    }

    @Override
    public List<NameDTO> getTrainName() {
        QueryWrapper<Train> wrapper = new QueryWrapper<Train>();
        wrapper.select("DISTINCT train_no");
        List<NameDTO> result = new ArrayList<>();
        List<Train> stations = this.baseMapper.selectList(wrapper);
        stations.forEach(station -> {
            result.add(new NameDTO(station.getTrainNo()));
        });
        return result;
    }
}
