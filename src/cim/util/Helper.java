package cim.util;

import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created with IntelliJ IDEA.
 * User: Mayacat
 * Date: 3/8/13
 * Time: 2:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class Helper
{

    public static int[] expandOnElement(int[] array, int element)
    {
        int[] newArray = new int[array.length+1];
        int i = 0;
        for(int n : array)
        {
            newArray[i++] = n;
        }
        newArray[i] = element;
        return newArray;
    }
    /**
     * Returns the first part of the string to the separator, and removes that part from the original string
     * @param str
     * @return
     */
    
    public static Time getTime(int hrs, int mins) {
    	Calendar c = getEmptyCalendar();
    	c.set(Calendar.HOUR, hrs);
    	c.set(Calendar.MINUTE, mins);
    	return new Time(c.getTimeInMillis());
    }
    
    public static Date getDate(int year, int month, int day) {
    	Calendar c = getEmptyCalendar();
    	c.set(year, month-1, day);
    	return new Date(c.getTimeInMillis());
    }
    
    private static Calendar getEmptyCalendar() {
    	return new GregorianCalendar(0,0,0,0,0);
    }
}
