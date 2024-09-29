package com.mires.socialimpactbridgebackend.controllers.mainAccount;

import com.google.gson.GsonBuilder;
import com.mires.common.objects.accounts.firm.Firm;
import com.mires.common.objects.accounts.investor.Investor;
import com.mires.common.objects.accounts.ngo.NGO;
import com.mires.common.objects.accounts.user.User;
import com.mires.socialimpactbridgebackend.service.accounts.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Map;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/accounts")
public class MainAccountController {
    private final AccountServiceImpl accountService;

    @Autowired
    public MainAccountController(AccountServiceImpl accountService) {
        this.accountService = accountService;
    }

    @PostMapping(value = "/getAccount", produces = "application/json", consumes = "application/json")
    @CrossOrigin
    public ResponseEntity<String> getAccount(@RequestBody Map<String, Object> body) {
        Long id;
        try {
            id = Long.valueOf(body.get("id").toString());
            System.out.println(id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new GsonBuilder().create().toJson(Map.of("error", "Invalid id format")));
        }

        return ResponseEntity.ok(new GsonBuilder().serializeNulls().disableHtmlEscaping().setPrettyPrinting().create().toJson(accountService.getAccount(id)));
    }

    @PostMapping(value = "/create/User", produces = "application/json", consumes = "application/json")
    @CrossOrigin
    public ResponseEntity<String> createAccount(@RequestBody User account) {
        if (accountService.findAccountByEmail(account.getEmail())) {
            return ResponseEntity.badRequest().body(new GsonBuilder().create().toJson(Map.of("error", "Email already in use")));
        }
        accountService.createAccount(account);

        return ResponseEntity.ok(new GsonBuilder().create().toJson(Map.of("message", "Account created successfully")));
    }

    @PostMapping(value = "/create/Firm", produces = "application/json", consumes = "application/json")
    @CrossOrigin
    public ResponseEntity<String> createAccount(@RequestBody Firm account) {
        if (accountService.findAccountByEmail(account.getEmail())) {
            return ResponseEntity.badRequest().body(new GsonBuilder().create().toJson(Map.of("error", "Email already in use")));
        }
        accountService.createAccount(account);
        return ResponseEntity.ok(new GsonBuilder().create().toJson(Map.of("message", "Account created successfully")));
    }

    @PostMapping(value = "/create/NGO", produces = "application/json", consumes = "application/json")
    @CrossOrigin
    public ResponseEntity<String> createAccount(@RequestBody NGO account) {
        if (accountService.findAccountByEmail(account.getEmail())) {
            return ResponseEntity.badRequest().body(new GsonBuilder().create().toJson(Map.of("error", "Email already in use")));
        }
        accountService.createAccount(account);
        return ResponseEntity.ok(new GsonBuilder().create().toJson(Map.of("message", "Account created successfully")));
    }

    @PostMapping(value = "/create/Investor", produces = "application/json", consumes = "application/json")
    @CrossOrigin
    public ResponseEntity<String> createAccount(@RequestBody Investor account) {
        if (accountService.findAccountByEmail(account.getEmail())) {
            return ResponseEntity.badRequest().body(new GsonBuilder().create().toJson(Map.of("error", "Email already in use")));
        }
        accountService.createAccount(account);
        return ResponseEntity.ok(new GsonBuilder().create().toJson(Map.of("message", "Account created successfully")));
    }

    @PostMapping(value = "/login", produces = "application/json", consumes = "application/json")
    @CrossOrigin
    public ResponseEntity<String> login(@RequestBody Map<String, Object> body) {
        final String email = (String) body.get("email");
        final String password = (String) body.get("password");

        if (email == null || password == null) {
            return ResponseEntity.badRequest().body(new GsonBuilder().create().toJson(Map.of("error", "Invalid email or password")));
        }


        return accountService.login(email, password);
    }

    @PostMapping(value = "/addNgoMember", produces = "application/json", consumes = "application/json")
    @CrossOrigin
    public ResponseEntity<String> addMember(@RequestBody Map<String, Object> body) {
        Long ngoId;
        Long userId;

        try {
            ngoId = Long.valueOf(body.get("ngoId").toString());
            userId = Long.valueOf(body.get("userId").toString());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new GsonBuilder().create().toJson(Map.of("error", "Invalid ngoId or userId format")));
        }

        return accountService.addMember(ngoId, userId);
    }

    @PostMapping(value = "/removeNgoMember", produces = "application/json", consumes = "application/json")
    @CrossOrigin
    public ResponseEntity<String> removeMember(@RequestBody Map<String, Object> body) {
        Long ngoId;
        Long userId;

        try {
            ngoId = Long.valueOf(body.get("ngoId").toString());
            userId = Long.valueOf(body.get("userId").toString());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new GsonBuilder().create().toJson(Map.of("error", "Invalid ngoId or userId format")));
        }

        return accountService.removeMember(ngoId, userId);
    }

    @PostMapping(value = "/getNgoMembers", produces = "application/json", consumes = "application/json")
    @CrossOrigin
    public ResponseEntity<String> getNgoMembers(@RequestBody Map<String, Object> body) {
        Long ngoId;

        try {
            ngoId = Long.valueOf(body.get("ngoId").toString());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new GsonBuilder().create().toJson(Map.of("error", "Invalid ngoId format")));
        }

        return accountService.getNgoMembers(ngoId);
    }
}
