package lab;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class ConsoleRunnerLab implements CommandLineRunner {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public ConsoleRunnerLab(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
//        advancedMapping();//2.	Advanced Mapping
//        projection();//3.	Projection
    }

    private void projection() {
        List<Employee> allBySalaryGreaterThan = this.employeeRepository.findAllBySalaryGreaterThan(BigDecimal.valueOf(3000));
        ModelMapper mapper = new ModelMapper();
        List<EmployeeDTO> employeeDtoList = new ArrayList<>();
        for (int i = 0; i < allBySalaryGreaterThan.size(); i++) {
            Employee employee = allBySalaryGreaterThan.get(i);
            EmployeeDTO employeeDto = mapper.map(employee, EmployeeDTO.class);
            employeeDtoList.add(employeeDto);
        }
        employeeDtoList.forEach(e -> System.out.printf("%s %s %.2f - Manager: %s%n",
                e.getFirstname(), e.getLastName(), e.getSalary(), 
                e.getManagerLastName() == null ? "[no manager]" : e.getManagerLastName()));
    }

    private void advancedMapping() {
        Employee manager1 = new Employee();
        manager1.setFirstName("Steve");
        manager1.setLastName("Jobbsen");
        manager1.setSalary(BigDecimal.valueOf(10000));
        this.employeeRepository.save(manager1);
        Employee employee1 = new Employee();
        employee1.setFirstName("Stephen");
        employee1.setLastName("Bjorn");
        employee1.setSalary(BigDecimal.valueOf(4300));
        employee1.setManager(manager1);
        this.employeeRepository.save(employee1);
        Employee employee2 = new Employee();
        employee2.setFirstName("Kirilyc");
        employee2.setLastName("Lefi");
        employee2.setSalary(BigDecimal.valueOf(4400));
        employee2.setManager(manager1);
        this.employeeRepository.save(employee2);

        manager1.setEmployees(Arrays.asList(employee1, employee2));

        ModelMapper modelMapper = new ModelMapper();

        ManagerDTO managerDto = modelMapper.map(manager1, ManagerDTO.class);

        System.out.printf("%s %s %d%n", managerDto.getFirstName(), managerDto.getLastName(), managerDto.getEmployees().size());
        managerDto.getEmployees().forEach(e -> System.out.printf("%s %s %.2f%n", e.getFirstname(), e.getLastName(), e.getSalary()));
    }
}
