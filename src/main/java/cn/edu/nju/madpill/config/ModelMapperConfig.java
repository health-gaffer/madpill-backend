package cn.edu.nju.madpill.config;

import cn.edu.nju.madpill.domain.Warehouse;
import cn.edu.nju.madpill.dto.ContentWrapper;
import cn.edu.nju.madpill.dto.WarehouseDTO;
import com.alibaba.fastjson.JSON;
import org.modelmapper.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * <p>
 * <p>
 *
 * @author Charles
 * @date 2020/2/27
 */
@Configuration
public class ModelMapperConfig {

    private Provider<WarehouseDTO> WarehouseDtoProvider = new AbstractProvider<WarehouseDTO>() {
        @Override
        public WarehouseDTO get() {
            return WarehouseDTO.builder().build();
        }
    };

    private Converter<Warehouse, WarehouseDTO> warehouseWarehouseDTOConverter = new AbstractConverter<Warehouse, WarehouseDTO>() {
        @Override
        protected WarehouseDTO convert(Warehouse warehouse) {
            return WarehouseDTO.builder()
                    .name(warehouse.getName())
                    .indication(wrap(warehouse.getIndication()))
                    .contraindication(wrap(warehouse.getContraindication()))
                    .build();
        }

        private String wrap(String toWrap) {
            ContentWrapper wrapper;
            if (null == toWrap) {
                wrapper = new ContentWrapper("");
            } else {
                wrapper = new ContentWrapper(toWrap);
            }
            return JSON.toJSONString(wrapper);
        }
    };

    @Bean
    @Scope("singleton")
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();

        mapper.createTypeMap(Warehouse.class, WarehouseDTO.class);
        mapper.addConverter(warehouseWarehouseDTOConverter);
        mapper.getTypeMap(Warehouse.class, WarehouseDTO.class).setProvider(WarehouseDtoProvider);
        return mapper;
    }
}
