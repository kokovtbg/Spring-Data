package jsonProcessing.carDealer.services;

import jsonProcessing.carDealer.dto.SupplierNotImporterDTO;
import jsonProcessing.carDealer.entities.Supplier;

import java.util.List;

public interface SupplierService {
    void register(Supplier supplier);

    List<SupplierNotImporterDTO> getAllByImportPartsFalse();
}
