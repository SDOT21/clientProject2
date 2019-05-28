import java.util.ArrayList;
import java.util.Comparator;

public class SubjectComparator implements Comparator<ArrayList<String>>{

	public int compare(final ArrayList<String> list1, final ArrayList<String> list2) {
		return list1.get(DataMatrix.getSubjectIndex()).compareTo(list2.get(DataMatrix.getSubjectIndex()));
	}
	
}
