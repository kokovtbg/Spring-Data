package hiberspring.service;

import hiberspring.domain.dtos.EmployeeImportDTO;
import hiberspring.domain.dtos.EmployeesRootImportDTO;
import hiberspring.domain.entities.Branch;
import hiberspring.domain.entities.Employee;
import hiberspring.domain.entities.EmployeeCard;
import hiberspring.repository.BranchRepository;
import hiberspring.repository.EmployeeCardRepository;
import hiberspring.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeCardRepository employeeCardRepository;
    private final BranchRepository branchRepository;
    private final Path path = Paths.get("src/main/resources/files/employees.xml");

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository,
                               EmployeeCardRepository employeeCardRepository,
                               BranchRepository branchRepository) {
        this.employeeRepository = employeeRepository;
        this.employeeCardRepository = employeeCardRepository;
        this.branchRepository = branchRepository;
    }

    @Override
    public Boolean employeesAreImported() {
        return this.employeeRepository.count() > 0;
    }

    @Override
    public String readEmployeesXmlFile() throws IOException {
        List<String> strings = Files.readAllLines(path);
        return String.join("\n", strings);
    }

    @Override
    public String importEmployees() throws JAXBException, IOException {
        List<String> message = new ArrayList<>();

        Reader reader = Files.newBufferedReader(path);
        JAXBContext context = JAXBContext.newInstance(EmployeesRootImportDTO.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        EmployeesRootImportDTO employeesRootImportDTO = (EmployeesRootImportDTO) unmarshaller.unmarshal(reader);

        List<EmployeeImportDTO> employeeImportDTOs = employeesRootImportDTO.getEmployees();
        for (EmployeeImportDTO employeeDTO : employeeImportDTOs) {
            if (employeeDTO.getBranch() == null) {
                message.add("Error: Invalid data.");
                continue;
            }
            EmployeeCard cardByNumber = this.employeeCardRepository.findByNumber(employeeDTO.getCard());
            Branch branchByName = branchRepository.findByName(employeeDTO.getBranch());
            if (branchByName == null) {
                message.add("Error: Invalid data.");
                continue;
            }
            Employee employeeByCard = this.employeeRepository.findByCard(cardByNumber);
            if (employeeByCard != null) {
                message.add("Error: Invalid data.");
                continue;
            }
            Employee employee = new Employee();
            employee.setFirstName(employeeDTO.getFirstName());
            employee.setLastName(employeeDTO.getLastName());
            employee.setPosition(employeeDTO.getPosition());
            employee.setCard(cardByNumber);
            employee.setBranch(branchByName);
            this.employeeRepository.save(employee);
            message.add(String.format("Successfully imported Employee %s %s.",
                    employee.getFirstName(), employee.getLastName()));
        }

        return String.join("\n", message);
    }

    @Override
    public String exportProductiveEmployees() {
        List<String> message = new ArrayList<>();

        List<Employee> byBranchCountProductsGreaterThanZero = this.employeeRepository
                .findByBranchCountProductsGreaterThanZero();
        byBranchCountProductsGreaterThanZero.forEach(e -> message.add(e.toString()));

        return String.join("\n-------------------------\n", message);
    }
}
