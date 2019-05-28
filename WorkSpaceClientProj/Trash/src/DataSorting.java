import com.aspose.cells.*;

public class DataSorting {
	public static void sort

	public static void main(String[] args) throws Exception {


		//Instantiating a Workbook object
		Workbook workbook = new Workbook("Blank.xlsx");
		 
		//Accessing the first worksheet in the Excel file
		Worksheet worksheet = workbook.getWorksheets().get(0);
		 
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
		workbook.save("AsposeSortedData_Out.xls");

	}
}