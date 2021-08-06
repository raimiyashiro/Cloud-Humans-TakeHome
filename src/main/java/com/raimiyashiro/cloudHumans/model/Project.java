package com.raimiyashiro.cloudHumans.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@AllArgsConstructor
public class Project {
    @Getter
    private String name;
    @Getter
    private int requiredScore;

    public boolean isEligible(int score) {
        return score > this.getRequiredScore();
    }
}
