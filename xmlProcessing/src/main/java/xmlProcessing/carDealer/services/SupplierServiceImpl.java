package xmlProcessing.carDealer.services;

import xmlProcessing.carDealer.dto.SupplierNotImporterDTO;
import xmlProcessing.carDealer.dto.SuppliersNotImporterDTO;
import xmlProcessing.carDealer.entities.Supplier;
import xmlProcessing.carDealer.repositories.SupplierRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplierServiceImpl implements SupplierService {
    private final SupplierRepository supplierRepository;
    private final ModelMapper mapper;

    @Autowired
    public SupplierServiceImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
        this.mapper = new ModelMapper();
    }

    @Override
    public void register(Supplier supplier) {
        this.supplierRepository.save(supplier);
    }

    @Override
    public SuppliersNotImporterDTO getAllByImportPartsFalse() {
        List<Supplier> allByIsImporter = this.supplierRepository.findAllByIsImporter(false);
        List<SupplierNotImporterDTO> supplierNotImporterDTOs = allByIsImporter.stream()
                .map(s -> mapper.map(s, SupplierNotImporterDTO.class))
                .collect(Collectors.toList());
        supplierNotImporterDTOs
                .forEach(s -> s.setPartsCount(s.getParts().size()));
        SuppliersNotImporterDTO suppliersNotImporterDTO = new SuppliersNotImporterDTO();
        suppliersNotImporterDTO.setSuppliers(supplierNotImporterDTOs);
        return suppliersNotImporterDTO;
    }
}
