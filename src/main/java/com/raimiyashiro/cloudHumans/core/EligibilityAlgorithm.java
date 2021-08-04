package com.raimiyashiro.cloudHumans.core;

import com.raimiyashiro.cloudHumans.enums.EducationLevelEnum;
import com.raimiyashiro.cloudHumans.enums.PastExperiencesEnum;
import com.raimiyashiro.cloudHumans.model.EligibilityScore;
import com.raimiyashiro.cloudHumans.model.Pro;
import com.raimiyashiro.cloudHumans.model.Project;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class EligibilityAlgorithm {


    public EligibilityScore calculateScore(Pro pro, List<Project> availableProjects) {

        var proScore = this.evaluatePro(pro);

        var eligibleProjects = this.selectEligibleProjects(availableProjects, proScore);
        var selectedProject = eligibleProjects.isEmpty() ? null : eligibleProjects.get(0).getName();


        return EligibilityScore.builder()
                .score(proScore)
                .selectedProject(selectedProject)
                .eligibleProjects(eligibleProjects.stream().map(Project::getName).collect(Collectors.toList()))
                .ineligibleProjects(null)
                .build();
    }

    public Integer evaluatePro(Pro pro) {
        // It could be stored in application-properties, or some KeyVault, etc...
        final var VALID_REFERRAL_TOKEN = "token1234";

        var finalScore = 0;

        if (pro.isUnderAge()) {
            return finalScore;
        }

        if (pro.hasEducationLevel(EducationLevelEnum.high_school)) {
            finalScore++;
        } else if (pro.hasEducationLevel(EducationLevelEnum.bachelors_degree_or_high)) {
            finalScore = finalScore + 2;
        }

        if (pro.hasExperienceWith(PastExperiencesEnum.sales) && pro.hasExperienceWith(PastExperiencesEnum.support)) {
            finalScore = finalScore + 5;
        } else if (pro.hasExperienceWith(PastExperiencesEnum.sales) || pro.hasExperienceWith(PastExperiencesEnum.support)) {
            finalScore = finalScore + 3;
        }

        if (pro.getInternetDownloadSpeed() > 50f) {
            finalScore++;
        } else if (pro.getInternetDownloadSpeed() < 5f) {
            finalScore--;
        }

        if (pro.getInternetUploadSpeed() > 50f) {
            finalScore++;
        } else if (pro.getInternetUploadSpeed() < 5f) {
            finalScore--;
        }

        if (pro.getWritingScore() < 0.3) {
            finalScore--;

        } else if (pro.getWritingScore() <= 0.7) {
            finalScore++;

        } else {
            finalScore = finalScore + 2;
        }

        if (pro.getReferralCode().equals(VALID_REFERRAL_TOKEN)) {
            finalScore++;
        }

        return finalScore;
    }

    public List<Project> selectEligibleProjects(List<Project> availableProjects, Integer proScore) {
        return null;
    }
}
