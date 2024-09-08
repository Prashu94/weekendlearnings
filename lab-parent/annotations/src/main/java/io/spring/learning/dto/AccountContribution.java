package io.spring.learning.dto;

import io.spring.learning.common.money.MonetaryAmount;
import io.spring.learning.common.money.Percentage;

import java.io.Serializable;
import java.util.Set;

public class AccountContribution implements Serializable {

    private String accountNumber;

    private MonetaryAmount amount;

    private Set<Distribution> distributions;

    public AccountContribution(String accountNumber, MonetaryAmount amount, Set<Distribution> distributions) {
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.distributions = distributions;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public MonetaryAmount getAmount() {
        return amount;
    }

    public void setAmount(MonetaryAmount amount) {
        this.amount = amount;
    }

    public Set<Distribution> getDistributions() {
        return distributions;
    }

    public void setDistributions(Set<Distribution> distributions) {
        this.distributions = distributions;
    }

    public Distribution getDistribution(String beneficiary){
        for(Distribution distribution: distributions){
            if(distribution.beneficiary.equals(beneficiary)){
                return distribution;
            }
        }
        throw new IllegalArgumentException("No Such Distribution for"+ beneficiary);
    }

    public static class Distribution implements Serializable{

        private String beneficiary;

        private MonetaryAmount amount;

        private Percentage percentage;

        private MonetaryAmount totalSavings;

        public Distribution(String beneficiary, MonetaryAmount amount, Percentage percentage,
                            MonetaryAmount totalSavings){
            this.beneficiary = beneficiary;
            this.percentage = percentage;
            this.amount = amount;
            this.totalSavings = totalSavings;
        }

        public String getBeneficiary() {
            return beneficiary;
        }

        public void setBeneficiary(String beneficiary) {
            this.beneficiary = beneficiary;
        }

        public MonetaryAmount getAmount() {
            return amount;
        }

        public void setAmount(MonetaryAmount amount) {
            this.amount = amount;
        }

        public Percentage getPercentage() {
            return percentage;
        }

        public void setPercentage(Percentage percentage) {
            this.percentage = percentage;
        }

        public MonetaryAmount getTotalSavings() {
            return totalSavings;
        }

        public void setTotalSavings(MonetaryAmount totalSavings) {
            this.totalSavings = totalSavings;
        }

        @Override
        public String toString() {
            return "Distribution{" +
                    "beneficiary='" + beneficiary + '\'' +
                    ", amount=" + amount +
                    ", percentage=" + percentage +
                    ", totalSavings=" + totalSavings +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "AccountContribution{" +
                "accountNumber='" + accountNumber + '\'' +
                ", amount=" + amount +
                '}';
    }
}
