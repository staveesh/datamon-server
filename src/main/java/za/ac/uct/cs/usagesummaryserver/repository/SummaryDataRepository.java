package za.ac.uct.cs.usagesummaryserver.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import za.ac.uct.cs.usagesummaryserver.dto.SummaryData;

public interface SummaryDataRepository extends MongoRepository<SummaryData, String> {
}
