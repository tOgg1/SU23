package cim.net;

import cim.models.CalendarObject;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Mayacat
 * Date: 3/7/13
 * Time: 12:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class Bucket implements Serializable
{
    public CalendarObject data;
    public final int flag;

    public Bucket(CalendarObject data, int flag)
    {
        this.data = data;
        this.flag = flag;
    }
}
