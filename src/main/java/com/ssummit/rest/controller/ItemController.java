package com.ssummit.rest.controller;

import com.ssummit.dto.ItemDto;
import com.ssummit.mapper.ItemMapper;
import com.ssummit.model.Item;
import com.ssummit.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/rest/item")
public class ItemController extends GenericController<Item, ItemDto> {
	private final ItemService service;
	private final ItemMapper mapper;

	public ItemController(ItemService service, ItemMapper mapper) {
		super(service, mapper);
		this.mapper = mapper;
		this.service = service;
	}

}

