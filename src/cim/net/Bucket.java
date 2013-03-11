package cim.net;

import cim.models.CalendarObject;
import cim.util.Helper;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Mayacat
 * Date: 3/7/13
 * Time: 12:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class Bucket
{
    public static final int FLAG_CREATE = 1;
    public static final int FLAG_READ = 2;
    public static final int FLAG_UPDATE = 3;
    public static final int FLAG_DELETE= 4;
    public static final int FLAG_EVENT = 5;

    private static String[] flags = {"CREATE", "READ", "UPDATE", "DELETE", "EVENT"};

    public CalendarObject data;
    public final int flag;

    public Bucket(CalendarObject data, int indexFlag, int flag)
    {
        this.data = data;
        this.flag = flag;
    }

    public static String decodeFlag(int flag)
    {
        return flags[flag];
    }

   
}
