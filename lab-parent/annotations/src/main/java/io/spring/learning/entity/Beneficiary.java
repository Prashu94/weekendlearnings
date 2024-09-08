package io.spring.learning.entity;

import io.spring.learning.common.money.MonetaryAmount;
import io.spring.learning.common.money.Percentage;

import javax.persistence.*;

@Entity
@Table(name = "T_ACCOUNT_BENEFICIARY")
public class Beneficiary {

    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long entityId;

    @Column(name = "NAME")
    private String name;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "ALLOCATION_PERCENTAGE"))
    private Percentage allocationPercentage;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "SAVINGS"))
    private MonetaryAmount savings = MonetaryAmount.zero();

    protected Beneficiary() {
    }

    public Beneficiary(String name, Percentage allocationPercentage){
        this.name = name;
        this.allocationPercentage = allocationPercentage;
    }

    public Beneficiary(String name, Percentage allocationPercentage, MonetaryAmount savings){
        this.name = name;
        this.allocationPercentage = allocationPercentage;
        this.savings = savings;
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

    public Percentage getAllocationPercentage() {
        return allocationPercentage;
    }

    public void setAllocationPercentage(Percentage allocationPercentage) {
        this.allocationPercentage = allocationPercentage;
    }

    public MonetaryAmount getSavings() {
        return savings;
    }

    public void setSavings(MonetaryAmount savings) {
        this.savings = savings;
    }

    public void credit(MonetaryAmount amount) {
        savings = savings.add(amount);
    }

    @Override
    public String toString() {
        return "Beneficiary [entityId=" + entityId + ", name=" + name + ", allocationPercentage=" + allocationPercentage
                + ", savings=" + savings + "]";
    }

    
    
}
