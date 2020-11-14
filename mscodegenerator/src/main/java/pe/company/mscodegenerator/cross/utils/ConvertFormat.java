package pe.company.mscodegenerator.cross.utils;

public class ConvertFormat 
{
	public static Boolean IsNullOrEmpty(Object o)
	{
		if(o==null) return true;
		
		if(o.toString().trim().isEmpty()) return true;
		
		return false;
	}
}
