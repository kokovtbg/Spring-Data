package jsonProcessing.carDealer.services;

import jsonProcessing.carDealer.entities.Part;
import jsonProcessing.carDealer.entities.Supplier;

import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

public interface PartService {
    Part getRandomSupplier(Part part, List<Supplier> suppliers);

    void register(Part part);
}
