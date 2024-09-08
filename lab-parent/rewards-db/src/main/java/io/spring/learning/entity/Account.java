package io.spring.learning.entity;

import javax.persistence.*;

import io.spring.learning.common.money.MonetaryAmount;
import io.spring.learning.common.money.Percentage;
import io.spring.learning.dto.AccountContribution;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "T_ACCOUNT")
public class Account {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long entityId;

    @Column(name = "NUMBER")
    private String number;

    @Column(name = "NAME")
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ACCOUNT_ID")
    private Set<Beneficiary> beneficiaries = new HashSet<Beneficiary>();

    protected Account() {
    }

    public Account(String number, String name){
        this.number = number;
        this.name = name;
    }

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Beneficiary> getBeneficiaries() {
        return Collections.unmodifiableSet(beneficiaries);
    }

    public void addBeneficiary(String beneficiaryName){
        addBeneficiary(beneficiaryName, Percentage.oneHundred());
    }

    public void addBeneficiary(String beneficiaryName, Percentage allocationPercentage){
        beneficiaries.add(new Beneficiary(beneficiaryName, allocationPercentage));
    }

    public Beneficiary getBeneficiary(String name){
        for(Beneficiary beneficiary: beneficiaries){
            if(beneficiary.getName().equals(name)){
                return beneficiary;
            }
        }

        throw new IllegalArgumentException("No Such Beneficiary with name: "+ name );
    }

    public void removeBeneficiary(String beneficiaryName){
        beneficiaries.remove(getBeneficiary(beneficiaryName));
    }

    public boolean isValid(){
        Percentage totalPercentage = Percentage.zero();
        for(Beneficiary b: beneficiaries){
            try{
                totalPercentage = totalPercentage.add(b.getAllocationPercentage());
            }catch (IllegalArgumentException e){
                return false;
            }
        }

        if(totalPercentage.equals(Percentage.oneHundred())){
            return true;
        }else{
            return false;
        }
    }

    public void setValid(boolean valid){}

    public AccountContribution makeContribution(MonetaryAmount amount){
        if(!isValid()){
            throw new IllegalArgumentException("Cannot make contribution to this account: it has " +
                    "invalid beneficiary allocations");
        }
        Set<AccountContribution.Distribution> distributions = distribute(amount);
        return new AccountContribution(getNumber(), amount, distributions);
    }

    private Set<AccountContribution.Distribution> distribute(MonetaryAmount amount){
        Set<AccountContribution.Distribution> distributions = new HashSet<>(beneficiaries.size());
        for(Beneficiary beneficiary: beneficiaries){
            MonetaryAmount distributionAmount =
                    amount.multiplyBy(beneficiary.getAllocationPercentage());
            beneficiary.credit(distributionAmount);
            AccountContribution.Distribution distribution =
                    new AccountContribution.Distribution(beneficiary.getName(),
                            distributionAmount, beneficiary.getAllocationPercentage(),
                            beneficiary.getSavings());
            distributions.add(distribution);
        }
        return distributions;
    }
    public void restoreBeneficiary(Beneficiary beneficiary){
        beneficiaries.add(beneficiary);
    }

    @Override
    public String toString() {
        return "Account{" +
                "entityId=" + entityId +
                ", number='" + number + '\'' +
                ", name='" + name + '\'' +
                ", beneficiaries=" + beneficiaries +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(entityId, account.entityId) && Objects.equals(number, account.number) && Objects.equals(name, account.name) && Objects.equals(beneficiaries, account.beneficiaries);
    }

    @Override
    public int hashCode() {
        return Objects.hash(entityId, number, name, beneficiaries);
    }
}
