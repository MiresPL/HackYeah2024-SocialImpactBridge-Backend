package com.mires.socialimpactbridgebackend.service.accounts;

import com.google.gson.GsonBuilder;
import com.mires.common.objects.accounts.MainAccount;
import com.mires.common.objects.accounts.firm.Firm;
import com.mires.common.objects.accounts.investor.Investor;
import com.mires.common.objects.accounts.ngo.NGO;
import com.mires.common.objects.accounts.user.User;
import com.mires.socialimpactbridgebackend.repositories.accounts.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    private final MainAccountRepository mainAccountRepository;
    private final FirmRepository firmRepository;
    private final NGORepository ngoRepository;
    private final InvestorRepository investorRepository;
    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public AccountServiceImpl(MainAccountRepository mainAccountRepository, FirmRepository firmRepository, NGORepository ngoRepository, InvestorRepository investorRepository, UserRepository userRepository) {
        this.mainAccountRepository = mainAccountRepository;
        this.firmRepository = firmRepository;
        this.ngoRepository = ngoRepository;
        this.investorRepository = investorRepository;
        this.userRepository = userRepository;
    }

    public List<MainAccount> getAllMainAccounts() {
        return mainAccountRepository.findAll();
    }

    public MainAccount saveMainAccount(MainAccount mainAccount) {
        return mainAccountRepository.save(mainAccount);
    }

    public MainAccount createAccount(MainAccount account) {
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        return mainAccountRepository.save(account);
    }

    public MainAccount getAccount(Long id) {
        return mainAccountRepository.findById(id).orElse(null);
    }

    public boolean findAccountByEmail(String email) {
        return mainAccountRepository.findByEmail(email) != null;
    }

    @Override
    public ResponseEntity<String> getAccountResponse(Long id) {
        final MainAccount account = mainAccountRepository.findById(id).orElse(null);

        if (account == null) {
            return ResponseEntity.badRequest().body("Account not found");
        }

        return ResponseEntity.ok(new GsonBuilder().serializeNulls().setPrettyPrinting().disableHtmlEscaping().create().toJson(account));
    }

    public Firm createFirm(Firm firm) {
        return firmRepository.save(firm);
    }

    public NGO createNGO(NGO ngo) {
        return ngoRepository.save(ngo);
    }

    public Investor createInvestor(Investor investor) {
        return investorRepository.save(investor);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }


    public ResponseEntity<String> login(String email, String password) {

        final MainAccount account = mainAccountRepository.findByEmail(email);
        if (account == null) {
            return ResponseEntity.status(404).body("Account not found");
        }

        if (!passwordEncoder.matches(password, account.getPassword())) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }



        return ResponseEntity.ok(new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().serializeNulls().create().toJson(account));
    }

    public ResponseEntity<String> addMember(final Long ngoId, final Long userId) {
        final NGO ngo = ngoRepository.findById(ngoId).orElse(null);
        final User user = userRepository.findById(userId).orElse(null);

        if (ngo == null || user == null) {
            return ResponseEntity.badRequest().body("NGO or User was not found");
        }

        ngo.addMember(ngo, user);
        user.setNgo(ngo);
        ngoRepository.save(ngo);
        userRepository.save(user);
        return ResponseEntity.ok("Member added successfully");
    }

    public ResponseEntity<String> removeMember(final Long ngoId, final Long userId) {
        final NGO ngo = ngoRepository.findById(ngoId).orElse(null);
        final User user = userRepository.findById(userId).orElse(null);

        if (ngo == null || user == null) {
            return ResponseEntity.badRequest().body("NGO or user not found");
        }

        ngo.removeMember(ngo, user);
        user.setNgo(null);
        userRepository.save(user);
        ngoRepository.save(ngo);
        return ResponseEntity.ok("Member removed successfully");
    }

    public ResponseEntity<String> getNgoMembers(final Long ngoId) {
        final NGO ngo = ngoRepository.findById(ngoId).orElse(null);

        if (ngo == null) {
            return ResponseEntity.badRequest().body("NGO not found");
        }

        ngo.getMembers().forEach(member -> {
            member.setNgo(ngo);
            userRepository.save(member);
        });


        StringBuilder response = new StringBuilder();
        response.append("[\n");

        for (int i = 0; i < ngo.getMembers().size(); i++) {
            User member = ngo.getMembers().get(i);
            response.append("  {\n");
            response.append("    \"id\": \"").append(member.getId()).append("\",\n");
            response.append("    \"email\": \"").append(member.getEmail()).append("\",\n");
            response.append("    \"name\": \"").append(member.getName()).append("\",\n");
            response.append("    \"surname\": \"").append(member.getSurname()).append("\",\n");
            response.append("    \"dateOfBirth\": \"").append(member.getDateOfBirth()).append("\",\n");
            response.append("  }");

            // Only append a comma if it's not the last element
            if (i <  ngo.getMembers().size() - 1) {
                response.append(",\n");
            }
        }

        response.append("\n]");


        return ResponseEntity.ok(response.toString());
    }

}
