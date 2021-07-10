package za.ac.uct.cs.usagesummaryserver.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.HashSet;
import java.util.stream.Collectors;

@Configuration
@EnableScheduling
public class ScheduledTaskConfig {

    private static final Logger logger = LoggerFactory.getLogger(ScheduledTaskConfig.class);

    @Scheduled(fixedDelay = 120000)
    public void activeConnectionsCount() {
        logger.info("Number of active connections : "+ new HashSet<>(WebSocketConfig.connections.values()).size());
    }
}
