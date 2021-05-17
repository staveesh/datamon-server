package za.ac.uct.cs.usagesummaryserver.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.uct.cs.usagesummaryserver.dto.SummaryData;
import za.ac.uct.cs.usagesummaryserver.repository.SummaryDataRepository;

import java.util.List;

@Component
public class DbManagerImpl implements DbManager{

    private static final Logger logger = LoggerFactory.getLogger(DbManagerImpl.class);

    @Autowired
    private SummaryDataRepository repository;

    @Override
    public void writeData(SummaryData data) {
        repository.save(data);
    }

    @Override
    public List<SummaryData> getAll() {
        return repository.findAll();
    }
}
