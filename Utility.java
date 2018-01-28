package org.tq.util;

import java.io.File;
import java.io.IOException;

import org.testng.annotations.DataProvider;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

 

public class Utility {
	
	 
	public static String[][] readXlsFile() throws BiffException, IOException {
		System.out.println("In Read Xls File");
		File f = new File(System.getProperty("user.dir")+"//src//test//resources//ServiceTypes.xls");
		Workbook wb = Workbook.getWorkbook(f);
		Sheet sheet[] = wb.getSheets();
		Sheet  fsheet=sheet[0];
		int rows = fsheet.getRows();
		int cols = fsheet.getColumns();
		String data[][] = new String[rows][cols];
		for(int i = 0 ; i< rows;i++)
		{
			for(int j=0;j<cols;j++)
			{
				Cell cell = fsheet.getCell(j, i);
				data[i][j]=cell.getContents();
				System.out.println(data[i][j]);
			}
		}
		return data;
	
	}


}
