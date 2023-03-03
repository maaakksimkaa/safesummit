package com.ssummit.service;


import com.ssummit.model.Role;
import com.ssummit.repository.RoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class RoleService {

  private final RoleRepository repository;

  public RoleService(RoleRepository repository) {
    this.repository = repository;
  }

  public List<Role> getList() {
    return repository.findAll();
  }

  public Role getOne(Long id) {
    return repository.findById(id).orElseThrow();
  }

}
