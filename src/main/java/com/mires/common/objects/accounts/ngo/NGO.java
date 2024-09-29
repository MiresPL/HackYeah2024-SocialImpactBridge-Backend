package com.mires.common.objects.accounts.ngo;

import com.mires.common.enums.AccountType;
import com.mires.common.objects.accounts.MainAccount;
import com.mires.common.objects.accounts.user.User;
import com.mires.common.objects.pkdCode.PkdCode;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@Entity
@DiscriminatorValue("NGO")
public class NGO extends MainAccount {
    private String Name, KRS, NIP, REGON, country,  voivodeship, district, commune, city, postalCode, street, number, firmEmail, websiteURL;
    @Temporal(TemporalType.DATE)
    private Date establishmentDate;
    @ElementCollection
    private List<String> goals;
    private String activityGoal;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "ngo") // Set the reverse mapping
    private List<User> members;

    public void addGoal(NGO ngo, PkdCode pkdCode) {
        List<String> currentGoals = ngo.getGoals();
        if (currentGoals == null) {
            currentGoals = new ArrayList<>();
        }
        currentGoals.add(pkdCode.getUuid().toString());
        ngo.setGoals(currentGoals);
    }

    public void addMember(NGO ngo, User user) {
        List<User> currentMembers = ngo.getMembers();
        if (currentMembers == null) {
            currentMembers = new ArrayList<>();
        }
        currentMembers.add(user);
        ngo.setMembers(currentMembers);
    }

    public void removeMember(NGO ngo, User user) {
        List<User> currentMembers = ngo.getMembers();
        if (currentMembers == null) {
            return;
        }
        currentMembers.remove(user);
        ngo.setMembers(currentMembers);
    }

    public NGO() {
        super();
    }



    public AccountType getAccountType() {
        return AccountType.NGO;
    }
}
