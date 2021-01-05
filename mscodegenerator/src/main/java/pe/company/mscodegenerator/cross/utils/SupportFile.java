package pe.company.mscodegenerator.cross.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

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
			
			try(FileWriter escribir = new FileWriter(file,StandardCharsets.UTF_8,false))
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
    
    
    public static Boolean ZipMultipleFiles(String zipFileName,Map<String,String> files)
    {
		try
		{
			try(ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipFileName)))
			{
				byte[] bytes;
				
				for (Map.Entry<String, String> f: files.entrySet())
				{
					zos.putNextEntry(new ZipEntry(f.getKey()));
					
					bytes = Files.readAllBytes(Paths.get(f.getValue()));
	                zos.write(bytes, 0, bytes.length);
	                zos.closeEntry();					
				}
			}
			
			return true;
		}
		catch(Exception ex)
		{
			Logger.getLogger(SupportFile.class.getName()).log(Level.SEVERE, null, ex);
			
			return false;
		}		    	
    }       
}
