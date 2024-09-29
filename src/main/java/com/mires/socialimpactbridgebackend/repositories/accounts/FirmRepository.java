package com.mires.socialimpactbridgebackend.repositories.accounts;

import com.mires.common.objects.accounts.firm.Firm;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FirmRepository extends CrudRepository<Firm, Long> {
    List<Firm> findAll();
}
