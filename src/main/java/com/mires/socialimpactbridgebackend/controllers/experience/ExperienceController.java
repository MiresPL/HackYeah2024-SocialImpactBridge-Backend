package com.mires.socialimpactbridgebackend.controllers.experience;

import com.google.gson.GsonBuilder;
import com.mires.common.objects.experience.ExperienceDTO;
import com.mires.socialimpactbridgebackend.service.experience.ExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/experience")
public class ExperienceController {
    @Autowired
    private ExperienceService experienceService;

    @PostMapping("/addExperience")
    @CrossOrigin
    public ResponseEntity<String> addExperience(@RequestBody Map<String, String> body) {
        Long userId;
        ExperienceDTO experience;

        try {
            userId = Long.valueOf(body.get("userId").toString());
            System.out.println(body.get("experience"));
            experience = new GsonBuilder().serializeNulls().disableHtmlEscaping().setPrettyPrinting().create().fromJson(body.get("experience"), ExperienceDTO.class);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid userId or experience format");
        }

        return experienceService.addExperienceToAccount(userId, experience);
    }

    @PostMapping("/getUserExperience")
    @CrossOrigin
    public ResponseEntity<String> getUserExperience(@RequestBody Map<String, String> body) {
        Long userId;

        try {
            userId = Long.valueOf(body.get("userId").toString());
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body("Invalid userId");
        }

        return experienceService.getUserExperience(userId);
    }

}
