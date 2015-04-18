package tools.comparator;

import java.util.Comparator;

import tp2.Exercice1.Edge;

public class EdgeComparator implements Comparator<Edge>{

	@Override
	public int compare(Edge e1, Edge e2) {
		// TODO Auto-generated method stub
		Float val1 = e1.getValue(), val2 = e2.getValue();
		if(val1 == val2)
			return 0;
		else if(val1 > val2)
			return 1;
		else return -1;
	}


}
