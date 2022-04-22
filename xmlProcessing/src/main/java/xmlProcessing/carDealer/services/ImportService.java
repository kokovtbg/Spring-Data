package xmlProcessing.carDealer.services;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface ImportService {
    void importSuppliers() throws IOException, JAXBException;

    void importParts() throws IOException, JAXBException;

    void importCars() throws IOException, JAXBException;

    void importCustomers() throws IOException, JAXBException;

    void importSales();

    default void importAll() throws IOException, JAXBException {
        importSuppliers();
        importParts();
        importCars();
        importCustomers();
        importSales();
    }
}
