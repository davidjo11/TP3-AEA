package tools.comparator;

import java.util.Comparator;

import tp2.Exercice1.Vertex;
/**
 * 
 * @author david
 *Compare deux sommets en fonction de leurs degrés dans l'ordre decroissant.
 */
public class VertexDegreeComparator implements Comparator<Vertex>{

	public int compare(Vertex v1, Vertex v2) {
		if(v1.getDegree() > v2.getDegree())
			return -1;
		else if(v1.getDegree() < v2.getDegree())
			return 1;
		else return 0;
	}
	
}
