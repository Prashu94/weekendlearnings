package io.spring.learning.entity;

import io.spring.learning.common.money.MonetaryAmount;
import io.spring.learning.common.money.Percentage;
import io.spring.learning.dto.AlwaysAvailable;
import io.spring.learning.dto.BenefitAvailabilityPolicy;
import io.spring.learning.dto.Dining;
import io.spring.learning.dto.NeverAvailable;

import javax.persistence.*;

@Entity
@Table(name = "T_RESTAURANT")
public class Restaurant {

    @Id
    @Column(name="ID")
    private Long entityId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "MERCHANT_NUMBER")
    private String number;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "BENEFIT_PERCENTAGE"))
    private Percentage benefitPercentage;

    @Transient
    private BenefitAvailabilityPolicy benefitAvailabilityPolicy;

    protected Restaurant(){}

    public Restaurant(String number, String name){
        this.name = name;
        this.number = number;
    }

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Percentage getBenefitPercentage() {
        return benefitPercentage;
    }

    public void setBenefitPercentage(Percentage benefitPercentage) {
        this.benefitPercentage = benefitPercentage;
    }

    public BenefitAvailabilityPolicy getBenefitAvailabilityPolicy() {
        return benefitAvailabilityPolicy;
    }

    public void setBenefitAvailabilityPolicy(BenefitAvailabilityPolicy benefitAvailabilityPolicy) {
        this.benefitAvailabilityPolicy = benefitAvailabilityPolicy;
    }

    public MonetaryAmount calculateBenefitFor(Account account, Dining dining){
        if(benefitAvailabilityPolicy.isBenefitAvailableFor(account, dining)){
            return dining.getMonetaryAmount().multiplyBy(benefitPercentage);
        }else {
            return MonetaryAmount.zero();
        }
    }

    @Access(AccessType.PROPERTY)
    @Column(name = "BENEFIT_AVAILABILITY_POLICY")
    public void setDbBenefitAvailabilityPolicy(String policyCode){
        if("A".equals(policyCode)){
            benefitAvailabilityPolicy = AlwaysAvailable.INSTANCE;
        }else if("N".equals(policyCode)){
            benefitAvailabilityPolicy = NeverAvailable.INSTANCE;
        }else {
            throw new IllegalArgumentException("Not supported policy code" + policyCode);
        }
    }

    @Access(AccessType.PROPERTY)
    @Column(name = "BENEFIT_AVAILABILITY_POLICY")
    public String getDbBenefitAvailabilityPolicy(){
        if (benefitAvailabilityPolicy == AlwaysAvailable.INSTANCE) {
            return "A";
        } else if (benefitAvailabilityPolicy == NeverAvailable.INSTANCE) {
            return "N";
        } else {
            throw new IllegalArgumentException("No policy code for "
                    + benefitAvailabilityPolicy.getClass());
        }
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "entityId=" + entityId +
                ", name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", benefitPercentage=" + benefitPercentage +
                ", benefitAvailabilityPolicy=" + benefitAvailabilityPolicy +
                '}';
    }


}
