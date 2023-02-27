package com.ssummit.service;

import com.ssummit.dto.LoginDTO;
import com.ssummit.dto.AddTourDto;
import com.ssummit.model.Tour;
import com.ssummit.model.User;
import com.ssummit.repository.RoleRepository;
import com.ssummit.repository.TourRepository;
import com.ssummit.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService extends GenericService<User> {
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	protected UserService (UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder){
		super(userRepository);
		this.userRepository=userRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	public boolean checkPassword(LoginDTO loginDTO) {
		System.out.println(loginDTO);
		return bCryptPasswordEncoder.matches(loginDTO.getPassword(),
				getByUserName(loginDTO.getLogin()).getPassword());

	private final TourRepository tourRepository;
	private final TourService tourService;
	private final UserRepository userRepository;
	private final RoleRepository roleRepository;

	protected UserService (UserRepository repository,
						   TourRepository tourRepository,
						   TourService tourService,
						   UserRepository userRepository,
						   RoleRepository roleRepository){
		super(repository);
		this.tourService = tourService;
		this.tourRepository = tourRepository;
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
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
		return userRepository.findAllByRole(roleRepository.findById(3L).get()).stream().toList();
	}

	public User getByUserName(final String name) {
		return userRepository.findUserByLogin(name);
	}


	public List<User> getAllParticipants() {
		return userRepository.findAllByRole(roleRepository.findById(2L).get()).stream().toList();
	}

}