package io.spring.learning.common.money;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.persistence.Embeddable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

@Embeddable
public class Percentage implements Serializable {

    private static final long serialVersionUID = 8077279865855620752L;

    private BigDecimal value;

    @JsonCreator
    public Percentage(BigDecimal value){
        initValue(value);
    }

    public Percentage(double value){
        initValue(BigDecimal.valueOf(value));
    }

    @SuppressWarnings("unused")
    private Percentage(){}

    private void initValue(BigDecimal value){
        value = value.setScale(2, RoundingMode.HALF_UP);
        if(value.compareTo(BigDecimal.ZERO) == -1 || value.compareTo(BigDecimal.ONE) == 1){
            throw new IllegalArgumentException("Percentage value must be between 0 and 1");
        } 
        this.value = value;
    }

    public static Percentage valueOf(String string){
        if(string == null || string.length() == 0){
            throw new IllegalArgumentException("The Percentage value is required");
        }
        boolean isPercentage = string.endsWith("%");
        if(isPercentage){
            int index = string.lastIndexOf("%");
            string = string.substring(0, index);
        }
        BigDecimal value = new BigDecimal(string);
        if(isPercentage){
            value = value.divide(new BigDecimal(100));
        }

        return new Percentage(value);
    }

    public static Percentage zero(){
        return new Percentage(0);
    }

    public static Percentage oneHundred(){
        return new Percentage(1);
    }

    public Percentage add(Percentage percentage) throws IllegalArgumentException{
        return new Percentage(value.add(percentage.value));
    }

    public double asDouble(){
        return value.doubleValue();
    }

    @JsonValue
    public BigDecimal asBigDecimal(){
        return value;
    }

    public boolean equals(Object o) {
		if (!(o instanceof Percentage)) {
			return false;
		}
		return value.equals(((Percentage) o).value);
	}

	public int hashCode() {
		return value.hashCode();
	}

	public String toString() {
		return value.multiply(new BigDecimal("100")).setScale(0) + "%";
	}
}
