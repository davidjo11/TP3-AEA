package tp2.Exercice2;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import tp2.Exercice1.Edge;
import tp2.Exercice1.Graph;
import tp2.Exercice1.ValuedGraph;
import tp2.Exercice1.Vertex;
import tp2.Exercice1.exception.VertexNotFoundException;

public class MST implements MSTTools {

	/*
	 * (non-Javadoc)
	 * @see tp2.Exercice2.MSTTools#runPrim(tp2.Exercice1.Graph)
	 * If a graph is empty then the algorithm terminates immediately. Thus, we assume otherwise.

The algorithm starts with a tree consisting of a single vertex, and continuously increases its size one edge at a time, until it spans all vertices.

Input: A non-empty connected weighted graph with vertices V and edges E (the weights can be negative).
Initialize: Vnew = {x}, where x is an arbitrary node (starting point) from V, Enew = {}
Repeat until Vnew = V:
Choose an edge {u, v} with minimal weight such that u is in Vnew and v is not (if there are multiple edges with the same weight, any of them may be picked)
Add v to Vnew, and {u, v} to Enew
Output: Vnew and Enew describe a minimal spanning tree
	 */
	@Override
	public Iterator<Edge> runPrim(Graph g) {
		// TODO Auto-generated method stub
		List<Integer> q = g.listVertexInt();

		//Le numéro d'un sommet pris au hasard
		Integer s = null;

		s = g.listVertex().get(new Random().nextInt(q.size())).getVertex();

		List<Integer> lu = new ArrayList<Integer>();
		List<Edge> lv = new ArrayList<Edge>();

		lu.add(s);
		/*
		Iterator<Vertex> it = g.listVertex().iterator();
		
		while(it.hasNext())
			System.out.println(it.next());*/
		
		Edge minValuedEdge = null, aux;
		Integer u = null, v = null;
		int i=1;
		while(!lu.containsAll(g.listVertexInt())){
			//On mélange la liste histoire de prendre les arêtes de manière arbitraire
			Collections.shuffle(lu);
			Iterator<Integer> itlu = lu.iterator();
			//On recherche l'arete de valeur minimale parmi toutes celles dont l'un des sommets est u
			while(itlu.hasNext()){
				u = itlu.next();
//				System.out.println("Tour U "+ i +":"+u);
				//Pour chaque arete de issue du sommet u on regarde si le sommet v non présent dans lu est lié au sommet u
				Iterator<Integer> itlv = g.listVertexInt().iterator();
				while(itlv.hasNext()){
					v = itlv.next();
//					System.out.println("Tour V "+ i +":"+v);
					if(!lu.contains(v)){
						aux = g.getEdge(u, v);
						aux = (aux == null) ? g.getEdge(v, u): aux;
//						System.out.println(aux == null);
						if(aux != null){
							if(minValuedEdge == null)
								minValuedEdge = aux;
							else if(minValuedEdge.getValue() > aux.getValue()){
								minValuedEdge = aux;
							}
						}
					}
				}
			}
			if(minValuedEdge == null){
				return lv.iterator();
			}
			else if(!lu.contains(minValuedEdge.getVertex()[1].getVertex())){
				lu.add(minValuedEdge.getVertex()[1].getVertex());
//				System.out.println("Ajout 2nd: "+minValuedEdge.getVertex()[0].getVertex()+"/"+minValuedEdge.getVertex()[1].getVertex());
			}
			else {
				lu.add(minValuedEdge.getVertex()[0].getVertex());
//				System.out.println("Ajout 1er: "+minValuedEdge.getVertex()[0].getVertex()+"/"+minValuedEdge.getVertex()[1].getVertex());
			}
			lv.add(minValuedEdge);
			minValuedEdge = null;
			i++;
		}

		return lv.iterator();
	}

	@Override
	public Iterator<Edge> runKruskal(Graph g) {
		// TODO Auto-generated method stub
		
		Edge[] le = (Edge[]) g.listEdges().toArray();
		
		List<Edge> f = new ArrayList<Edge>();
		
		Edge min = le[le.length], aux = null;
		
		Collections.sort(g.listEdges(), c);
		
		
		return null;
	}

	public static void main(String[] args){
		MST mst = new MST();

		try {
			Graph g = new ErdosRenyi().generateErdosRenyiGraph(6, (float) 0.5);

			PrintWriter pw = new PrintWriter("graphPrim.txt");

			List<Vertex> lv = g.listVertex();
			Iterator<Vertex> it = lv.iterator();
			pw.print("graph G {\n");

			while(it.hasNext()){
				pw.print(it.next().getVertex()+";\n");
			}
			Iterator<Edge> ite = mst.runPrim(g);
			while(ite.hasNext()){
				Edge edge = ite.next();
				pw.print(edge.getVertex()[0].getVertex() + " -- " + edge.getVertex()[1].getVertex() + " [label=\"" + edge.getValue() + "\"];\n");
			}
			
			pw.print("}");

			pw.close();
			
			pw = new PrintWriter("graphorigin.txt");
			
			pw.print("graph G {\n");

			it = lv.iterator();
			while(it.hasNext()){
				pw.print(it.next().getVertex()+";\n");
			}
			
			ite = g.listEdges().iterator();
			while(ite.hasNext()){
				Edge edge = ite.next();
				pw.print(edge.getVertex()[0].getVertex() + " -- " + edge.getVertex()[1].getVertex() + " [label=\"" + edge.getValue() + "\"];\n");
			}
			
			pw.print("}");
			
			pw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

}
