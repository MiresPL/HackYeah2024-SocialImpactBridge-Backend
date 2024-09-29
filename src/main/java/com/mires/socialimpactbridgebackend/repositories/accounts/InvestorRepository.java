package com.mires.socialimpactbridgebackend.repositories.accounts;

import com.mires.common.objects.accounts.investor.Investor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvestorRepository extends CrudRepository<Investor, Long> {
    List<Investor> findAll();
}
