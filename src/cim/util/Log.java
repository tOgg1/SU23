package cim.util;

/**
 * Created with IntelliJ IDEA.
 * User: Mayacat
 * Date: 3/8/13
 * Time: 1:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class Log
{
    public static void d(String tag, String content)
    {
        System.out.println("[Debug " + tag + "]: " + content);
    }

    public static void e(String tag, String content)
    {
        System.err.println("[Debug " + tag + "]: " + content);
    }
}
