package driverFactory;

import org.openqa.selenium.WebDriver;

import commonFunctions.FunctionLibrary;
import utilities.ExcelFileUtil;

public class DriverScript {

	String inputpath="./FileInput/Controller2.xlsx";
	String outputpath="./FileOutput/nyresults.xlsx";
	String TCSheet="MasterTestCases";
	WebDriver driver;
	
	public void startTest() throws Throwable {
		String module_status="";
		String module_new="";
		ExcelFileUtil xl=new ExcelFileUtil(inputpath);
		
		for(int i=1;i<=xl.rowCount(TCSheet);i++) 
		{
			if(xl.getCellData(TCSheet, i, 2).equalsIgnoreCase("Y"))
			{
				String TCModule=xl.getCellData(TCSheet, i,1);
				
				for(int j=1;j<=xl.rowCount(TCModule);j++)
				{
					System.out.println(xl.rowCount(TCModule));
					String Description=xl.getCellData(TCModule, j, 0);
					String otype=xl.getCellData(TCModule, j, 1);
					String ltype=xl.getCellData(TCModule, j, 2);
					String lvalue=xl.getCellData(TCModule, j, 3);
					String tdata=xl.getCellData(TCModule, j, 4);
					
					try {
						if(otype.equalsIgnoreCase("startBrowser"))
						{
							System.out.println("HELLO");
							driver=FunctionLibrary.startBrowser();
						}
						if(otype.equalsIgnoreCase("openUrl"))
						{
							System.out.println("HELLO");
							FunctionLibrary.openUrl();
						}
						if(otype.equalsIgnoreCase("clickAction"))
						{
							FunctionLibrary.clickAction(ltype, lvalue);
						}
						if(otype.equalsIgnoreCase("validateTitle"))
						{
							FunctionLibrary.validateTitle(tdata);
						}
						if(otype.equalsIgnoreCase("waitForElement"))
						{
							FunctionLibrary.waitForElement(ltype, lvalue, tdata);
						}
						if(otype.equalsIgnoreCase("moveToElement"))
						{
							FunctionLibrary.moveToElement(ltype, lvalue);
						}
						if(otype.equalsIgnoreCase("scrollToElement"))
						{
							FunctionLibrary.scrollToElement(ltype, lvalue);
						}
						if(otype.equalsIgnoreCase("closeBrowser"))
						{
							FunctionLibrary.closeBrowser();
						}
						xl.setCellData(TCModule, j, 5, "Pass", outputpath);
						module_status="True";
					} catch (Exception e) {
						System.out.println(e.getMessage());
						xl.setCellData(TCModule, j, 5, "Fail", outputpath);
						module_new="False";
						// TODO: handle exception
					}
				}
					if(module_status.equalsIgnoreCase("True"))
					{
						xl.setCellData(TCSheet, i, 3, "Pass", outputpath);
					}
				
					if(module_new.equalsIgnoreCase("False"))
					{
						xl.setCellData(TCSheet, i, 3, "Fail", outputpath);
					}
				}
				
				else
				{
					xl.setCellData(TCSheet, i, 3, "Blocked", outputpath);
				}
			}
		}
			
		
	}

