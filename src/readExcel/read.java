package readExcel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class read 
{   
    Vector cot = new Vector();
    int dem =1;
    boolean kiemtra = true;
   public boolean ReadDataFromExcelFile(String ExcelFile, String table) throws IOException
   {
       FileInputStream InputStream = new FileInputStream(new File(ExcelFile));
	Workbook workbook = getWorkbook(InputStream, ExcelFile);
        Sheet FirstSheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = FirstSheet.iterator();
        while (rowIterator.hasNext()) 
       {
           Row row = rowIterator.next();
           Iterator<Cell> cellIterator = row.cellIterator();
           Vector value = new Vector();
           if(dem==1)
           {    int demcell = 0;
                while (cellIterator.hasNext()) 
                {
                   Cell cell = cellIterator.next();
                   CellType cellType = cell.getCellTypeEnum();
                   cot.add(row.getCell(demcell));
                   demcell++;
                }
                dem++;
           }
           else
            {   int demcell = 0;
                while (cellIterator.hasNext()) 
                {
                   Cell cell = cellIterator.next();
                   CellType cellType = cell.getCellTypeEnum();
                   value.add(row.getCell(demcell));
                   demcell++;
                }
                dem++;
            }
            if((dem>2) && (value!=null) && (value.size()>0))
            {
                boolean giatri = new inSert().run(table, cot, value);
                if(giatri==false) 
                {
                    kiemtra=false;
                    break;
                }
            }
       }
        workbook.close();
        InputStream.close();
        return kiemtra;
   }
   private Workbook getWorkbook(FileInputStream InputStream, String ExcelFile)
	        throws IOException {
	    Workbook WorkBook = null;
	 
	    if (ExcelFile.endsWith("xlsx")) {
	        WorkBook = new XSSFWorkbook(InputStream);
	    } else if (ExcelFile.endsWith("xls")) {
	        WorkBook = (Workbook) new HSSFWorkbook(InputStream);
	    } else {
	        throw new IllegalArgumentException("The specified file is not Excel file");
	    }
	    return WorkBook;
	}
}
