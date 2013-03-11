package cim.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Mayacat
 * Date: 3/8/13
 * Time: 1:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class Log
{
    private static File logFile;
    private static BufferedWriter logWriter;

    public static void d(String tag, String content)
    {
        String debug = "[Debug " + tag + "]: " + content;
        System.out.println(debug);
        writeToFile(debug);
    }

    public static void e(String tag, String content)
    {
        String err = "[Error " + tag + "]: " + content;
        System.err.println(err);
        writeToFile(err);
    }

    public static void setLogFile(File file)
    {
        logFile = file;
        try
        {
            logWriter = new BufferedWriter(new FileWriter(file, true));
        }
        catch(IOException e)
        {
            Log.e("Log", "Error setting log file");
            return;
        }
    }

    private static void writeToFile(String s)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aa");
        Date today = Calendar.getInstance().getTime();
        String timestamp = sdf.format(today);

        if(logFile != null && logWriter != null)
        {
            try
            {
                logWriter.newLine();
                logWriter.write(timestamp + " " + s);
                logWriter.flush();

            }
            catch(IOException e)
            {
                System.err.println("[Error Log]: Error writing to file");
                return;
            }
        }
    }

    public static void close()
    {
        try
        {
            logWriter.close();
        }
        catch(Exception e)
        {
            // Fuck off we're done already
        }
    }

    public static boolean clearLogFile()
    {
        try
        {
            logWriter.close();
            String name = logFile.getName();
            logFile.delete();
            logFile = new File(name);
            logWriter = new BufferedWriter(new FileWriter(logFile,  true));
            return true;
        }
        catch(Exception e)
        {
            return false;
        }
    }
}
