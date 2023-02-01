package com.ssummit.service;

import com.ssummit.model.GenericModel;
import com.ssummit.repository.GenericRepository;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

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
		return repository.findAll();
	}

	public T getOne(Long id) {
		return repository.findById(id).orElseThrow(() -> new NotFoundException("Row with such ID: " + id + " not found"));
	}

	public T create(T object) {
		return repository.save(object);
	}

	public T update(T object) {
		return repository.save(object);
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}

}
