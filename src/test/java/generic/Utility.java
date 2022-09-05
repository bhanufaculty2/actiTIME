package generic;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Utility 
{
	public static String getPropertyValue(String filePath,String key) throws Exception
	{
		Properties p=new Properties();
		
		p.load(new FileInputStream(filePath));
		String value=p.getProperty(key);
		
		return value;
	}
	
	public static String getXLData(String path,String sheet,int r,int c) throws Exception
	{
		Workbook wb = WorkbookFactory.create(new FileInputStream(path));
		String value = wb.getSheet(sheet).getRow(r).getCell(c).getStringCellValue();
		wb.close();
		return value;
	}
}
