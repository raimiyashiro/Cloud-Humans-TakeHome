package com.raimiyashiro.cloudHumans.model;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class EligibilityScore {
    private Integer score;
    private String selectedProject;
    private List<String> eligibleProjects = new ArrayList<>();
    private List<String> ineligibleProjects = new ArrayList<>();
}
