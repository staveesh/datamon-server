package za.ac.uct.cs.usagesummaryserver.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class ScheduledTaskConfig {

    private static final Logger logger = LoggerFactory.getLogger(ScheduledTaskConfig.class);

    @Scheduled(fixedDelay = 6000)
    public void activeConnectionsCount() {
        logger.info("Active connections : "+WebSocketConfig.connections);
        logger.info("Number of active connections : "+WebSocketConfig.connections.size());
    }
}
