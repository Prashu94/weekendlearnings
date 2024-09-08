package io.spring.learning.common.datetime;

import java.beans.PropertyEditorSupport;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Locale;

import io.spring.learning.common.datetime.SimpleDate;

/**
 * A formatter for Simple date properties. Converts object values to well formatted strings and strings back to values.
 */
public class SimpleDateEditor extends PropertyEditorSupport{

    private DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.LONG, Locale.ENGLISH);

    @Override
    public String getAsText(){
        SimpleDate date = (SimpleDate) getValue();

        if(date == null){
            return "";
        }else {
            return dateFormat.format(date.asDate());
        }
    }

    @Override
    public void setAsText(String text){
        try{
            setValue(SimpleDate.valueOf(dateFormat.parse(text)));
        }catch(ParseException e){
            throw new IllegalArgumentException("Could not parse date: " + e.getMessage(), e);
        }
    }
}
