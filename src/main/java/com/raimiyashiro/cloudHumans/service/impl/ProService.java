package com.raimiyashiro.cloudHumans.service.impl;

import com.raimiyashiro.cloudHumans.core.EligibilityAlgorithm;
import com.raimiyashiro.cloudHumans.model.Evaluation;
import com.raimiyashiro.cloudHumans.model.Pro;
import com.raimiyashiro.cloudHumans.model.Project;
import com.raimiyashiro.cloudHumans.service.IProService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ProService implements IProService {

    private final ProjectService projectService;
    private final EligibilityAlgorithm algorithm;

    @Autowired
    public ProService(ProjectService projectService, EligibilityAlgorithm algorithm) {
        this.projectService = projectService;
        this.algorithm = algorithm;
    }

    @Override
    public Evaluation evaluatePro(Pro pro) {
        Evaluation evaluation = new Evaluation();
        List<Project> availableProjects = this.projectService.getAvailableProjects();
        evaluation.setScore(this.algorithm.calculateScore(pro));

        List<Project> ineligibleProjects = availableProjects.stream()
                .filter(project -> project.getRequiredScore() > evaluation.getScore())
                .collect(Collectors.toList());

        evaluation.setIneligibleProjects(
                ineligibleProjects.stream()
                        .map(Project::getName)
                        .collect(Collectors.toList())
        );

        List<Project> eligibleProjects = availableProjects.stream()
                .filter(project -> project.getRequiredScore() <= evaluation.getScore())
                .sorted(Comparator.comparingInt(Project::getRequiredScore).reversed())
                .collect(Collectors.toList());

        eligibleProjects.stream()
                .findFirst()
                .ifPresent(index -> evaluation.setSelectedProject(index.getName()));

        evaluation.setEligibleProjects(
                eligibleProjects.stream().map(Project::getName)
                        .filter(name -> !Objects.equals(name, evaluation.getSelectedProject()))
                        .collect(Collectors.toList())
        );

        return evaluation;
    }
}
