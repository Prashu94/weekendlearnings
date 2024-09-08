package io.spring.learning.common.money;

import java.beans.PropertyEditorSupport;

import org.springframework.util.StringUtils;

public class MonetaryAmountEditor extends PropertyEditorSupport{

    @Override
    public String getAsText() {
        MonetaryAmount amount = (MonetaryAmount) getValue();
        if(amount == null){
            return "";
        }else{
            return super.getAsText();
        }
        
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if(StringUtils.hasText(text)){
            setValue(MonetaryAmount.valueOf(text));
        }else{
            setValue(null);
        }
    }

}
