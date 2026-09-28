package com.ventureseed.contribution.repository;

import com.ventureseed.contribution.model.Contribution;
import com.ventureseed.contribution.model.ContributionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ContributionRepository extends JpaRepository<Contribution, Long> {

    List<Contribution> findByCampaignIdOrderByCreatedAtDesc(Long campaignId);

    List<Contribution> findByBackerIdOrderByCreatedAtDesc(Long backerId);

    List<Contribution> findByStatus(ContributionStatus status);

    @Query("SELECT COALESCE(SUM(c.amount), 0) FROM Contribution c WHERE c.status = 'COMPLETED'")
    BigDecimal getTotalCompletedContributions();

    long countByStatus(ContributionStatus status);
}
