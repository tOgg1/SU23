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
public class Bucket implements Serializable
{
    public static final int FLAG_CREATE = 1;
    public static final int FLAG_READ = 2;
    public static final int FLAG_UPDATE = 3;
    public static final int FLAG_DELETE= 4;
    public static final int FLAG_EVENT = 5;

    private static String[] flags = {"CREATE", "READ", "UPDATE", "DELETE", "EVENT"};

    public CalendarObject data;
    /**
     *  index represents the index of the data.getData() array that are to be inserted, updated or removed
     *  To support ranges of values, the index is a flag of the type 2^n, n > 0.
     */
    public final int indexFlag;
    public final int flag;

    public Bucket(CalendarObject data, int indexFlag, int flag)
    {
        this.data = data;
        this.indexFlag = indexFlag;
        this.flag = flag;
    }

    public static String decodeFlag(int flag)
    {
        return flags[flag];
    }

    /**
     * Typical index of array, not an indexFlag
     * @param index
     * @return
     */
    public static boolean isIndexFlagged(int indexFlag, int index)
    {
        int iterator = 1;
        iterator = iterator << index;
        return (indexFlag & index) > 0;
    }

    public static int[] getIndexes(int indexFlag)
    {
        int[] returnArray = new int[0];
        int iterator = 1;

        while((iterator = iterator << 1) > indexFlag)
        {
            if((indexFlag & iterator) > 0)
            {
                returnArray = Helper.expandOnElement(returnArray, (int)(Math.log(iterator)/Math.log(2)));
            }
        }
        return returnArray;
    }
}
