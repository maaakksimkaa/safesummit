package com.ssummit.service;

import com.ssummit.model.GenericModel;
import com.ssummit.repository.GenericRepository;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Абстрактный сервис который хранит в себе реализацию CRUD операций по умолчанию
 * Если реализация отличная от того что представлено в этом классе,
 * то она переопределяется в сервисе для конкретной сущности
 *
 * @param <T> - Сущность с которой мы работаем
 */
@Service
public abstract class GenericService<T extends GenericModel> {

    //Инжектим абстрактный репозиторий для работы с базой данных
    private final GenericRepository<T> repository;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    protected GenericService(GenericRepository<T> repository) {
        this.repository = repository;
    }

    public List<T> listAll() {
        return repository.findAll().stream().filter(obj -> !obj.getIsDeleted()).toList();
    }

    public T getOne(Long id) {
        T object = repository.findById(id).orElseThrow(() -> new NotFoundException("Row with such ID: " + id + " not found"));
        if (object.getIsDeleted()) {
            throw new NotFoundException("Row with such ID: " + id + " was deleted");
        }
        return object;
    }

    public T create(T object) {
        object.setIsDeleted(false);
        object.setCreatedBy("OTLADKA");
        object.setCreatedDateTime(LocalDateTime.now());
//		object.setUpdatedDateTime(LocalDateTime.now());
        return repository.save(object);
    }

    public T update(T object) {
        object.setUpdatedBy("OTLADKA");
        object.setUpdatedDateTime(LocalDateTime.now());
        return repository.save(object);
    }

    public void delete(Long id) {
        T object = repository.findById(id).orElseThrow(() -> new NotFoundException("Row with such ID: " + id + " not found"));
        object.setDeletedBy("OTLADKA");
        object.setIsDeleted(true);
        object.setDeletedDateTime(LocalDateTime.now());
        repository.save(object);
    }

}
