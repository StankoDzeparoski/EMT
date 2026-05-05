package mk.ukim.finki.emt.lab1_groupb_emt.jobs;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import mk.ukim.finki.emt.lab1_groupb_emt.repository.AccommodationStatisticsByCategoryRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Scheduled component for refreshing the accommodation_statistics_by_category materialized view.
 * 
 * The materialized view does not refresh automatically, so this scheduler periodically
 * refreshes it to keep the aggregated data up-to-date.
 */
@Component
@Slf4j
public class MaterializedViewRefreshScheduler {
    
    private final AccommodationStatisticsByCategoryRepository accommodationStatisticsRepository;
    
    @Value("${accommodation.statistics.view.refresh-concurrently:true}")
    private boolean refreshConcurrently;

    public MaterializedViewRefreshScheduler(AccommodationStatisticsByCategoryRepository accommodationStatisticsRepository) {
        this.accommodationStatisticsRepository = accommodationStatisticsRepository;
    }

    /**
     * Scheduled task that refreshes the accommodation statistics materialized view.
     * 
     * Default: Runs every hour (0 0 * * * * = at the start of every hour)
     * Can be customized via application properties: accommodation.statistics.view.cron
     */
    @Scheduled(cron = "${accommodation.statistics.view.cron:0 0 * * * *}")
    @Transactional
    public void refreshAccommodationStatisticsView() {
        try {
            log.info("Starting refresh of accommodation_statistics_by_category materialized view...");
            
            if (refreshConcurrently) {
                log.debug("Using CONCURRENT refresh to avoid blocking reads");
                accommodationStatisticsRepository.refreshConcurrently();
            } else {
                log.debug("Using standard refresh");
                accommodationStatisticsRepository.refresh();
            }
            
            log.info("accommodation_statistics_by_category materialized view successfully refreshed");
        } catch (Exception e) {
            log.error("Failed to refresh accommodation_statistics_by_category materialized view", e);
            // Don't rethrow to prevent scheduler from being disabled
        }
    }
}

