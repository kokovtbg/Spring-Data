package xmlProcessing.carDealer.services;

import xmlProcessing.carDealer.dto.SupplierNotImporterDTO;
import xmlProcessing.carDealer.dto.SuppliersNotImporterDTO;
import xmlProcessing.carDealer.entities.Supplier;

import java.util.List;

public interface SupplierService {
    void register(Supplier supplier);

    SuppliersNotImporterDTO getAllByImportPartsFalse();
}
