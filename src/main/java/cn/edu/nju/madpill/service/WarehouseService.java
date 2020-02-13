package cn.edu.nju.madpill.service;

import cn.edu.nju.madpill.domain.Warehouse;
import cn.edu.nju.madpill.dto.WarehouseDTO;
import cn.edu.nju.madpill.mapper.WarehouseMapper;
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

    public List<WarehouseDTO> getWarehouses(String query) {
        final String queryStr = "%" + query + "%";
        List<Warehouse> warehouses = warehouseMapper.select(c -> c.where(name, isLike(queryStr)));
        return warehouses.stream().map(warehouse -> {
            WarehouseDTO dto = new WarehouseDTO();
            modelMapper.map(warehouse, dto);
            return dto;
        }).collect(Collectors.toList());
    }

}
