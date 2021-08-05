package com.raimiyashiro.cloudHumans.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Evaluation {
    private Integer score;
    private String selectedProject;
    private List<String> eligibleProjects = new ArrayList<>();
    private List<String> ineligibleProjects = new ArrayList<>();
}
