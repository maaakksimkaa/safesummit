package com.ssummit.dto;

import com.ssummit.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TourGuidesAndParticipantsDto /*extends TourDto*/ {

    private String primaryGuide;
    private String secondaryGuide;
    private Set<String> participants;
}
