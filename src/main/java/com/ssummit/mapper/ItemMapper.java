package com.ssummit.mapper;

import com.ssummit.dto.ItemDto;
import com.ssummit.model.Item;
import com.ssummit.repository.ItemRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper extends GenericMapper<Item, ItemDto> {

    private final ModelMapper mapper;

    private final ItemRepository itemRepository;

    protected ItemMapper(ModelMapper mapper, ItemRepository itemRepository) {
        super(mapper, Item.class, ItemDto.class);
        this.mapper = mapper;
        this.itemRepository = itemRepository;
    }
}
