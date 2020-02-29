package cn.edu.nju.madpill.config;

import cn.edu.nju.madpill.domain.Warehouse;
import cn.edu.nju.madpill.dto.ContentWrapper;
import cn.edu.nju.madpill.dto.WarehouseDTO;
import com.alibaba.fastjson.JSON;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
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

    private Converter<Warehouse, WarehouseDTO> warehouseWarehouseDTOConverter = new AbstractConverter<Warehouse, WarehouseDTO>() {
        @Override
        protected WarehouseDTO convert(Warehouse warehouse) {
            return WarehouseDTO.builder()
                    .name(warehouse.getName())
                    .indication(wrap(warehouse.getIndication()))
                    .contraindication(wrap(warehouse.getContraindication()))
                    .build();
        }
    };

    /**
     * 将内容包装为 JSON 字符串
     */
    private String wrap(String toWrap) {
        ContentWrapper wrapper;
        if (null == toWrap) {
            wrapper = new ContentWrapper("");
        } else {
            wrapper = new ContentWrapper(toWrap);
        }
        return JSON.toJSONString(wrapper);
    }

    @Bean
    @Scope("singleton")
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.addConverter(warehouseWarehouseDTOConverter);
        return mapper;
    }
}
