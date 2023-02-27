package com.ssummit.service;

import com.ssummit.dto.AddTourDto;
import com.ssummit.model.Tour;
import com.ssummit.model.User;
import com.ssummit.repository.RoleRepository;
import com.ssummit.repository.TourRepository;
import com.ssummit.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService extends GenericService<User> {

	private final TourRepository tourRepository;
	private final TourService tourService;
	private final UserRepository repository;
	private final RoleRepository roleRepository;
	private final RoleService roleService;

	protected UserService (UserRepository repository,
						   TourRepository tourRepository,
						   TourService tourService,
						   RoleRepository roleRepository, RoleService roleService){
		super(repository);
		this.tourService = tourService;
		this.tourRepository = tourRepository;
		this.repository = repository;
		this.roleRepository = roleRepository;
		this.roleService = roleService;
	}

	@Override
	public User create(User user) {
		user.setCreatedBy("REGISTRATION");
		user.setDeleted(false);
		user.setRole(roleService.getOne(2L));
		//user.setPassword();
		return repository.save(user);
	}

	public User createGuide(User user) {
		user.setCreatedBy("ADMIN");
		user.setDeleted(false);
		user.setRole(roleService.getOne(3L));
		//user.setPassword();
		return repository.save(user);
	}

	public User createSpectator(User user) {
		user.setCreatedBy("пользователь" /* должен прописываться пользователь, создавший этого наблюдателя */);
		user.setDeleted(false);
		user.setRole(roleService.getOne(4L));
		//user.setPassword();
		return repository.save(user);
	}

	public User addTour(AddTourDto addTourDto) {
		User user = getOne(addTourDto.getUserId());
		Tour tour = tourService.getOne(addTourDto.getTourId());
		user.getAssignedTours().add(tour);
		return update(user);
	}

	public User revokeTour(AddTourDto addTourDto) {
		User user = getOne(addTourDto.getUserId());
		Tour tour = tourService.getOne(addTourDto.getTourId());
		user.getAssignedTours().remove(tour);
		return update(user);
	}

	public List<User> getAllGuides() {
		return repository.findAllByRole(roleService.getOne(3L)).stream().toList();
	}

	public List<User> getAllParticipants() {
		return repository.findAllByRole(roleService.getOne(2L)).stream().toList();
	}

}