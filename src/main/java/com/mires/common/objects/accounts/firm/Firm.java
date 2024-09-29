package com.mires.common.objects.accounts.firm;

import com.mires.common.enums.AccountType;
import com.mires.common.objects.accounts.MainAccount;
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
@DiscriminatorValue("FIRM")
public class Firm extends MainAccount {
    private String Name, KRS, NIP, REGON, country,  voivodeship, district, commune, city, postalCode, street, number, firmEmail, websiteURL;
    @Temporal(TemporalType.DATE)
    private Date establishmentDate;
    @ElementCollection // This allows you to store a collection of basic types or embeddable objects
    private List<String> interestedGoals; // Use String for UUIDs or Long for IDs
    private String description;
    private Double budget;

    public void addInterestedGoal(Firm firm, PkdCode pkdCode) {
        List<String> currentGoals = firm.getInterestedGoals();
        if (currentGoals == null) {
            currentGoals = new ArrayList<>();
        }
        currentGoals.add(pkdCode.getUuid().toString()); // Assuming ID is Long
        firm.setInterestedGoals(currentGoals);
    }

    public Firm() {
        super();
    }


    public AccountType getAccountType() {
        return AccountType.FIRM;
    }
}
