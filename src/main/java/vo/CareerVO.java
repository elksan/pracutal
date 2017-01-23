package vo;

import models.Career;

/**
 * Created by Diego on 21-12-2016.
 */
public class CareerVO {

    private String careerName;
    private Integer careerId;

    public CareerVO() {
    }

    public CareerVO(Career career) {
        this.careerId = career.getCareerId();
        this.careerName = career.getCareerName();
    }

    public String getCareerName() {
        return careerName;
    }

    public void setCareerName(String careerName) {
        this.careerName = careerName;
    }

    public Integer getCareerId() {
        return careerId;
    }

    public void setCareerId(Integer careerId) {
        this.careerId = careerId;
    }
}
