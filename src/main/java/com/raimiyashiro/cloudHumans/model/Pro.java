package com.raimiyashiro.cloudHumans.model;

import com.raimiyashiro.cloudHumans.enums.EducationLevelEnum;
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
    private InternetTest internetTest = new InternetTest();
    private String referralCode;

    public boolean isUnderAge() {
        return this.age != null && this.getAge() < 18;
    }

    public boolean hasEducationLevel(EducationLevelEnum e) {
        return this.getEducationLevel().equalsIgnoreCase(e.name());
    }

    public boolean hasExperienceWith(PastExperiencesEnum e) {
        return this.getPastExperiences().getOrDefault(e.name(), false);
    }

    public float getInternetDownloadSpeed() {
        return this.getInternetTest().getDownloadSpeed();
    }

    public float getInternetUploadSpeed() {
        return this.getInternetTest().getUploadSpeed();
    }
}
