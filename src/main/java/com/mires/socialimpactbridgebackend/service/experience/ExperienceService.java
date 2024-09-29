package com.mires.socialimpactbridgebackend.service.experience;

import com.mires.common.objects.accounts.MainAccount;
import com.mires.common.objects.accounts.user.User;
import com.mires.common.objects.experience.Experience;
import com.mires.common.objects.experience.ExperienceDTO;
import com.mires.socialimpactbridgebackend.repositories.experience.ExperienceRepository;
import com.mires.socialimpactbridgebackend.service.accounts.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExperienceService {
    @Autowired
    private ExperienceRepository experienceRepository;

    @Autowired
    private AccountService accountService;

    // src/main/java/com/mires/socialimpactbridgebackend/service/experience/ExperienceService.java

    public ResponseEntity<String> addExperienceToAccount(Long accountId, ExperienceDTO experienceDTO) {
        MainAccount optionalAccount = accountService.getAccount(accountId);
        if (optionalAccount == null) {
            return ResponseEntity.badRequest().body("Account not found");
        }
        User user = (User) optionalAccount;
        Experience experience = new Experience();
        experience.setEventName(experienceDTO.getEventName());
        experience.setEventStartDate(experienceDTO.getEventStartDate());
        experience.setEventEndDate(experienceDTO.getEventEndDate());
        experience.setPosition(experienceDTO.getPosition());
        experience.setEventDescription(experienceDTO.getEventDescription());
        experience.setUser(user); // Set the user field

        user.addExperience(experience); // Add experience to the account
        experienceRepository.save(experience); // Save experience (or mainAccount.save() if you cascade)
        return ResponseEntity.ok("Experience added successfully");
    }

    public ResponseEntity<String> getUserExperience(Long userId) {
        MainAccount optionalAccount = accountService.getAccount(userId);
        if (optionalAccount == null) {
            return ResponseEntity.badRequest().body("Account not found");
        }

        User user = (User) optionalAccount;
        List<Experience> experiences = user.getExperienceList();

        if (experiences == null || experiences.isEmpty()) {
            return ResponseEntity.badRequest().body("No experiences found");
        }

        // Use StringBuilder to build the JSON response
        StringBuilder response = new StringBuilder();
        response.append("[\n");

        for (int i = 0; i < experiences.size(); i++) {
            Experience experience = experiences.get(i);
            response.append("  {\n");
            response.append("    \"eventName\": \"").append(experience.getEventName()).append("\",\n");
            response.append("    \"position\": \"").append(experience.getPosition()).append("\",\n");
            response.append("    \"eventDescription\": \"").append(experience.getEventDescription()).append("\",\n");
            response.append("    \"eventStartDate\": \"").append(experience.getEventStartDate()).append("\",\n");
            response.append("    \"eventEndDate\": \"").append(experience.getEventEndDate()).append("\"\n");
            response.append("  }");

            // Only append a comma if it's not the last element
            if (i < experiences.size() - 1) {
                response.append(",\n");
            }
        }

        response.append("\n]");

        return ResponseEntity.ok(response.toString());
    }
}
