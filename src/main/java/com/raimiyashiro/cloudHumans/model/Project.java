package com.raimiyashiro.cloudHumans.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Project {
    private String name;
    private int requiredScore;

    public boolean isEligible(int score) {
        return score > this.getRequiredScore();
    }
}
