package com.raimiyashiro.cloudHumans.core;

import com.raimiyashiro.cloudHumans.enums.EducationLevelEnum;
import com.raimiyashiro.cloudHumans.enums.PastExperiencesEnum;
import com.raimiyashiro.cloudHumans.model.Pro;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EligibilityAlgorithm {

    public Integer evaluateEducationLevel(Pro pro) {
        if (pro.hasEducationLevel(EducationLevelEnum.high_school)) {
            return 1;
        } else if (pro.hasEducationLevel(EducationLevelEnum.bachelors_degree_or_high)) {
            return 2;
        }
        return 0;
    }

    public Integer evaluatePastExperiences(Pro pro) {
        if (pro.hasExperienceWith(PastExperiencesEnum.sales) && pro.hasExperienceWith(PastExperiencesEnum.support)) {
            return 5;
        } else if (pro.hasExperienceWith(PastExperiencesEnum.sales) || pro.hasExperienceWith(PastExperiencesEnum.support)) {
            return 3;
        }
        return 0;
    }

    public Integer evaluateInternetSpeed(float speed) {
        Integer evaluation = 0;

        float highSpeedInternet = 50f;
        float poorSpeedInternet = 5f;

        if (speed > highSpeedInternet) {
            evaluation++;
        } else if (speed < poorSpeedInternet) {
            evaluation--;
        }

        return evaluation;
    }

    public Integer evaluateWritingScore(float score) {
        if (score < 0.3) {
            return -1;
        } else if (score <= 0.7) {
            return 1;
        }
        return 2;
    }

    public Integer evaluateReferralCode(String referralCode) {
        // It could be stored in application-properties, or some KeyVault, etc...
        final var VALID_REFERRAL_CODE = "token1234";

        return VALID_REFERRAL_CODE.equals(referralCode) ? 1 : 0;
    }


    public Integer calculateScore(Pro pro) {
        return pro.isUnderAge() ? 0 : (
                this.evaluateEducationLevel(pro)
                        + this.evaluatePastExperiences(pro)
                        + this.evaluateInternetSpeed(pro.getInternetDownloadSpeed())
                        + this.evaluateInternetSpeed(pro.getInternetUploadSpeed())
                        + this.evaluateWritingScore(pro.getWritingScore())
                        + this.evaluateReferralCode(pro.getReferralCode())
        );
    }
}
