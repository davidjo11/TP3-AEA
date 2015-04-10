package tp2.utils;

import java.util.Comparator;
import java.util.Random;

import tp2.Exercice1.Edge;

public class Tools {

	//SINGLETONS
	public final static Comparator<Edge> EDGECOMPARATOR = new EdgeComparator();
	
	public final static Random RD = new Random();
}
