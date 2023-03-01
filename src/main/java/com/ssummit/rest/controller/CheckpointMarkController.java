package com.ssummit.rest.controller;

import com.ssummit.dto.MarkCheckpointDto;
import com.ssummit.mapper.CheckpointMarkMapper;
import com.ssummit.model.CheckpointMark;
import com.ssummit.service.CheckpointMarkService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping("/checkpoint-mark")
public class CheckpointMarkController {

	private final CheckpointMarkService service;
	private final CheckpointMarkMapper mapper;

	public CheckpointMarkController(CheckpointMarkService service, CheckpointMarkMapper mapper) {
		this.mapper = mapper;
		this.service = service;
	}

	@Operation(description = "Получить список всех записей", method = "GetAll")
	@GetMapping("/list")
	public List<CheckpointMark> list() {
		return service.getList();
	}

	@Operation(description = "Создать запись", method = "Create")
	@PostMapping
	public CheckpointMark create(@RequestBody CheckpointMark object) {
		return service.create(object);
	}

	@Operation(description = "Обновить запись", method = "Update")
	@PutMapping("/{id}")
	public CheckpointMark update(@RequestBody CheckpointMark object, @PathVariable Long id) {
		object.setId(id);
		return service.update(object);
	}

	@Operation(description = "Отметить прохождение контрольной точки")
	@PutMapping("/mark-checkpoint")
	public CheckpointMark markCheckpoint(@RequestBody MarkCheckpointDto markCheckpointDto) {
		return service.markCheckpoint(markCheckpointDto);
	}

//	@Operation(description = "Удалить запись", method = "Delete")
//	@DeleteMapping("/{id}")
//	public void delete(@PathVariable Long id) {
//		service.delete(id);
//	}

}
