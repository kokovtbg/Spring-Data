package xmlProcessing.carDealer.services;

import xmlProcessing.carDealer.entities.Part;
import xmlProcessing.carDealer.entities.Supplier;
import xmlProcessing.carDealer.repositories.PartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class PartServiceImpl implements PartService {
    private final PartRepository partRepository;

    @Autowired
    public PartServiceImpl(PartRepository partRepository) {
        this.partRepository = partRepository;
    }

    @Override
    public Part getRandomSupplier(Part part, List<Supplier> suppliers) {
        Random random = new Random();
        int index = random.nextInt(suppliers.size());
        part.setSupplier(suppliers.get(index));
        return part;
    }

    @Override
    public void register(Part part) {
        this.partRepository.save(part);
    }
}
