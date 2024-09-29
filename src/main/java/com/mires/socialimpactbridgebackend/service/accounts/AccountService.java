package com.mires.socialimpactbridgebackend.service.accounts;

import com.mires.common.objects.accounts.MainAccount;
import com.mires.common.objects.accounts.firm.Firm;
import com.mires.common.objects.accounts.investor.Investor;
import com.mires.common.objects.accounts.ngo.NGO;
import com.mires.common.objects.accounts.user.User;
import org.springframework.http.ResponseEntity;

public interface AccountService {
        MainAccount createAccount(MainAccount account);
        MainAccount getAccount(Long id);
        ResponseEntity<String> getAccountResponse(Long id);
        Firm createFirm(Firm firm);
        NGO createNGO(NGO ngo);
        Investor createInvestor(Investor investor);
        User createUser(User user);
}
