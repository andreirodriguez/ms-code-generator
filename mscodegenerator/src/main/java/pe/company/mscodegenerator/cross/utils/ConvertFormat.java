package pe.company.mscodegenerator.cross.utils;

public class ConvertFormat 
{
	public static Boolean IsNullOrEmpty(Object o)
	{
		if(o==null) return true;
		
		if(o.toString().trim().isEmpty()) return true;
		
		return false;
	}
	
	public static String getLowerCamelCase(String text) 
    {
    	text = text.trim();
    	
		if(text=="") return "";
		
		text = text.substring(0,1).toLowerCase() + text.substring(1);
		
		return text;
    }   	
}
