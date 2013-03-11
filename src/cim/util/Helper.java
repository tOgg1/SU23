package cim.util;

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
}
