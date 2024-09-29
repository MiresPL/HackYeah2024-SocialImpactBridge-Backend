package com.mires.socialimpactbridgebackend.repositories.pkd;

import com.mires.common.objects.pkdCode.PkdCode;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PkdCodeRepository extends CrudRepository<PkdCode, Long> {
    PkdCode save(PkdCode pkdCode);
    //GET ALL
    List<PkdCode> findAll();
    //GET BY CLASS CODE
    PkdCode findByCodeClass(String codeClass);
}
