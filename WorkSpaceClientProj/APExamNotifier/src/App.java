import java.io.File;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JFileChooser;
//2457 rows
public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFileChooser fileChooser= new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		fileChooser.showOpenDialog(null);
		File file =fileChooser.getSelectedFile();
		DataMatrix data= new DataMatrix(file);
		System.out.println(data);
		/*DataMatrix.setPeriodIndex(2);
		DataMatrix.setTeacherIndex(3);
		DataMatrix.setSubjectIndex(4);
		data.sortByPeriod();
		data.sortBySubject();
		data.sortByTeacher();
		data.makeTeachersList();
		System.out.println(data.getTeachersList());
		//System.out.println(data);
		URLReader url =new URLReader("https://www.montgomeryschoolsmd.org/schools/poolesvillehs/staff/directory.aspx");
		data.makeTeachersEmails(url.getContents());
		System.out.println(data.getTeachersEmails());
		System.out.println(data.checkEmails());
		data.editEmails(8, "SOMETHING");
		System.out.println(data.getTeachersEmails());*/
		
		
	}

}
