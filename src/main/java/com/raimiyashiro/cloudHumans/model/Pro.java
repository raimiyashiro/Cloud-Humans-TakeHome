package com.raimiyashiro.cloudHumans.model;

import com.raimiyashiro.cloudHumans.enums.PastExperiencesEnum;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Pro {
    private Integer age;
    private String educationLevel;
    private Map<String, Boolean> pastExperiences = new HashMap<>();
    private float writingScore;
    private InternetTest internetTest;
    private String referralCode;

    public boolean hasExperienceWith(PastExperiencesEnum e) {
        return this.getPastExperiences().getOrDefault(e, false);
    }
}
