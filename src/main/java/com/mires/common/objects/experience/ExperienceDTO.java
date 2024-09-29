package com.mires.common.objects.experience;

import lombok.Data;

import java.util.Date;

@Data
public class ExperienceDTO {
    private String eventName;
    private Date eventStartDate;
    private Date eventEndDate;
    private String position;
    private String eventDescription;
}
