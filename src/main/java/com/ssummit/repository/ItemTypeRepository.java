package com.ssummit.repository;

import com.ssummit.model.ItemType;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ItemTypeRepository extends GenericRepository<ItemType> {

    Set<ItemType> findAllByIdIn(Set<Long> ids);
}
