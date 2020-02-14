package cn.edu.nju.madpill.service;

import cn.edu.nju.madpill.domain.Warehouse;
import cn.edu.nju.madpill.dto.PageRequest;
import cn.edu.nju.madpill.dto.WarehouseDTO;
import cn.edu.nju.madpill.mapper.WarehouseMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static cn.edu.nju.madpill.mapper.WarehouseDynamicSqlSupport.name;
import static org.mybatis.dynamic.sql.SqlBuilder.isLike;

/**
 * <p>
 * <p>
 *
 * @author Charles
 * @date 2020/2/13
 */
@Service
public class WarehouseService {

    private final WarehouseMapper warehouseMapper;
    private final ModelMapper modelMapper;

    public WarehouseService(WarehouseMapper warehouseMapper, ModelMapper modelMapper) {
        this.warehouseMapper = warehouseMapper;
        this.modelMapper = modelMapper;
    }

    public PageInfo<WarehouseDTO> getWarehouses(String query, PageRequest pageRequest) {
        final String queryStr = "%" + query + "%";
        // 在你需要进行分页的 MyBatis 查询方法前调用 PageHelper.startPage 静态方法即可
        // 紧跟在这个方法后的第一个MyBatis 查询方法会被进行分页。
        PageHelper.startPage(pageRequest.getPage(), pageRequest.getPageSize(), pageRequest.getSort());
        List<Warehouse> warehouses = warehouseMapper.select(c -> c.where(name, isLike(queryStr)));
        List<WarehouseDTO> dtoList = warehouses.stream().map(warehouse -> {
            WarehouseDTO dto = new WarehouseDTO();
            modelMapper.map(warehouse, dto);
            return dto;
        }).collect(Collectors.toList());
        return PageInfo.of(dtoList);
    }

}
