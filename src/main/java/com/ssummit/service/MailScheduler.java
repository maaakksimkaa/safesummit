package com.ssummit.service;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class MailScheduler {

  private final UserService userService;
  private final TourService tourService;
  private final JavaMailSender javaMailSender;

  public MailScheduler(UserService userService, TourService tourService, JavaMailSender javaMailSender) {
    this.userService = userService;
    this.tourService = tourService;
    this.javaMailSender = javaMailSender;
  }
//  @Scheduled(cron = "0 0/30 * 1/1 * ? *") //Every 30 minutes
//  public void generateMessage() {
//    LinkedList<Tour> list= (LinkedList<Tour>) tourService.listAll();
//    while (list.iterator().hasNext()){
//      for (int i=0;i<list.size();i++){
//        if (list.get(i).getStartDate().compareTo(LocalDate.now())&now().isBefore(list.get(i).getEndDate())) {
//          Set<CheckpointMark> setCheckpointMarks = list.get(i).getCheckpointMarks();
//          for (CheckpointMark s:setCheckpointMarks) {
//            if (now().isAfter(s.getScheduledMarkedTime())&s.getActualMarkedTime()==null) {
//              if (Period.between(now(),s.getScheduledMarkedTime()).ge)
//          }
//      }
//
//
//          }
//        }
//      }
//
//    }
//  }
  //http://www.cronmaker.com/?1 - для генерации крон записей наглядно
//  @Scheduled(cron = "0 0/30 * 1/1 * ? *") //Every 30 minutes
//  public void sendMail() {
//    SimpleMailMessage message = new SimpleMailMessage();
//    message.setTo("ur email");
//    message.setSubject("TEST");
//    message.setText("TEST: " + LocalDateTime.now());
//    javaMailSender.send(message);
//    System.out.println("Scheduled task running");
//  }

}
