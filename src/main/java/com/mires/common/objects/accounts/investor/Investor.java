package com.mires.common.objects.accounts.investor;

import com.mires.common.enums.AccountType;
import com.mires.common.objects.accounts.MainAccount;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
@Entity
@DiscriminatorValue("INVESTOR")
public class Investor extends MainAccount {
    private String name, surname;
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    public Investor() {
        super();
    }
    

    public AccountType getAccountType() {
        return AccountType.INVESTOR;
    }
}
