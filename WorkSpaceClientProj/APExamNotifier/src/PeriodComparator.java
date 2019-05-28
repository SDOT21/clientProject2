import java.util.ArrayList;
import java.util.Comparator;

public class PeriodComparator implements Comparator<ArrayList<String>> {
	public int compare(final ArrayList<String> list1, final ArrayList<String> list2) {
		
		
		return list1.get(DataMatrix.getPeriodIndex()).compareTo(list2.get(DataMatrix.getPeriodIndex()));
	}
	

}
