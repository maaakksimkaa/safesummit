package com.ssummit.service;

import com.ssummit.model.CheckpointMark;
import com.ssummit.model.Message;
import com.ssummit.model.Tour;
import com.ssummit.model.User;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

@Component
public class ShedulerServise {

	private final UserService userService;
	private final TourService tourService;
	private final MessageService messageService;
	private final MessageTypeService messageTypeService;
	private final JavaMailSender javaMailSender;

	public ShedulerServise(UserService userService,
	                       TourService tourService,
	                       MessageService messageService,
	                       MessageTypeService messageTypeService,
	                       JavaMailSender javaMailSender) {
		this.userService = userService;
		this.tourService = tourService;
		this.messageService = messageService;
		this.messageTypeService = messageTypeService;
		this.javaMailSender = javaMailSender;
	}


	//	@Scheduled(cron = "0 0/1 * 1/1 * *") //Every 30 minutes
	public void generateMessage() {
		List<Tour> list = tourService.getAllActiveTour();
		for (int i = 0; i < list.size(); i++) {
			Set<CheckpointMark> checkpointMarks = tourService.getOne(list.get(i).getId()).getCheckpointMarks();
			CheckpointMark lastCheckpointMark = checkpointMarks.stream()
					.max(Comparator.comparing(CheckpointMark::getActualMarkedTime))
					.get();
			CheckpointMark nowCheckpointMark = checkpointMarks.stream()
					.min(Comparator.comparing(CheckpointMark::getScheduledMarkedTime))
					.get();
			Message message = new Message();
			message.setCreatedBy("ADMIN");
			message.setCreatedDateTime(LocalDateTime.now());
			if (!lastCheckpointMark.isMessageSend() & lastCheckpointMark.getActualMarkedTime().isBefore(lastCheckpointMark.getScheduledMarkedTime())) {
				message.setMessageType(messageTypeService.getOne(1L));
				message.setTitle("normal");
				message.setDescription("Группа успешно прошла контрольную точку в заданное время");
				messageService.create(message);
				Set<User> users=list.get(i).getParticipants();
				for (User user : users) {
					sendMail(user.getEmail(),message.getTitle(),message.getDescription());}
				lastCheckpointMark.setMessageSend(true);
			} else if ((!lastCheckpointMark.isMessageSend()
					& lastCheckpointMark.getActualMarkedTime().isAfter(lastCheckpointMark.getScheduledMarkedTime()))) {
				message.setMessageType(messageTypeService.getOne(2L));
				message.setTitle("warning");
				message.setDescription("Группа успешно прошла контрольную точку с задержкой в " +
						lastCheckpointMark.getScheduledMarkedTime().until(LocalDateTime.now(), ChronoUnit.MINUTES) + "минут");
				messageService.create(message);
				Set<User> users=list.get(i).getParticipants();
				for (User user : users) {
					sendMail(user.getEmail(),message.getTitle(),message.getDescription());}
				lastCheckpointMark.setMessageSend(true);
			} else if ((nowCheckpointMark.getScheduledMarkedTime().isBefore(LocalDateTime.now())
					& nowCheckpointMark.getScheduledMarkedTime().until(LocalDateTime.now(), ChronoUnit.MINUTES) < 360)) {
				message.setMessageType(messageTypeService.getOne(2L));
				message.setTitle("warning");
				message.setDescription("Не пройдена контрольная точка. Задержка более " +
						nowCheckpointMark.getScheduledMarkedTime().until(LocalDateTime.now(), ChronoUnit.MINUTES) + "минут");
				messageService.create(message);
				Set<User> users=list.get(i).getParticipants();
				for (User user : users) {
					sendMail(user.getEmail(),message.getTitle(),message.getDescription());}
			} else if ((nowCheckpointMark.getScheduledMarkedTime().isBefore(LocalDateTime.now())
					& nowCheckpointMark.getScheduledMarkedTime().until(LocalDateTime.now(), ChronoUnit.MINUTES) >= 360)) {
				message.setMessageType(messageTypeService.getOne(2L));
				message.setTitle("Alarm");
				message.setDescription("Тревога! Не пройдена контрольная точка. Задержка более " +
						nowCheckpointMark.getScheduledMarkedTime().until(LocalDateTime.now(), ChronoUnit.HOURS) + "часов");
				messageService.create(message);
				Set<User> users=list.get(i).getParticipants();
				for (User user : users) {
					sendMail(user.getEmail(),message.getTitle(),message.getDescription());}
			}
		}
	}

	public void sendMail(String email, String subject, String text){
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(email);
		message.setSubject(subject);
		message.setText(text);
		javaMailSender.send(message);
	}
	//  http://www.cronmaker.com/?1 - для генерации крон записей наглядно
//	@Scheduled(cron = "0 0/1 * 1/1 * *") //Every 1 minutes
//	public void sendMail() {
//		SimpleMailMessage message = new SimpleMailMessage();
//		message.setTo("evgeniibash@yandex.ru");
//		message.setSubject("TEST");
//		message.setText("TEST: " + LocalDateTime.now());
//		javaMailSender.send(message);
//		System.out.println("Scheduled task running");
//	}

}
