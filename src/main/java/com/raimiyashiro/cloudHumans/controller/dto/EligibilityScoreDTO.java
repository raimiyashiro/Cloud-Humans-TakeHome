package com.raimiyashiro.cloudHumans.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class EligibilityScoreDTO {
    private Integer score;
    @JsonProperty("selected_project")
    private String selectedProject;

    @JsonProperty("eligible_projects")
    private List<String> eligibleProjects = new ArrayList<>();

    @JsonProperty("ineligible_projects")
    private List<String> ineligibleProjects = new ArrayList<>();
}
