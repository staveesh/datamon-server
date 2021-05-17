package za.ac.uct.cs.usagesummaryserver.db;

import org.springframework.stereotype.Service;
import za.ac.uct.cs.usagesummaryserver.dto.SummaryData;

import java.util.List;

@Service
public interface DbManager {
    public void writeData(SummaryData data);

    public List<SummaryData> getAll();
}
