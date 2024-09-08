package io.spring.learning.common.money;
import java.beans.PropertyEditorSupport;

import org.springframework.util.StringUtils;

public class PercentageEditor extends PropertyEditorSupport{

    @Override
    public String getAsText() {
        Percentage percentage = (Percentage) getValue();
        if(percentage == null){
            return "";
        }else{
            return percentage.toString();
        }
        
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if(StringUtils.hasText(text)){
            setValue(Percentage.valueOf(text));
        }else{
            setValue(null);
        }
    }

    
}
