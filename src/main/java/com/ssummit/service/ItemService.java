package com.ssummit.service;

import com.ssummit.model.Item;
import com.ssummit.repository.ItemRepository;
import org.springframework.stereotype.Service;

@Service
public class ItemService extends GenericService<Item>{
	private final ItemRepository repository;

	protected ItemService(ItemRepository repository) {
		super(repository);
		this.repository = repository;
	}
}
