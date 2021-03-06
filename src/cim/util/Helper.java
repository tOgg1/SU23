package cim.util;

import cim.models.CalendarObject;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;

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
	 * @param
	 * @return
	 */

	public static Time getTime(int hrs, int mins) {
		Calendar c = getEmptyCalendar();
		c.set(Calendar.HOUR, hrs);
		c.set(Calendar.MINUTE, mins);
		return new Time(c.getTimeInMillis());
	}
	public static Timestamp getTimestampFromObjects(Date date, Time time)
	{
		Calendar c = getEmptyCalendar();
		String[] split = date.toString().split("-");
		if(!split[0].equals(null) && !split[1].equals(null) && !split[2].equals(null))
		{
			int year = Integer.parseInt(split[0]);
			int month = Integer.parseInt(split[1]);
			int day = Integer.parseInt(split[2]);
			c.set(Calendar.YEAR, year);
			c.set(Calendar.MONTH, month);
			c.set(Calendar.DAY_OF_MONTH, day);
		}
		String[] split2 = time.toString().split(":");
		if(!split2[0].equals(null) && !split2[1].equals(null) && !split2[2].equals(null))
		{
			int hours = Integer.parseInt(split2[0]);
			int minutes = Integer.parseInt(split2[1]);
			int seconds = Integer.parseInt(split2[2]);
			c.set(Calendar.HOUR, hours);
			c.set(Calendar.MINUTE, minutes);
			c.set(Calendar.SECOND, seconds);
		}
		return new Timestamp(c.getTimeInMillis());		
		
	}
	public static Timestamp getTime(int year, int month, int day, int hour, int minutes, int seconds)
	{
		Calendar c = getEmptyCalendar();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month);
		c.set(Calendar.DAY_OF_MONTH, day);
		c.set(Calendar.HOUR, hour);
		c.set(Calendar.MINUTE, minutes);
		c.set(Calendar.SECOND, seconds);
		return new Timestamp(c.getTimeInMillis());

	}
	public static Date getDate(int year, int month, int day) {
		Calendar c = getEmptyCalendar();
		c.set(year, month-1, day);
		return new Date(c.getTimeInMillis());
	}

	public static String join(Collection<?> s, String delimiter) {
		StringBuilder builder = new StringBuilder();
		Iterator iter = s.iterator();
		while (iter.hasNext()) {
			builder.append(iter.next());
			if (!iter.hasNext()) {
				break;                  
			}
			builder.append(delimiter);
		}
		return builder.toString();
	}
	public static String formatTime(Time t, String format) {
		Calendar c = getEmptyCalendar();
		c.setTimeInMillis(t.getTime());
		DateFormat df = new SimpleDateFormat(format);
		return df.format(t);
	}

	public static String formatDate(Date d, String format) {
		Calendar c = getEmptyCalendar();
		c.setTimeInMillis(d.getTime());
		DateFormat df = new SimpleDateFormat(format);
		return df.format(d);
	}

	public static Timestamp getNow() {
		return new Timestamp(System.currentTimeMillis());
	}

	private static Calendar getEmptyCalendar() {
		return new GregorianCalendar(0,0,0,0,0);
	}

	public static boolean equalsById(CalendarObject obj1, CalendarObject obj2)
	{
		return obj1.getId() == obj2.getId();
	}

	public static boolean containsById(Collection<? extends CalendarObject> col, CalendarObject obj)
	{
		for(CalendarObject obj1 : col)
		{
			if(equalsById(obj1, obj))
			{
				return true;
			}
		}
		return false;
	}

	public static void removeById(Collection<? extends CalendarObject> col, CalendarObject obj)
	{
		CalendarObject tbr = null;
		for(CalendarObject obj1 : col)
		{
			if(equalsById(obj1, obj))
			{
				tbr = obj1;
			}
		}
		col.remove(tbr);
	}
}
