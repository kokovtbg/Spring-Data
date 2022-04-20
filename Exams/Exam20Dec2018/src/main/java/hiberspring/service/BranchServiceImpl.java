package hiberspring.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import hiberspring.domain.dtos.BranchesImportDTO;
import hiberspring.domain.entities.Branch;
import hiberspring.domain.entities.Town;
import hiberspring.repository.BranchRepository;
import hiberspring.repository.TownRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class BranchServiceImpl implements BranchService {
    private final BranchRepository branchRepository;
    private final TownRepository townRepository;
    private final Path path = Paths.get("src/main/resources/files/branches.json");
    private final Gson gson;

    @Autowired
    public BranchServiceImpl(BranchRepository branchRepository, TownRepository townRepository) {
        this.branchRepository = branchRepository;
        this.townRepository = townRepository;
        this.gson = new GsonBuilder().create();
    }

    @Override
    public Boolean branchesAreImported() {
        return this.branchRepository.count() > 0;
    }

    @Override
    public String readBranchesJsonFile() throws IOException {
        List<String> strings = Files.readAllLines(path);
        return String.join("\n", strings);
    }

    @Override
    public String importBranches(String branchesFileContent) throws IOException {
        List<String> message = new ArrayList<>();

        Reader reader = Files.newBufferedReader(path);
        BranchesImportDTO[] branchesImportDTOs = this.gson.fromJson(reader, BranchesImportDTO[].class);
        for (BranchesImportDTO branchDTO : branchesImportDTOs) {
            if (!branchDTO.isValid()) {
                message.add("Error: Invalid data.");
                continue;
            }
            Town townByName = townRepository.findByName(branchDTO.getTown());
            Branch branch = new Branch();
            branch.setName(branchDTO.getName());
            branch.setTown(townByName);
            this.branchRepository.save(branch);
            message.add(String.format("Successfully imported Branch %s.", branch.getName()));
        }

        return String.join("\n", message);
    }
}
