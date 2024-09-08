package io.spring.learning.common.datetime;

import java.io.Serializable;
import java.net.Socket;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * A simple wrapper around a calendar for working with dates like 12/29/1977. Does not consider time.
 */
public class SimpleDate implements Serializable{

    private static final long serialVersionUID = 2285962420279644602L;

    private GregorianCalendar base;

    public SimpleDate(int month, int day, int year){
        init(new GregorianCalendar(year, month - 1, day)); // month is 0 based
    }

    SimpleDate(long time){
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTimeInMillis(time);
        init(cal);
    }

    private SimpleDate(){
        init(new GregorianCalendar());
    }

    private void init(GregorianCalendar cal){
        this.base = trimToDays(cal);
    }

    private GregorianCalendar trimToDays(GregorianCalendar cal){
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal;
    }

    public Date asDate(){
        return base.getTime();
    }

    public long inMilliSeconds(){
        return asDate().getTime();
    }

    public int compareTo(Object date){
        SimpleDate other = (SimpleDate) date;
        return asDate().compareTo(other.asDate());
    }

    public boolean equals(Object day){
        if(!(day instanceof SimpleDate)){
            return false;
        }
        SimpleDate other = (SimpleDate) day;
        return (base.equals(other.base));
    }

    public int hashCode(){
        return 29 * base.hashCode();
    }

    /**
     * Returns today's date. A convienence method.
     */
    public static SimpleDate today(){
        return new SimpleDate();
    }

    public static SimpleDate valueOf(Date date){
        return valueOf(date.getTime());
    }

    public static SimpleDate valueOf(long time){
        return new SimpleDate(time);
    }

    public String toString(){
        return new SimpleDateFormat().format(base.getTime());
    }

}
