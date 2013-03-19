package cim.util;

import java.awt.Font;
import java.io.File;

// for � f� fontawesome;last ned jar fil fra http://mvnrepository.com/artifact/org.webjars/font-awesome
// flere iconer finner du her http://www.jensd.de/wordpress/?p=178


public class Fonts {
	
	public static Font getFontAwesome() {
		return new Font("FontAwesome", Font.PLAIN, 14);
		
	}

	public enum AwesomeIcons
	{
		ICON_OK('\uf00c'),
		ICON_REMOVE('\uf00d'),
		ICON_PENCIL('\uf040'),
		ICON_HAPPY('\uf04a'),
		ICON_ANGRY('\uf04c'),
		ICON_ARROW_LEFT('\uf060'),
		ICON_ARROW_RIGHT('\uf061'),
		ICON_ARROW_UP('\uf062'),
		ICON_ARROW_DOWN('\uf063'),
		ICON_GROUP('\uf0c0'),
	    ICON_SAVE('\uf0c7'),
	    ICON_USER('\uf007'),
	    ICON_TIME('\uf017'),
	    ICON_MAP_MARKER('\uf041'),
	    ICON_CARET_LEFT('\uf0d9'), 
	    ICON_CARET_RIGHT('\uf0da'),
	    ICON_CARET_DOWN('\uf0d7'),
		ICON_CARET_UP('\uf0d8');
	    private final Character character;
	 
	    private AwesomeIcons(Character character)
	    {
	        this.character = character;
	    }
	 
	    public Character character()
	    {
	        return character;
	    }
	 
	    public String toString()
	    {
	        return character.toString();
	    }
	    
	}

}
