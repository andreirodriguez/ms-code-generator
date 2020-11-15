package pe.company.mscodegenerator.cross.utils;

import java.io.File;
import java.io.FileWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SupportFile 
{
    public static String CombinePath(String path1,String path2)
    {
        return new File(path1,path2).toString();
    }
    
    public static Boolean WriteFilePlainText(String path,StringBuilder text)
    {
		try
		{
			File file = new File(path);
			
			try(FileWriter escribir = new FileWriter(file,false))
			{
				escribir.write(text.toString());
				escribir.close();
			}
			
			return true;
		}
		catch(Exception ex)
		{
			Logger.getLogger(SupportFile.class.getName()).log(Level.SEVERE, null, ex);
			
			return false;
		}		    	
    }   
    
    public static Boolean CreateDirectory(String directoryPath)
    {
	    File directory = new File(directoryPath);
	    
	    if (!directory.exists()) directory.mkdirs();
	    
	    return true;
    }
}
