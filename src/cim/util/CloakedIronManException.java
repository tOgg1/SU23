package cim.util;

/**
 * Created with IntelliJ IDEA.
 * User: Mayacat
 * Date: 3/7/13
 * Time: 1:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class CloakedIronManException extends Exception
{

    public CloakedIronManException()
    {
        super();
        Log.e("Exception", "Some CloakedIronManException occured");

    }

    public CloakedIronManException(String s)
    {
        super(s);
        Log.e("Exception", s);
    }
    
    public CloakedIronManException(String s, Exception inner) {
    	super(s, inner);
    }
}
