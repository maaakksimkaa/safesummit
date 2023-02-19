package com.ssummit.mapper;

import com.ssummit.dto.ItemTypeDto;
import com.ssummit.model.ItemType;
import com.ssummit.repository.ItemRepository;
import com.ssummit.repository.ItemTypeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ItemTypeMapper extends GenericMapper<ItemType, ItemTypeDto> {

    private final ModelMapper mapper;

    private final ItemTypeRepository repository;

    protected ItemTypeMapper(ModelMapper mapper, ItemTypeRepository repository) {
        super(mapper, ItemType.class, ItemTypeDto.class);
        this.mapper = mapper;
        this.repository = repository;
    }
}
