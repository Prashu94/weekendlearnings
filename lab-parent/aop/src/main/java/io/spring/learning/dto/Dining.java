package io.spring.learning.dto;

import io.spring.learning.common.datetime.SimpleDate;
import io.spring.learning.common.money.MonetaryAmount;

import java.io.Serializable;
import java.util.Objects;

public class Dining implements Serializable {

    private MonetaryAmount monetaryAmount;

    private String creditCardNumber;

    private String merchantNumber;

    private SimpleDate date;

    public Dining(MonetaryAmount monetaryAmount, String creditCardNumber, String merchantNumber, SimpleDate date) {
        this.monetaryAmount = monetaryAmount;
        this.creditCardNumber = creditCardNumber;
        this.merchantNumber = merchantNumber;
        this.date = date;
    }

    public static Dining createDining(String amount, String creditCardNumber,
                                      String merchantNumber){
        return new Dining(MonetaryAmount.valueOf(amount), creditCardNumber, merchantNumber,
                SimpleDate.today());
    }

    public static Dining createDining(String amount, String creditCardNumber,
                                      String merchantNumber, int month, int day, int year){
        return new Dining(MonetaryAmount.valueOf(amount), creditCardNumber, merchantNumber,
                new SimpleDate(month, day, year));
    }

    public MonetaryAmount getMonetaryAmount() {
        return monetaryAmount;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public String getMerchantNumber() {
        return merchantNumber;
    }

    public SimpleDate getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dining dining = (Dining) o;
        return Objects.equals(monetaryAmount, dining.monetaryAmount) && Objects.equals(creditCardNumber, dining.creditCardNumber) && Objects.equals(merchantNumber, dining.merchantNumber) && Objects.equals(date, dining.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(monetaryAmount, creditCardNumber, merchantNumber, date);
    }

    @Override
    public String toString() {
        return "Dining{" +
                "monetaryAmount=" + monetaryAmount +
                ", creditCardNumber='" + creditCardNumber + '\'' +
                ", merchantNumber='" + merchantNumber + '\'' +
                ", date=" + date +
                '}';
    }
}
