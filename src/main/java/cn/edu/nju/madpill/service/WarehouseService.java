package cn.edu.nju.madpill.service;

import cn.edu.nju.madpill.domain.Warehouse;
import cn.edu.nju.madpill.dto.WarehouseBriefDTO;
import cn.edu.nju.madpill.dto.WarehouseDTO;
import cn.edu.nju.madpill.exception.ExceptionSuppliers;
import cn.edu.nju.madpill.mapper.WarehouseMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;

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

    /**
     * 每次请求仓库药品时的数量
     */
    private static final int WAREHOUSE_NUM_PER_REQUEST = 10;
    private final WarehouseMapper warehouseMapper;
    private final ModelMapper modelMapper;

    public WarehouseService(WarehouseMapper warehouseMapper, ModelMapper modelMapper) {
        this.warehouseMapper = warehouseMapper;
        this.modelMapper = modelMapper;
    }

    public WarehouseDTO getWarehouse(Long warehouseId) {
        Warehouse warehouse = warehouseMapper.selectByPrimaryKey(warehouseId).orElseThrow(ExceptionSuppliers.WAREHOUSE_NOT_FOUND);
        return modelMapper.map(warehouse, WarehouseDTO.class);
    }

    public List<WarehouseBriefDTO> getWarehouses(String query, long start) {
        final String queryStr = "%" + query + "%";
        List<Warehouse> warehouses = warehouseMapper.select(
                c -> c.where(name, isLike(queryStr))
                        .limit(WAREHOUSE_NUM_PER_REQUEST)
                        .offset(start)
        );
        return modelMapper.map(warehouses, new TypeToken<List<WarehouseBriefDTO>>() {
        }.getType());
    }

}
