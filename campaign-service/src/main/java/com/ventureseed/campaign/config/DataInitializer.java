package com.ventureseed.campaign.config;

import com.ventureseed.campaign.model.Campaign;
import com.ventureseed.campaign.model.CampaignCategory;
import com.ventureseed.campaign.model.CampaignStatus;
import com.ventureseed.campaign.repository.CampaignRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDate;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initCampaigns(CampaignRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                // Campaign 1: Tech / AI Clean Energy
                Campaign c1 = new Campaign(
                        "QuantumLeaf: Artificial Photosynthetic Energy Cells",
                        "Harnessing ambient sunlight and CO2 to produce ultra-clean hydrogen power for urban buildings.",
                        "QuantumLeaf has developed next-generation bionic nanofilm surfaces that mimic plant photosynthesis at 18x biological efficiency. Our bio-hybrid cells convert atmospheric carbon dioxide and sunlight directly into clean green hydrogen and electrical power. Funds will accelerate pilot deployments across metropolitan micro-grids and UL safety certifications.",
                        CampaignCategory.CLEAN_ENERGY,
                        BigDecimal.valueOf(100000.00),
                        3L,
                        "Dr. Aris Thorne",
                        "founder@quantumleaf.io",
                        LocalDate.now().plusDays(45),
                        "https://images.unsplash.com/photo-1509391365360-2e959784a276?auto=format&fit=crop&w=1200&q=80"
                );
                c1.setCurrentAmount(BigDecimal.valueOf(78450.00));
                c1.setBackerCount(142);
                c1.setStatus(CampaignStatus.ACTIVE);
                repository.save(c1);

                // Campaign 2: Biotech / Neural Prosthetics
                Campaign c2 = new Campaign(
                        "NeuroPulse Bionic Mobility Pro",
                        "Sub-millimeter neural interface sensor restoring natural intuitive movement to limb amputees.",
                        "NeuroPulse combines ultra-low latency edge machine learning with biocompatible myoelectric neural implants. Patients achieve fluid hand articulation, sensory haptic feedback, and natural dexterity without cognitive fatigue. We have concluded Phase 1 clinical trials and need capital to manufacture 50 trial units.",
                        CampaignCategory.BIOTECH,
                        BigDecimal.valueOf(150000.00),
                        1L,
                        "Elena Rostova",
                        "creator@ventureseed.com",
                        LocalDate.now().plusDays(30),
                        "https://images.unsplash.com/photo-1576086213369-97a306d36557?auto=format&fit=crop&w=1200&q=80"
                );
                c2.setCurrentAmount(BigDecimal.valueOf(150000.00));
                c2.setBackerCount(310);
                c2.setStatus(CampaignStatus.FUNDED);
                repository.save(c2);

                // Campaign 3: Social Impact / Clean Water
                Campaign c3 = new Campaign(
                        "AquaPure Solar: Clean Water for 100 Rural Schools",
                        "Autonomous solar water filtration stations delivering 10,000 liters of potable water daily.",
                        "Access to clean drinking water remains a critical bottleneck for rural education. AquaPure Solar units operate completely off-grid, using solar ultraviolet sterilization and multi-stage ceramic filtration to purify contaminated groundwater at zero operating fuel cost.",
                        CampaignCategory.SOCIAL_IMPACT,
                        BigDecimal.valueOf(45000.00),
                        1L,
                        "Elena Rostova",
                        "creator@ventureseed.com",
                        LocalDate.now().plusDays(60),
                        "https://images.unsplash.com/photo-1541888946425-d0fbb186f5f7?auto=format&fit=crop&w=1200&q=80"
                );
                c3.setCurrentAmount(BigDecimal.valueOf(28900.00));
                c3.setBackerCount(215);
                c3.setStatus(CampaignStatus.ACTIVE);
                repository.save(c3);

                // Campaign 4: Creative Arts & Open Hardware
                Campaign c4 = new Campaign(
                        "OpenSynth Studio: Modular Synthesizer Ecosystem",
                        "Open-source modular sound design platform empowering independent audio artists globally.",
                        "A fully open-hardware modular synthesizer engine featuring 32-bit DSP audio processing, CV/Gate patch interconnectivity, and web-based algorithmic firmware updates.",
                        CampaignCategory.CREATIVE_ARTS,
                        BigDecimal.valueOf(30000.00),
                        3L,
                        "Dr. Aris Thorne",
                        "founder@quantumleaf.io",
                        LocalDate.now().plusDays(20),
                        "https://images.unsplash.com/photo-1598488035139-bdbb2231ce04?auto=format&fit=crop&w=1200&q=80"
                );
                c4.setCurrentAmount(BigDecimal.valueOf(14200.00));
                c4.setBackerCount(89);
                c4.setStatus(CampaignStatus.ACTIVE);
                repository.save(c4);
            }
        };
    }
}
