package com.ssummit.rest.controller;

import com.ssummit.dto.ItemTypeDto;
import com.ssummit.mapper.ItemTypeMapper;
import com.ssummit.model.ItemType;
import com.ssummit.service.ItemTypeService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping("/item-type")
public class ItemTypeController extends GenericController<ItemType, ItemTypeDto> {
	private final ItemTypeService service;
	private final ItemTypeMapper mapper;

	public ItemTypeController(ItemTypeService service, ItemTypeMapper mapper) {
		super(service, mapper);
		this.mapper = mapper;
		this.service = service;
	}

}
