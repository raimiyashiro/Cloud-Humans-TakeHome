package com.raimiyashiro.cloudHumans.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.raimiyashiro.cloudHumans.model.InternetTest;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.*;

@Data
public class ProDTO {
    @NotNull(message = "It shouldn't be null")
    @PositiveOrZero(message = "It should be equal or greater than zero")
    private Integer age;

    @NotBlank(message = "It shouldn't be blank")
    @JsonProperty("education_level")
    private String educationLevel;

    @NotNull(message = "It shouldn't be null")
    @JsonProperty("past_experiences")
    private Map<String, Boolean> pastExperiences = new HashMap<>();

    @NotNull(message = "It shouldn't be null")
    @JsonProperty("internet_test")
    private InternetTest internetTest;

    @NotNull(message = "It shouldn't be null")
    @Min(value = 0, message = "It should be greater than zero")
    @Max(value = 1, message = "It shouldn't be grater than one")
    @JsonProperty("writing_score")
    private float writingScore;

    @JsonProperty("referral_code")
    private String referralCode;
}
