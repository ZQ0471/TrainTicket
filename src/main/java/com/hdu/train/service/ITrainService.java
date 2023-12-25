package com.hdu.train.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hdu.train.DTO.NameDTO;
import com.hdu.train.entity.Train;
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
public interface ITrainService extends IService<Train> {

    List<Train> getTrainList(Integer pageSize, Integer currentPage);

    List<NameDTO> getTrainName();
}
