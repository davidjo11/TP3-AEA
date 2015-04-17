package tp2.utils;

import java.io.PrintWriter;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

import tp2.Exercice1.Edge;
import tp2.Exercice1.Graph;
import tp2.Exercice1.ValuedGraph;
import tp2.Exercice1.Vertex;
import tp2.Exercice1.exception.VertexAlreadyExistException;
import tp2.Exercice1.exception.VertexNotFoundException;

public class Tools {

	//SINGLETONS
	public final static Comparator<Edge> EDGECOMPARATOR = new EdgeComparator();

	public final static Random RD = new Random();


	//METHODES
	public static void toTextFormat(Graph g, String file) throws Exception{

		PrintWriter pw = new PrintWriter(file);

		String line = "";

		Iterator<Vertex> it = g.listVertex().iterator();
		Iterator<Edge> ite;
		Vertex v = null;
		Edge e = null;
		while(it.hasNext()){
			v = it.next();
			ite = g.getEdge(v.getVertex()).iterator();
			line = v.getVertex()+" ";
			while(ite.hasNext()){
				e = ite.next();
				if(!e.isMarked()){
					if(e.getVertex()[0] == v){
						line += e.getVertex()[1].getVertex()+" "+e.getValue()+" ";
					}else {
						line += e.getVertex()[0].getVertex()+" "+e.getValue()+" ";
					}
					e.marked();
				}
			}
			pw.print(line);
		}
		pw.close();
	}

	public static Graph loadGraphFromFile(String file) throws Exception{

		Graph g = new ValuedGraph();

		Scanner f = new Scanner(file);

		String line = "";
		String[] sommets = null;
		int som = 0;

		while(f.hasNextLine()){
			line = f.nextLine().trim();
			sommets = line.split(" ");
			if(sommets.length > 0){
				try{
					g.addVertexNumber(Integer.parseInt(sommets[0]));
				}
				catch(VertexAlreadyExistException e){
					//Rien à faire le sommets est déjà dans le graphe
				}
				som = Integer.parseInt(sommets[0]);
				if(sommets.length > 1)
					for(int i=1; i<sommets.length;i++){
						try{
							g.addEdge(som, Integer.parseInt(sommets[i++]), Float.parseFloat(sommets[i]));
						}
						catch(VertexNotFoundException e){
							//Le sommet lié au sommet som n'eciste pas dans le graphe g:
							g.addVertexNumber(Integer.parseInt(sommets[i-1]));
							g.addEdge(som, Integer.parseInt(sommets[i-1]), Float.parseFloat(sommets[i]));
						}
					}
			}
		}

		f.close();

		return g;

	}
}
