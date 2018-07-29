package util;

import java.io.File;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

// A class responsible for writing text to file
public class Printer
{
    private PrintWriter writer;

    public Printer(String path)
    {
	// Create directory if nonexistent
	File file = new File("data");
	if (!file.exists())
	    file.mkdir();

	// Initiate printwriter
	path = "data/" + path + ".dat";
	try
	{
	    writer = new PrintWriter(path, "UTF-8");
	} catch (Exception e)
	{
	    System.out.println("ERROR: Unable to write data to file, path: " + path);
	}

	// Write date to header of file
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	writer.println("# " + dateFormat.format(new Date()));
    }

    public void write(String a)
    {
	writer.println(a);
    }

    public void close()
    {
	writer.close();
    }
}
