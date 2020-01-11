package org.woehlke.simulation.evolution.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;
import org.woehlke.simulation.evolution.model.Point;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.beans.Transient;

@Configuration
@ConfigurationProperties("org.woehlke.simulation.evolution.config")
@Valid
@Validated
public class SimulatedEvolutionProperties {

    @NotBlank private String title;
    @NotBlank private String subtitle;
    @NotBlank private String footer;
    @NotNull private Integer scale;
    @NotNull private Integer width;
    @NotNull private Integer height;
    @NotNull private Integer initialPopulation;
    @NotNull private Integer queueMaxLength;

    /**
     * How much food per Time Step (a day) shall be placed in this World.
     */
    @NotNull private Integer foodPerDay;
    @NotBlank private String foodPerDayLabel;

    /**
     * A Garden of Eden is an Area where much more Food grows within the same time.
     * As a Result of Evolution you will find sucessful Bacteria Cells with a different DNA and Motion as outside the Garden of Eden.
     */

    @NotNull private Boolean gardenOfEdenEnabled;
    @NotNull private Integer gardenOfEdenFoodPerDay;
    @NotBlank private String gardenOfEdenEnabledString;
    @NotNull private Integer gardenOfEdenParts;
    @NotNull private Integer gardenOfEdenPartsPadding;

    @NotBlank private String buttonFoodPerDayIncrease;
    @NotBlank private String buttonFoodPerDayDecrease;
    @NotBlank private String buttonToggleGardenOfEden;

    @NotNull private Integer maxFat;
    @NotNull private Integer maxHunger;
    @NotNull private Integer FullAge;
    @NotNull private Integer fatMinimumForSex;
    @NotNull private Integer fatAtBirth;
    @NotNull private Integer fatPerFood;
    @NotNull private Integer oldAge;
    @NotNull private Integer maxAge;
    @NotNull private Integer time2wait;
    @NotNull private Integer exitStatus;

    @Transient
    public Point getWorldDimensions() {
        return new Point(
            this.getWidth() * this.getScale(),
            this.getHeight() * this.getScale()
        );
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getFooter() {
        return footer;
    }

    public void setFooter(String footer) {
        this.footer = footer;
    }

    public Integer getScale() {
        return scale;
    }

    public void setScale(Integer scale) {
        this.scale = scale;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getInitialPopulation() {
        return initialPopulation;
    }

    public void setInitialPopulation(Integer initialPopulation) {
        this.initialPopulation = initialPopulation;
    }

    public Integer getQueueMaxLength() {
        return queueMaxLength;
    }

    public void setQueueMaxLength(Integer queueMaxLength) {
        this.queueMaxLength = queueMaxLength;
    }

    public Integer getFoodPerDay() {
        return foodPerDay;
    }

    public void setFoodPerDay(Integer foodPerDay) {
        this.foodPerDay = foodPerDay;
    }

    public Integer getGardenOfEdenFoodPerDay() {
        return gardenOfEdenFoodPerDay;
    }

    public void setGardenOfEdenFoodPerDay(Integer gardenOfEdenFoodPerDay) {
        this.gardenOfEdenFoodPerDay = gardenOfEdenFoodPerDay;
    }

    public Boolean getGardenOfEdenEnabled() {
        return gardenOfEdenEnabled;
    }

    public void setGardenOfEdenEnabled(Boolean gardenOfEdenEnabled) {
        this.gardenOfEdenEnabled = gardenOfEdenEnabled;
    }

    public String getFoodPerDayLabel() {
        return foodPerDayLabel;
    }

    public void setFoodPerDayLabel(String foodPerDayLabel) {
        this.foodPerDayLabel = foodPerDayLabel;
    }

    public String getGardenOfEdenEnabledString() {
        return gardenOfEdenEnabledString;
    }

    public void setGardenOfEdenEnabledString(String gardenOfEdenEnabledString) {
        this.gardenOfEdenEnabledString = gardenOfEdenEnabledString;
    }

    public String getButtonFoodPerDayIncrease() {
        return buttonFoodPerDayIncrease;
    }

    public void setButtonFoodPerDayIncrease(String buttonFoodPerDayIncrease) {
        this.buttonFoodPerDayIncrease = buttonFoodPerDayIncrease;
    }

    public String getButtonFoodPerDayDecrease() {
        return buttonFoodPerDayDecrease;
    }

    public void setButtonFoodPerDayDecrease(String buttonFoodPerDayDecrease) {
        this.buttonFoodPerDayDecrease = buttonFoodPerDayDecrease;
    }

    public String getButtonToggleGardenOfEden() {
        return buttonToggleGardenOfEden;
    }

    public void setButtonToggleGardenOfEden(String buttonToggleGardenOfEden) {
        this.buttonToggleGardenOfEden = buttonToggleGardenOfEden;
    }

    public Integer getMaxFat() {
        return maxFat;
    }

    public void setMaxFat(Integer maxFat) {
        this.maxFat = maxFat;
    }

    public Integer getMaxHunger() {
        return maxHunger;
    }

    public void setMaxHunger(Integer maxHunger) {
        this.maxHunger = maxHunger;
    }

    public Integer getFullAge() {
        return FullAge;
    }

    public void setFullAge(Integer fullAge) {
        FullAge = fullAge;
    }

    public Integer getFatMinimumForSex() {
        return fatMinimumForSex;
    }

    public void setFatMinimumForSex(Integer fatMinimumForSex) {
        this.fatMinimumForSex = fatMinimumForSex;
    }

    public Integer getFatAtBirth() {
        return fatAtBirth;
    }

    public void setFatAtBirth(Integer fatAtBirth) {
        this.fatAtBirth = fatAtBirth;
    }

    public Integer getFatPerFood() {
        return fatPerFood;
    }

    public void setFatPerFood(Integer fatPerFood) {
        this.fatPerFood = fatPerFood;
    }

    public Integer getOldAge() {
        return oldAge;
    }

    public void setOldAge(Integer oldAge) {
        this.oldAge = oldAge;
    }

    public Integer getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(Integer maxAge) {
        this.maxAge = maxAge;
    }

    public Integer getTime2wait() {
        return time2wait;
    }

    public void setTime2wait(Integer time2wait) {
        this.time2wait = time2wait;
    }

    public Integer getExitStatus() {
        return exitStatus;
    }

    public void setExitStatus(Integer exitStatus) {
        this.exitStatus = exitStatus;
    }

    public Integer getGardenOfEdenParts() {
        return gardenOfEdenParts;
    }

    public void setGardenOfEdenParts(Integer gardenOfEdenParts) {
        this.gardenOfEdenParts = gardenOfEdenParts;
    }

    public Integer getGardenOfEdenPartsPadding() {
        return gardenOfEdenPartsPadding;
    }

    public void setGardenOfEdenPartsPadding(Integer gardenOfEdenPartsPadding) {
        this.gardenOfEdenPartsPadding = gardenOfEdenPartsPadding;
    }
}
