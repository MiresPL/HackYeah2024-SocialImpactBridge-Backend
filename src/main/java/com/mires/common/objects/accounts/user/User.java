package com.mires.common.objects.accounts.user;

import com.mires.common.enums.AccountType;
import com.mires.common.objects.accounts.MainAccount;
import com.mires.common.objects.accounts.ngo.NGO;
import com.mires.common.objects.experience.Experience;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@Entity
@DiscriminatorValue("USER")
public class User extends MainAccount {
    private String name, surname;
    @Temporal(jakarta.persistence.TemporalType.DATE)
    private Date dateOfBirth;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user") // Set the reverse mapping
    private List<Experience> experienceList;

    @ManyToOne // Many users can belong to one NGO
    @JoinColumn(name = "ngo_id", nullable = true) // Foreign key to User table
    private NGO ngo;

    public User() {
        super();
    }


    public void addExperience(Experience experience) {
        if (experienceList == null) {
            experienceList = new ArrayList<>();
        }
        experienceList.add(experience);
    }

    public AccountType getAccountType() {
        return AccountType.USER;
    }
}
