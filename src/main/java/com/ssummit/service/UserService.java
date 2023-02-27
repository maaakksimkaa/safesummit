package com.ssummit.service;

import com.ssummit.dto.AddTourDto;
import com.ssummit.dto.LoginDTO;
import com.ssummit.model.Tour;
import com.ssummit.model.User;
import com.ssummit.repository.RoleRepository;
import com.ssummit.repository.TourRepository;
import com.ssummit.repository.UserRepository;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class UserService extends GenericService<User> {
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final TourRepository tourRepository;
	private final TourService tourService;
	private final RoleRepository roleRepository;
	private final JavaMailSender javaMailSender;

	public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, TourRepository tourRepository, TourService tourService, RoleRepository roleRepository, JavaMailSender javaMailSender) {
		super(userRepository);
		this.userRepository = userRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.tourRepository = tourRepository;
		this.tourService = tourService;
		this.roleRepository = roleRepository;
		this.javaMailSender = javaMailSender;
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

	public User getUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public void sendChangePasswordEmail(String email) {
		UUID uuid = UUID.randomUUID();
		User user = getUserByEmail(email);

		user.setChangePasswordToken(uuid.toString());
		update(user);
		SimpleMailMessage message = createMessage(email,
				"Восстановление пароля на сайте Походник",
				"Добрый день. Вы получили это письмо, так как с вашего аккаунта была отправлена заявка <br> на восстановление пароля.\n "
						+ "Для восстановления пароля перейдите по ссылке: http://localhost:99292/users/change-password?uuid=" + uuid);
		javaMailSender.send(message);
	}

	private SimpleMailMessage createMessage(String email, String subject, String text) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(email);
		message.setSubject(subject);
		message.setText(text);
		return message;
	}

	private User findByToken(String password) {
		return userRepository.findByChangePasswordToken(password);
	}

	public void changePassword(String uuid, String password) {
		User user = findByToken(uuid);
		user.setChangePasswordToken(null);
		user.setPassword(bCryptPasswordEncoder.encode(password));
		update(user);
	}

	public boolean checkPassword(LoginDTO loginDTO) {
		System.out.println(loginDTO);
		return bCryptPasswordEncoder.matches(loginDTO.getPassword(),
				getByUserName(loginDTO.getLogin()).getPassword());
	}
}