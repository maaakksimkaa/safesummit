package com.ssummit.service;

import com.ssummit.model.ItemType;
import com.ssummit.repository.ItemTypeRepository;
import org.springframework.stereotype.Service;

@Service
public class ItemTypeService extends GenericService<ItemType> {
	private final ItemTypeRepository repository;

	protected ItemTypeService(ItemTypeRepository repository) {
		super(repository);
		this.repository = repository;
	}
}
