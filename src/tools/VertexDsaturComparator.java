package tools;

import java.util.Comparator;

import tp2.Exercice1.Vertex;

public class VertexDsaturComparator implements Comparator<Vertex> {

	@Override
	public int compare(Vertex v1, Vertex v2) {
		// TODO Auto-generated method stub
		if(v1.getTag() > v2.getTag())
			return -1;
		else if(v1.getTag() < v2.getTag())
			return 1;
		else if(v1.getDegree() > v2.getDegree())
			return -1;
		else if(v1.getDegree() < v2.getDegree())
			return 1;
		else return 0;
	}

}
