package com.ventureseed.campaign.repository;

import com.ventureseed.campaign.model.Campaign;
import com.ventureseed.campaign.model.CampaignCategory;
import com.ventureseed.campaign.model.CampaignStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Long> {

    List<Campaign> findByStatus(CampaignStatus status);

    List<Campaign> findByCategory(CampaignCategory category);

    List<Campaign> findByCreatorId(Long creatorId);

    List<Campaign> findByTitleContainingIgnoreCaseOrTaglineContainingIgnoreCase(String title, String tagline);

    @Query("SELECT COALESCE(SUM(c.currentAmount), 0) FROM Campaign c")
    BigDecimal getTotalFundsRaised();

    @Query("SELECT COALESCE(SUM(c.backerCount), 0) FROM Campaign c")
    Long getTotalBackersCount();

    long countByStatus(CampaignStatus status);
}
