package com.ventureseed.contribution.config;

import com.ventureseed.contribution.model.Contribution;
import com.ventureseed.contribution.model.ContributionStatus;
import com.ventureseed.contribution.repository.ContributionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initContributions(ContributionRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                // Backer 2 (David Vance) backing Campaign 1
                Contribution c1 = new Contribution(
                        1L,
                        2L,
                        "David Vance",
                        "backer@ventureseed.com",
                        BigDecimal.valueOf(2500.00),
                        "CREDIT_CARD",
                        ContributionStatus.COMPLETED,
                        "txn_init_seed_001",
                        "Excited to support sustainable green hydrogen production in urban grids!"
                );
                repository.save(c1);

                // Backer 4 (Sophia Chen) backing Campaign 1
                Contribution c2 = new Contribution(
                        1L,
                        4L,
                        "Sophia Chen",
                        "investor@summitcapital.com",
                        BigDecimal.valueOf(10000.00),
                        "BANK_TRANSFER",
                        ContributionStatus.COMPLETED,
                        "txn_init_seed_002",
                        "Strategic early-stage backing for QuantumLeaf photosynthetic tech."
                );
                repository.save(c2);

                // Backer 2 backing Campaign 3 (Clean Water)
                Contribution c3 = new Contribution(
                        3L,
                        2L,
                        "David Vance",
                        "backer@ventureseed.com",
                        BigDecimal.valueOf(500.00),
                        "DIGITAL_WALLET",
                        ContributionStatus.COMPLETED,
                        "txn_init_seed_003",
                        "Clean water access is foundational for students."
                );
                repository.save(c3);
            }
        };
    }
}
