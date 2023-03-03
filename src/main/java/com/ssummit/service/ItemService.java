package com.ssummit.service;

import com.ssummit.model.Item;
import com.ssummit.repository.ItemRepository;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

@Service
public class ItemService extends GenericService<Item>{
	private final ItemRepository repository;

	protected ItemService(ItemRepository repository) {
		super(repository);
		this.repository = repository;
	}

	@Override
	public Item create(Item item) {
		item.setCreatedBy("ADMIN");
		item.setUpdatedBy("ADMIN");
		return super.create(item);
	}

	@Override
	public Item update(Item item) {
		item.setUpdatedBy("ADMIN");
		return super.update(item);
	}

	@Override
	public void delete(Long id) {
		Item item = repository.findById(id).orElseThrow(() -> new NotFoundException("Row with such ID: " + id + "not found"));
		item.setUpdatedBy("ADMIN");
		super.update(item);
	}
}
