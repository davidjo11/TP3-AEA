package tools.comparator;

import java.util.Comparator;

import tp2.Exercice1.Edge;

/**
 * 
 * @author david
 *
 *Compare les aretes en fonction de leurs poids dans l'ordre croissant.
 */
public class EdgeComparator implements Comparator<Edge>{

	public int compare(Edge e1, Edge e2) {
		Float val1 = e1.getValue(), val2 = e2.getValue();
		if(val1 == val2)
			return 0;
		else if(val1 > val2)
			return 1;
		else return -1;
	}


}
