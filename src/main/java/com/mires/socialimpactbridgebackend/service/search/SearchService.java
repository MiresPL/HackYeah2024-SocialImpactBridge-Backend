package com.mires.socialimpactbridgebackend.service.search;

import com.mires.common.objects.accounts.firm.Firm;
import com.mires.common.objects.accounts.ngo.NGO;
import com.mires.socialimpactbridgebackend.repositories.accounts.FirmRepository;
import com.mires.socialimpactbridgebackend.repositories.accounts.NGORepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {
    private final NGORepository ngoRepository;
    private final FirmRepository firmRepository;

    @Autowired
    public SearchService(NGORepository ngoRepository, FirmRepository firmRepository) {
        this.ngoRepository = ngoRepository;
        this.firmRepository = firmRepository;
    }

    public ResponseEntity<String> getAllNGOs() {
        return ResponseEntity.ok(ngosToJson(ngoRepository.findAll()));
    }

    public ResponseEntity<String> getAllFirms() {
        return ResponseEntity.ok(firmToJson(firmRepository.findAll()));
    }

    public ResponseEntity<String> findNGOsByPkdCode(List<String> pkdCodesClassCode, String voivodeship) {
        return ResponseEntity.ok(ngosToJson(ngoRepository.findAll().stream().filter(ngo -> pkdCodesClassCode.isEmpty() || ngo.getGoals().stream().anyMatch(pkdCodesClassCode::contains)).filter(ngo -> voivodeship.isEmpty() ||ngo.getVoivodeship().equals(voivodeship)).toList()));
    }

    public ResponseEntity<String> findFirmsByPkdCode(List<String> pkdCodesClassCode, String voivodeship) {
        return ResponseEntity.ok(firmToJson(firmRepository.findAll().stream().filter(firm -> pkdCodesClassCode.isEmpty() || firm.getInterestedGoals().stream().anyMatch(pkdCodesClassCode::contains)).filter(firm -> voivodeship.isEmpty() || firm.getVoivodeship().equals(voivodeship)).toList()));
    }

    public ResponseEntity<String> findNGOsByName(String name) {
        return ResponseEntity.ok(ngosToJson(ngoRepository.findAll().stream().filter(ngo -> ngo.getName().contains(name)).toList()));
    }

    public ResponseEntity<String> findFirmsByName(String name) {
        return ResponseEntity.ok(firmToJson(firmRepository.findAll().stream().filter(firm -> firm.getName().contains(name)).toList()));
    }

    private String ngosToJson(final List<NGO> ngos) {
        final StringBuilder builder = new StringBuilder();
        builder.append("[\n");
        ngos.forEach(ngo -> {
            builder.append("  {\n");
            builder.append("    \"id\": ").append(ngo.getId()).append(",\n");
            builder.append("    \"KRS\": \"").append(ngo.getKRS()).append("\",\n");
            builder.append("    \"NIP\": \"").append(ngo.getNIP()).append("\",\n");
            builder.append("    \"REGON\": \"").append(ngo.getREGON()).append("\",\n");
            builder.append("    \"country\": \"").append(ngo.getCountry()).append("\",\n");
            builder.append("    \"voivodeship\": \"").append(ngo.getVoivodeship()).append("\",\n");
            builder.append("    \"district\": \"").append(ngo.getDistrict()).append("\",\n");
            builder.append("    \"commune\": \"").append(ngo.getCommune()).append("\",\n");
            builder.append("    \"city\": \"").append(ngo.getCity()).append("\",\n");
            builder.append("    \"postalCode\": \"").append(ngo.getPostalCode()).append("\",\n");
            builder.append("    \"street\": \"").append(ngo.getStreet()).append("\",\n");
            builder.append("    \"number\": \"").append(ngo.getNumber()).append("\",\n");
            builder.append("    \"firmEmail\": \"").append(ngo.getFirmEmail()).append("\",\n");
            builder.append("    \"websiteURL\": \"").append(ngo.getWebsiteURL()).append("\",\n");
            builder.append("    \"establishmentDate\": \"").append(ngo.getEstablishmentDate()).append("\",\n");
            builder.append("    \"goals\": ").append(ngo.getGoals().toString()).append(",\n");
            builder.append("    \"activityGoal\": \"").append(ngo.getActivityGoal()).append("\",\n");
            builder.append("    \"membersCount\": ").append(ngo.getMembers().size()).append("\n");
            builder.append("  },\n");
        });

        if (builder.length() == 2) {
            return "[]";
        }

        //remove coma from last object
        builder.deleteCharAt(builder.length() - 2);

        builder.append("]");
        return builder.toString();
    }

    private String firmToJson(final List<Firm> firms) {
        final StringBuilder builder = new StringBuilder();
        builder.append("[\n");
        firms.forEach(firm -> {
            builder.append("  {\n");
            builder.append("    \"id\": ").append(firm.getId()).append(",\n");
            builder.append("    \"KRS\": \"").append(firm.getKRS()).append("\",\n");
            builder.append("    \"NIP\": \"").append(firm.getNIP()).append("\",\n");
            builder.append("    \"REGON\": \"").append(firm.getREGON()).append("\",\n");
            builder.append("    \"country\": \"").append(firm.getCountry()).append("\",\n");
            builder.append("    \"voivodeship\": \"").append(firm.getVoivodeship()).append("\",\n");
            builder.append("    \"district\": \"").append(firm.getDistrict()).append("\",\n");
            builder.append("    \"commune\": \"").append(firm.getCommune()).append("\",\n");
            builder.append("    \"city\": \"").append(firm.getCity()).append("\",\n");
            builder.append("    \"postalCode\": \"").append(firm.getPostalCode()).append("\",\n");
            builder.append("    \"street\": \"").append(firm.getStreet()).append("\",\n");
            builder.append("    \"number\": \"").append(firm.getNumber()).append("\",\n");
            builder.append("    \"firmEmail\": \"").append(firm.getFirmEmail()).append("\",\n");
            builder.append("    \"websiteURL\": \"").append(firm.getWebsiteURL()).append("\",\n");
            builder.append("    \"establishmentDate\": \"").append(firm.getEstablishmentDate()).append("\",\n");
            builder.append("    \"interestedGoals\": ").append(firm.getInterestedGoals().toString()).append(",\n");
            builder.append("    \"description\": \"").append(firm.getDescription()).append("\",\n");
            builder.append("    \"budget\": ").append(firm.getBudget()).append("\n");
            builder.append("  },\n");
        });

        if (builder.length() == 2) {
            return "[]";
        }

        // Remove the trailing comma from the last object
        builder.deleteCharAt(builder.length() - 2);

        builder.append("]");
        return builder.toString();
    }

}
