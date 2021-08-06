package com.raimiyashiro.cloudHumans.algorithm;

import com.raimiyashiro.cloudHumans.enums.EducationLevelEnum;
import com.raimiyashiro.cloudHumans.enums.PastExperiencesEnum;
import com.raimiyashiro.cloudHumans.model.Pro;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
class EligibilityAlgorithmTest {

    EligibilityAlgorithm algorithm;

    @BeforeEach
    void setup() {
        algorithm = Mockito.spy(new EligibilityAlgorithm());
    }

    @Test
    void evaluateEducationLevel_whenProHasHighSchoolEducationLevel() {
        Pro pro = new Pro();
        pro.setEducationLevel(EducationLevelEnum.high_school.name());

        Integer expected = 1;
        Integer actual = this.algorithm.evaluateEducationLevel(pro);
        Assertions.assertEquals(actual, expected);
    }

    @Test
    void evaluateEducationLevel_whenProHasBachelorsDegreeOrHighEducationLevel() {
        Pro pro = new Pro();
        pro.setEducationLevel(EducationLevelEnum.bachelors_degree_or_high.name());

        Integer expected = 2;
        Integer actual = this.algorithm.evaluateEducationLevel(pro);
        Assertions.assertEquals(actual, expected);
    }

    @Test
    void evaluateEducationLevel_whenProHasNoEducationLevel() {
        Pro pro = new Pro();
        pro.setEducationLevel(EducationLevelEnum.no_education.name());

        Integer expected = 0;
        Integer actual = this.algorithm.evaluateEducationLevel(pro);
        Assertions.assertEquals(actual, expected);
    }

    @Test
    void evaluatePastExperiences_whenProHasExperienceWithSalesAndSupport() {
        Pro pro = new Pro();
        Map<String, Boolean> experiences = new HashMap<>();

        experiences.put(PastExperiencesEnum.sales.name(), Boolean.TRUE);
        experiences.put(PastExperiencesEnum.support.name(), Boolean.TRUE);
        pro.setPastExperiences(experiences);

        Integer expected = 5;
        Integer actual = this.algorithm.evaluatePastExperiences(pro);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void evaluatePastExperiences_whenProHasExperienceWithSalesOrSupport() {
        Pro pro = new Pro();
        Map<String, Boolean> experiences = new HashMap<>();

        experiences.put(PastExperiencesEnum.sales.name(), Boolean.TRUE);
        pro.setPastExperiences(experiences);

        Integer expectedEvaluation = 3;
        Integer actual = this.algorithm.evaluatePastExperiences(pro);
        Assertions.assertEquals(expectedEvaluation, actual);

        // Just making sure that it works also with PastExperiencesEnum.Support
        experiences.remove(PastExperiencesEnum.sales.name());
        experiences.put(PastExperiencesEnum.support.name(), Boolean.TRUE);

        pro.setPastExperiences(experiences);
        Assertions.assertEquals(expectedEvaluation, this.algorithm.evaluatePastExperiences(pro));
    }

    @Test
    void evaluatePastExperiences_whenProHasNoExperiences() {
        Pro pro = new Pro();

        Integer expected = 0;
        Integer actual = this.algorithm.evaluatePastExperiences(pro);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void evaluateInternetSpeed_whenProHasHighSpeedInternet() {
        float highSpeedInternet = 51f;

        Integer expected = 1;
        Integer actual = this.algorithm.evaluateInternetSpeed(highSpeedInternet);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void evaluateInternetSpeed_whenProHasPoorSpeedInternet() {
        float poorSpeedInternet = 1f;

        Integer expected = -1;
        Integer actual = this.algorithm.evaluateInternetSpeed(poorSpeedInternet);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void evaluateInternetSpeed_whenProHasAverageInternetSpeed() {
        float averageSpeed = 50f;

        Integer expected = 0;
        Integer actual = this.algorithm.evaluateInternetSpeed(averageSpeed);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void evaluateWritingScore() {
        float poorScore = 0.2f;
        float averageScoreA = 0.3f;
        float averageScoreB = 0.7f;
        float highScore = 0.8f;

        Integer poorScoreEvaluation = -1;
        Integer averageScoreEvaluation = 1;
        Integer highScoreEvaluation = 2;

        Assertions.assertEquals(poorScoreEvaluation, this.algorithm.evaluateWritingScore(poorScore));
        Assertions.assertEquals(averageScoreEvaluation, this.algorithm.evaluateWritingScore(averageScoreA));
        Assertions.assertEquals(averageScoreEvaluation, this.algorithm.evaluateWritingScore(averageScoreB));
        Assertions.assertEquals(highScoreEvaluation, this.algorithm.evaluateWritingScore(highScore));
    }

    @Test
    void evaluateReferralCode() {
        String validReferralCode = "token1234";
        String invalidReferralCode = "fooB4r";

        Integer validReferralCodeEvaluation = 1;
        Integer invalidReferralCodeEvaluation = 0;

        Assertions.assertEquals(validReferralCodeEvaluation, this.algorithm.evaluateReferralCode(validReferralCode));
        Assertions.assertEquals(invalidReferralCodeEvaluation, this.algorithm.evaluateReferralCode(invalidReferralCode));
    }

    @Test
    void calculateScore_whenProIsUnderAge() {
        Pro pro = new Pro();
        pro.setAge(16);

        Integer expected = 0;
        Integer actual = this.algorithm.calculateScore(pro);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void calculateScore_whenProIsEligible() {
        Pro pro = new Pro();
        Mockito.doReturn(1).when(this.algorithm).evaluateEducationLevel(pro);
        Mockito.doReturn(1).when(this.algorithm).evaluatePastExperiences(pro);
        Mockito.doReturn(1).when(this.algorithm).evaluateInternetSpeed(Mockito.anyFloat());
        Mockito.doReturn(1).when(this.algorithm).evaluateWritingScore(Mockito.anyFloat());
        Mockito.doReturn(1).when(this.algorithm).evaluateReferralCode(Mockito.any());

        Integer expected = 6; // evaluateInternetSpeed runs either for Download/Upload speed
        Integer actual = this.algorithm.calculateScore(pro);
        Assertions.assertEquals(expected, actual);
    }
}