package com.ssummit;

import com.ssummit.model.Tour;
import com.ssummit.model.TourApplication;
import com.ssummit.model.User;

import java.util.Set;

public class TourApplicationTemplateFiller {

    private String applicationDate;
    private String firstGuideLastName;
    private String firstGuideName;
    private String firstGuideMiddleName;
    private String firstGuideAddress;
    private String firstGuidePhone;
    private String participantsListAsString;
    private String participantsAmount;
    private String startLocation;
    private String startDate;
    private String endDate;

    public TourApplicationTemplateFiller(Tour tour, TourApplication tourApplication) {
        this.applicationDate = "LocalDate.now().toString()";
        this.firstGuideLastName = tour.getPrimaryGuide().getLastName();
        this.firstGuideName = tour.getPrimaryGuide().getFirstName();
        this.firstGuideMiddleName = tour.getPrimaryGuide().getMiddleName();
        this.firstGuideAddress = tour.getPrimaryGuide().getAddress();
        this.firstGuidePhone = tour.getPrimaryGuide().getPhone();
        this.participantsAmount = String.valueOf(tour.getParticipants().size());
        this.startDate = tour.getStartDate().toString();
        this.endDate = tour.getEndDate().toString();
        StringBuilder participantsAsString = new StringBuilder();
        Set<User> participants = tour.getParticipants();
        for (User user:
             participants) {
            participantsAsString
                    .append(user.getLastName())
                    .append(" ")
                    .append(user.getFirstName())
                    .append(" ")
                    .append(user.getMiddleName())
                    .append(" ")
                    .append(user.getBirthDate())
                    .append(" ")
                    .append(user.getAddress())
                    .append(" ")
                    .append(user.getPhone())
                    .append("\n");
        }
        this.participantsListAsString = participantsAsString.toString();

    }

    public String getApplicationDate() {
        return applicationDate;
    }

    public String getFirstGuideLastName() {
        return firstGuideLastName;
    }

    public String getFirstGuideName() {
        return firstGuideName;
    }

    public String getFirstGuideMiddleName() {
        return firstGuideMiddleName;
    }

    public String getFirstGuideAddress() {
        return firstGuideAddress;
    }

    public String getFirstGuidePhone() {
        return firstGuidePhone;
    }

    public String getParticipantsListAsString() {
        return participantsListAsString;
    }

    public String getParticipantsAmount() {
        return participantsAmount;
    }

    public String getStartLocation() {
        return startLocation;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }
}
