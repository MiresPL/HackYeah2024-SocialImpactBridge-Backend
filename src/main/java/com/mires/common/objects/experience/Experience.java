package com.mires.common.objects.experience;

import com.mires.common.objects.accounts.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
@Entity
public class Experience {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long uuid;
    private String eventName;
    @Temporal(jakarta.persistence.TemporalType.DATE)
    private Date eventStartDate, eventEndDate;
    private String position, eventDescription;

    @ManyToOne // Many experiences can belong to one user
    @JoinColumn(name = "user_id", nullable = false) // Foreign key to User table
    private User user;

    public Experience() {
    }

    /*public String getEventDuration() {
        return DateHelper.durationToString(eventEndDate.getTime() - eventStartDate.getTime(), false);
    }*/
}
