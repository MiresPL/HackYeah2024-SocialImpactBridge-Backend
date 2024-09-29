package com.mires.socialimpactbridgebackend.service.pkd;

import com.google.gson.GsonBuilder;
import com.mires.common.objects.pkdCode.PkdCode;
import com.mires.socialimpactbridgebackend.repositories.pkd.PkdCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PkdCodeService {
    private final PkdCodeRepository pkdCodeRepository;

    @Autowired
    public PkdCodeService(PkdCodeRepository pkdCodeRepository) {
        this.pkdCodeRepository = pkdCodeRepository;
    }

    public ResponseEntity<String> addPkdCode(PkdCode pkdCode) {
        if (pkdCodeRepository.findByCodeClass(pkdCode.getCodeClass()) != null) {
            return ResponseEntity.badRequest().body("PkdCode with class code " + pkdCode.getCodeClass() + " already exists");
        }
        pkdCodeRepository.save(pkdCode);
        return ResponseEntity.ok("PkdCode with class code " + pkdCode.getCodeClass() + " added successfully");
    }

    public List<PkdCode> getAllPkdCodes() {
        return pkdCodeRepository.findAll();
    }

    public PkdCode savePkdCode(PkdCode pkdCode) {
        return pkdCodeRepository.save(pkdCode);
    }

    public ResponseEntity<String> getPkdCodeByClassCode(String classCode) {
        final PkdCode pkdCode = pkdCodeRepository.findByCodeClass(classCode);
        if (pkdCode == null) {
            return ResponseEntity.badRequest().body("PkdCode with class code " + classCode + " not found");
        }

        return ResponseEntity.ok(new GsonBuilder().setPrettyPrinting().serializeNulls().disableHtmlEscaping().create().toJson(pkdCodeRepository.findByCodeClass(classCode)));
    }
}
