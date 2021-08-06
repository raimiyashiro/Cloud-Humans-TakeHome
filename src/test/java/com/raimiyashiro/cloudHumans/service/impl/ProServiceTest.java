package com.raimiyashiro.cloudHumans.service.impl;

import com.raimiyashiro.cloudHumans.algorithm.EligibilityAlgorithm;
import com.raimiyashiro.cloudHumans.model.Evaluation;
import com.raimiyashiro.cloudHumans.model.Pro;
import com.raimiyashiro.cloudHumans.model.Project;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

@ExtendWith(MockitoExtension.class)
class ProServiceTest {

    @Mock
    ProjectService projectService;
    @Mock
    EligibilityAlgorithm algorithm;

    ProService serviceUnderTests;

    @BeforeEach
    void setup() {
        this.serviceUnderTests = new ProService(projectService, algorithm);
    }

    @Test
    void evaluatePro() {
        Mockito.doReturn(5).when(this.algorithm).calculateScore(Mockito.any());
        Mockito.doReturn(Arrays.asList(
                new Project("Calculate the Dark Matter of the universe for Nasa", 10),
                new Project("Determine if the Schrodinger's cat is alive", 5),
                new Project("Attend to users support for a YXZ Company", 3),
                new Project("Collect specific people information from their social media for XPTO Company", 2)
        )).when(this.projectService).getAvailableProjects();

        Evaluation expectedEvaluation = new Evaluation();
        expectedEvaluation.setScore(5);
        expectedEvaluation.setSelectedProject("Attend to users support for a YXZ Company");
        expectedEvaluation.setEligibleProjects(Arrays.asList(
                "Attend to users support for a YXZ Company", // requiredScore: 3
                "Collect specific people information from their social media for XPTO Company" // requiredScore: 2
        ));

        expectedEvaluation.setIneligibleProjects(Arrays.asList(
                "Calculate the Dark Matter of the universe for Nasa", // requiredScore: 10
                "Determine if the Schrodinger's cat is alive" // requiredScore: 5
        ));

        Evaluation actualEvaluation = this.serviceUnderTests.evaluatePro(new Pro());
        Assertions.assertEquals(expectedEvaluation, actualEvaluation);
    }
}