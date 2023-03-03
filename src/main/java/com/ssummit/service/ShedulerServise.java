package com.ssummit.service;

import com.ssummit.model.CheckpointMark;
import com.ssummit.model.Message;
import com.ssummit.model.Tour;
import com.ssummit.model.User;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
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

    //  http://www.cronmaker.com/?1 - для генерации крон записей наглядно
    @Scheduled(cron = "0 0/30 * 1/1 * *") //Every 30 minutes
    @Transactional()
    public void generateMessage() {
        List<Tour> list = tourService.getAllActiveTour();
        for (int i = 0; i < list.size(); i++) {
            CheckpointMark nowCheckpointMark = tourService.getNowCheckpointMark(list.get(i).getId());
            CheckpointMark lastCheckpointMark = tourService.getLastPassedCheckMark(list.get(i).getId());
            Message message = new Message();
            message.setCreatedBy("ADMIN");
            message.setCreatedDateTime(LocalDateTime.now());
            if (!lastCheckpointMark.isMessageSend() & lastCheckpointMark.getActualMarkedTime().isBefore(lastCheckpointMark.getScheduledMarkedTime())) {
                message.setMessageType(messageTypeService.getOne(1L));
                message.setTitle("Normal. Группа №" + list.get(i).getId() + " идущая по маршруту" + list.get(i).getRoute().getTitle());
                message.setDescription("Группа успешно прошла контрольную точку: " + lastCheckpointMark.getCheckpoint().getDescription() + " в заданное время");
                messageService.create(message);
//почта администрации сайта, только для примера кода, заблокировано так как ящика не существует.
//              sendMail("admin@safesummit.ru", message.getTitle(), message.getDescription());
//Отправка сообщения на почту участникам похода
                Set<User> users = list.get(i).getParticipants();
                for (User user : users) {
                    sendMail(user.getEmail(), message.getTitle(), message.getDescription());
                }
                lastCheckpointMark.setMessageSend(true);
            } else if ((!lastCheckpointMark.isMessageSend()
                    & lastCheckpointMark.getActualMarkedTime().isAfter(lastCheckpointMark.getScheduledMarkedTime()))) {
                message.setMessageType(messageTypeService.getOne(2L));
                message.setTitle("Warning. Группа №" + list.get(i).getId() + " идущая по маршруту" + list.get(i).getRoute().getTitle());
                message.setDescription("Группа прошла контрольную точку: " + lastCheckpointMark.getCheckpoint().getDescription() + " с задержкой в " +
                        lastCheckpointMark.getScheduledMarkedTime().until(lastCheckpointMark.getActualMarkedTime(), ChronoUnit.MINUTES) + "минут");
                messageService.create(message);
//почта администрации сайта, только для примера кода, заблокировано так как ящика не существует.
//              sendMail("admin@safesummit.ru", message.getTitle(), message.getDescription());
//Отправка сообщения на почту участникам похода
                Set<User> users = list.get(i).getParticipants();
                for (User user : users) {
                    sendMail(user.getEmail(), message.getTitle(), message.getDescription());
                }
                lastCheckpointMark.setMessageSend(true);
            } else if ((nowCheckpointMark.getScheduledMarkedTime().isBefore(LocalDateTime.now())
                    & nowCheckpointMark.getScheduledMarkedTime().until(LocalDateTime.now(), ChronoUnit.MINUTES) < 360)) {
                message.setMessageType(messageTypeService.getOne(2L));
                message.setTitle("Warning. Группа №" + list.get(i).getId() + " идущая по маршруту" + list.get(i).getRoute().getTitle());
                message.setDescription("Не пройдена контрольная точка: " + lastCheckpointMark.getCheckpoint().getDescription() + ". Задержка более " +
                        nowCheckpointMark.getScheduledMarkedTime().until(LocalDateTime.now(), ChronoUnit.MINUTES) + "минут");
                messageService.create(message);
//почта администрации сайта, только для примера кода, заблокировано так как ящика не существует.
//              sendMail("admin@safesummit.ru", message.getTitle(), message.getDescription());
//Отправка сообщения на почту участникам похода
                Set<User> users = list.get(i).getParticipants();
                for (User user : users) {
                    sendMail(user.getEmail(), message.getTitle(), message.getDescription());
                }
            } else if ((nowCheckpointMark.getScheduledMarkedTime().isBefore(LocalDateTime.now())
                    & nowCheckpointMark.getScheduledMarkedTime().until(LocalDateTime.now(), ChronoUnit.MINUTES) >= 360)) {
                message.setMessageType(messageTypeService.getOne(2L));
                message.setTitle("Alarm. Группа №" + list.get(i).getId() + " идущая по маршруту" + list.get(i).getRoute().getTitle());
                message.setDescription("Тревога! Не пройдена контрольная точка: " + lastCheckpointMark.getCheckpoint().getDescription() + ". Задержка более " +
                        nowCheckpointMark.getScheduledMarkedTime().until(LocalDateTime.now(), ChronoUnit.HOURS) + "часов");
                messageService.create(message);
//почта МЧС от фонаря, только для примера кода, заблокировано чтоб не засорять если ящик существует
//				sendMail("MCHS@MCHS.78.ru", message.getTitle(), message.getDescription());
//почта администрации сайта, только для примера кода, заблокировано так как ящика не существует.
//              sendMail("admin@safesummit.ru", message.getTitle(), message.getDescription());
//Отправка сообщения на почту участникам похода
                Set<User> users = list.get(i).getParticipants();
                for (User user : users) {
                    sendMail(user.getEmail(), message.getTitle(), message.getDescription());
                }
            }
        }
    }

    public void sendMail(String email, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send(message);
    }


}
