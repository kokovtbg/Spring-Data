package jsonProcessing.carDealer.services;

import java.io.IOException;

public interface ImportService {
    void importSuppliers() throws IOException;

    void importParts() throws IOException;

    void importCars() throws IOException;

    void importCustomers() throws IOException;

    void importSales();

    default void importAll() throws IOException {
        importSuppliers();
        importParts();
        importCars();
        importCustomers();
        importSales();
    }
}
