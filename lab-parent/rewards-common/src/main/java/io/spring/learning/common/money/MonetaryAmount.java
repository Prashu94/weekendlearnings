package io.spring.learning.common.money;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.persistence.Embeddable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

@Embeddable
public class MonetaryAmount implements Serializable{

    private static final long serialVersionUID = -3734467432803577280L;

    private BigDecimal value;

    @JsonCreator
    public MonetaryAmount(BigDecimal value){
        initValue(value);
    }

    public MonetaryAmount(double value){
        initValue(BigDecimal.valueOf(value));
    }

    @SuppressWarnings("unused")
    private MonetaryAmount(){}

    private void initValue(BigDecimal value){
       this.value = value.setScale(2, RoundingMode.HALF_EVEN);
    }


    public static MonetaryAmount valueOf(String string){
        if (string == null || string.length() == 0){
            throw new IllegalArgumentException("The Monetary amount value is required");
        }

        if(string.startsWith("$")){
            int index = string.indexOf("$");
            string = string.substring(index + 1);
        }
        BigDecimal value = new BigDecimal(string);
        return new MonetaryAmount(value);
    }

    public static MonetaryAmount zero(){
        return new MonetaryAmount(0);
    }

    public MonetaryAmount add(MonetaryAmount amount){
        return new MonetaryAmount(value.add(amount.value));
    }

    public MonetaryAmount subtract(MonetaryAmount amount){
        return new MonetaryAmount(value.subtract(amount.value));
    }

    public MonetaryAmount multiplyBy(BigDecimal amount){
        return new MonetaryAmount(value.multiply(amount));
    }

    public BigDecimal divide(MonetaryAmount amount){
        return value.divide(amount.value);
    }

    public MonetaryAmount divideBy(BigDecimal amount){
        return new MonetaryAmount(value.divide(amount));
    }

    public MonetaryAmount multiplyBy(Percentage percentage){
        return new MonetaryAmount(value.multiply(percentage.asBigDecimal()));
    }

    public boolean greaterThan(MonetaryAmount amount){
        return value.compareTo(amount.value) > 0;
    }

    public Double asDouble(){
        return value.doubleValue();
    }

    @JsonValue
    public BigDecimal asBigDecimal(){
        return value;
    }

    
	public boolean equals(Object o) {
		if (!(o instanceof MonetaryAmount)) {
			return false;
		}
		return value.equals(((MonetaryAmount) o).value);
	}

	public int hashCode() {
		return value.hashCode();
	}

	public String toString() {
		return "$" + value.toString();
	}
    
}
