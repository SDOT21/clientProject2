

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.aspose.cells.CellArea;
import com.aspose.cells.Cells;
import com.aspose.cells.DataSorter;
import com.aspose.cells.SortOrder;
import com.aspose.cells.Worksheet;

public class Project {
	//private static final String FILE_NAME = "ClientProjectTest.xlsx";
	public static XSSFWorkbook copySheet(File file) {
		 try {
	            FileInputStream excelFile = new FileInputStream(file);
	            XSSFWorkbook readWB = new XSSFWorkbook(excelFile);
	            Sheet datatypeSheet = readWB.getSheetAt(0);
	            XSSFWorkbook writeWB = new XSSFWorkbook();
	            XSSFSheet sheet = writeWB.createSheet("Copy Data");
	            int rowNum=0;
	            Iterator<Row> iterator = datatypeSheet.iterator();
	            while (iterator.hasNext()) {
	            	Row writeRow=sheet.createRow(rowNum++);
	                Row currentRow = iterator.next();
	                Iterator<Cell> cellIterator = currentRow.iterator();
	                int colNum = 0;
	                while (cellIterator.hasNext()) {
	                	
	                    Cell currentCell = cellIterator.next();
	                    //getCellTypeEnum shown as deprecated for version 3.15
	                    //getCellTypeEnum ill be renamed to getCellType starting from version 4.0
	                    Cell cell = writeRow.createCell(colNum++);
	                    if (currentCell.getCellType() == CellType.STRING) {
	                        cell.setCellValue((String) currentCell.getStringCellValue() );
	                        System.out.print(currentCell.getStringCellValue() + "--");
	                    } else if (currentCell.getCellType() == CellType.NUMERIC) {
	                    	cell.setCellValue((Double) currentCell.getNumericCellValue());
	                        System.out.print(currentCell.getNumericCellValue() + "--");
	                    }

	                }
	            }
	         
	            File copy=new File("DataCopy.xlsx");
	            FileOutputStream outputStream = new FileOutputStream(copy);
	            writeWB.write(outputStream);
	            writeWB.close();
	            readWB.close();
	            //return writeWB;
	        	XSSFSheet worksheet = writeWB.getSheetAt(0);
	   		 
	    		//Get the cells collection in the sheet
	    		Cells cells = worksheet.getCells();
	    		 
	    		//Obtain the DataSorter object in the workbook
	    		DataSorter sorter = workbook.getDataSorter();
	    		 
	    		//Set the first order
	    		sorter.setOrder1(SortOrder.ASCENDING);
	    		 
	    		//Define the first key.
	    		sorter.setKey1(0);
	    		 
	    		//Set the second order
	    		sorter.setOrder2(SortOrder.ASCENDING);
	    		 
	    		//Define the second key
	    		sorter.setKey2(1);
	    		 
	    		//Create a cells area (range).
	    		CellArea ca = new CellArea();
	    		 
	    		//Specify the start row index.
	    		ca.StartRow = 1;
	    		//Specify the start column index.
	    		ca.StartColumn = 0;
	    		//Specify the last row index.
	    		ca.EndRow = 9;
	    		//Specify the last column index.
	    		ca.EndColumn = 2;
	    		 
	    		//Sort data in the specified data range (A2:C10)
	    		sorter.sort(cells, ca);
	    		 
	    		//Saving the excel file
	    		writeWB.save("AsposeSortedData_Out.xls");
	          //  return copy;
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } catch (NullPointerException e) {
		            JFrame frame =new JFrame();
		            JOptionPane.showMessageDialog(frame,"The file was not selected");
	        }
		return null;
		
		 
	}
	public static File chooseFile() {
		JFileChooser fileChooser= new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		fileChooser.showOpenDialog(null);
		File file =fileChooser.getSelectedFile();
		return file;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File inputFile=chooseFile();
		/*while(fileState==false) {
			inputFile= chooseFile();
			if(inputFile !=null ) {
				fileState= true;
			}
		}*/
		 
		sortData(copySheet(inputFile));
		
		
		//File sortedFile=new File("Sorted.xlsx");
		
		
		
	    }
	}

