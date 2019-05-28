import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

import javax.swing.JFileChooser;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataMatrix {
	//private static final String FILE_NAME = "ClientProjectTest.xlsx";
	private ArrayList<ArrayList<String>> data;
	public static int teacherIndex;
	public static int periodIndex;
	public  static int subjectIndex;
	private ArrayList<String> header;
	private ArrayList<String>teachersList;
	private ArrayList<String>teachersEmails;
	
	public DataMatrix(File file,Workbook workbook) {
		//makes data matrix from excel sheet. All other variables are initiated but are not useful
		data= new ArrayList<ArrayList<String>>();
	           
			
		     Sheet datatypeSheet = workbook.getSheetAt(0);
		     Iterator<Row> iterator = datatypeSheet.iterator();
		     Row currentRow = iterator.next();
		     //int numCells=currentRow.getPhysicalNumberOfCells();
		     
		    int i=0;
		     while (iterator.hasNext()) {
		         Iterator<Cell> cellIterator = currentRow.iterator();
		         data.add(new ArrayList<String>()); 
		         currentRow = iterator.next();
		         while (cellIterator.hasNext()) {
		        	 
		        	 String cell="";
		        	 
		        	 Cell currentCell = cellIterator.next();
			        	 
		        	 if (currentCell.getCellType() == CellType.NUMERIC) {
			        	cell= ""+currentCell.getNumericCellValue();
		        	 }
		        	 else if (currentCell.getCellType() == CellType.STRING) {
		        		 cell= ""+currentCell.getStringCellValue();
		        	 }
		        	 data.get(i).add(cell);
		        	
		         }
		       
		         i++;
		  
		     }
		     
		//workbook.close();
		subjectIndex=-1;
		teacherIndex=-1;
		periodIndex=-1;
		teachersList=null;
		teachersEmails=null;
		header=data.get(0);
		data.remove(0);
		removeExtraneous();
		
	}
	private void removeExtraneous() {
		//helper method that gets rid of extraneous data (students that do not have teachers and periods)
		int i=0;
		while(i<data.size()) {
			if((data.get(i)).size()<6) {
				//Systemt.out.println(data.get(i));
				data.remove(data.get(i));
			}
			else {
			i++;
			}
		}
	}
	public String toString() {
			
		return header+"\n"+data.toString();
	}
	public void sortBySubject() {
		//Sorts data alphabetically by subject
		Comparator<ArrayList<String>> c=new SubjectComparator();
		Collections.sort(data, c);
	}
	public void sortByTeacher() {
		//Sorts data alphabetically by teacher
		Comparator<ArrayList<String>> c=new TeacherComparator();
		Collections.sort(data, c);
	}
	public void sortByPeriod() {
		//Sorts data alphabetically by period
		Comparator<ArrayList<String>> c=new PeriodComparator();
		Collections.sort(data, c);
	}
	public void makeTeachersList() {
		//gets list of teachers to email
		teachersList=new ArrayList<String>();
		for(int i=0;i<data.size();i++) {
			if(teachersList.indexOf(data.get(i).get(teacherIndex).replaceAll(" ", ""))==-1) {
				teachersList.add(data.get(i).get(teacherIndex).replaceAll(" ", ""));
			}
		}

	}
	public ArrayList<String> getTeachersList(){
		return teachersList;
	}
	public static int getTeacherIndex() {
		return teacherIndex;
	}
	public static void setTeacherIndex(int teacherIndex) {
		DataMatrix.teacherIndex = teacherIndex;
	}
	public static int getPeriodIndex() {
		return periodIndex;
	}
	public static  void setPeriodIndex(int periodIndex) {
		DataMatrix.periodIndex = periodIndex;
	}
	public static int getSubjectIndex() {
		return subjectIndex;
	}
	public static void setSubjectIndex(int subjectIndex) {
		DataMatrix.subjectIndex = subjectIndex;
	}
	public void makeTeachersEmails(String contents) {
		//gets teacher emails based on list
		teachersEmails = new ArrayList<String>();
		String before;
		for(int i=0;i<teachersList.size();i++) {
			int endIndex=contents.indexOf(teachersList.get(i)+"@mcpsmd.org");
			if(endIndex!=-1) {
				int j=endIndex;
				before=contents.substring(0,endIndex);
				while(before.indexOf("mailto",j)==-1) {
					j--;
				}
				teachersEmails.add(contents.substring(j+7, endIndex+11+teachersList.get(i).length()));
			}
			else {
				teachersEmails.add("NOT FOUND");
			}
			
		}
		

	}
	public String[] getHeader(){
		String[] list=new String[header.size()];
		for(int i=0;i<header.size();i++) {
			list[i]=header.get(i);
		}
		return list;
	}
	public void makeFirst(int i) {
		String temp= header.get(0);
		header.set(0, header.get(i));	
		header.set(i, temp);
	}
	public ArrayList <String> getTeachersEmails(){
		return teachersEmails;
	}
	public boolean checkEmails() {
		//returns true if all teachers have emails
		if(teachersEmails.indexOf("NOT FOUND")==-1){
			return true;
		}
		return false;
	}
	public void editEmails(int index,String email) {
		teachersEmails.set(index,email);
	}
		
}
