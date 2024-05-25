package com.chashugu.lowcodecrud;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chashugu.lowcodecrud.model.FilterInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 业务实现类
 * 表筛选项表
 * </p>
 *
 * @author chashugu
 * @date 2023-08-22
 */
@Slf4j
@Service
public class FilterServiceImpl extends ServiceImpl<FilterMapper, FilterInfo> implements FilterService {

}
