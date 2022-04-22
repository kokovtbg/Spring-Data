package xmlProcessing.carDealer.services;

import xmlProcessing.carDealer.entities.Part;
import xmlProcessing.carDealer.entities.Supplier;

import java.util.List;

public interface PartService {
    Part getRandomSupplier(Part part, List<Supplier> suppliers);

    void register(Part part);
}
