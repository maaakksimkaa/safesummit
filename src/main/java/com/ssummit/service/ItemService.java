package com.ssummit.service;

import com.ssummit.model.Item;
import com.ssummit.repository.ItemRepository;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.time.LocalDateTime;

@Service
public class ItemService extends GenericService<Item> {
    private final ItemRepository repository;

    protected ItemService(ItemRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public Item create(Item item) {
        item.setIsDeleted(false);
        item.setCreatedBy("OTLADKA");
        item.setCreatedDateTime(LocalDateTime.now());
        return super.create(item);
    }

    @Override
    public Item update(Item item) {
        item.setUpdatedBy("OTLADKA");
        item.setUpdatedDateTime(LocalDateTime.now());
        return super.update(item);
    }

    @Override
    public void delete(Long id) {
        Item item = repository.findById(id).orElseThrow(() -> new NotFoundException("Row with such ID: " + id + "not found"));
        item.setDeletedBy("OTLADKA");
        item.setIsDeleted(true);
        item.setDeletedDateTime(LocalDateTime.now());
        super.update(item);
    }
}
