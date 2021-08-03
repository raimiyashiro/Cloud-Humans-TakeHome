package com.raimiyashiro.cloudHumans.controller;

import com.raimiyashiro.cloudHumans.controller.dto.EligibilityScoreDTO;
import com.raimiyashiro.cloudHumans.controller.dto.ProDTO;
import com.raimiyashiro.cloudHumans.core.EligibilityAlgorithm;
import com.raimiyashiro.cloudHumans.data.ProjectDataSource;
import com.raimiyashiro.cloudHumans.model.EligibilityScore;
import com.raimiyashiro.cloudHumans.model.Pro;
import com.raimiyashiro.cloudHumans.model.Project;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/pro")
public class ProController {

    private final EligibilityAlgorithm algorithm;
    private final ProjectDataSource projectDataSource;

    @Autowired
    public ProController(EligibilityAlgorithm algorithm, ProjectDataSource projectDataSource) {
        this.algorithm = algorithm;
        this.projectDataSource = projectDataSource;
    }

    @PostMapping("/score")
    @ResponseBody
    public EligibilityScoreDTO getScore(@RequestBody @Valid ProDTO proDTO) {
        var mapper = new ModelMapper();
        var pro = mapper.map(proDTO, Pro.class);

        List<Project> projects = this.projectDataSource.listAvailableProjects();
        EligibilityScore score = this.algorithm.evaluatePro(pro, projects);

        return mapper.map(score, EligibilityScoreDTO.class);
    }
}
