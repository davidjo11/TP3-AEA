package tp2.utils;

import java.util.Comparator;

import tp2.Exercice1.Edge;

public class Tools {

	//SINGLETON 
	public final static Comparator<Edge> SINGLETON = new EdgeComparator();
}
