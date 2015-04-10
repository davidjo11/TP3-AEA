package tp2.Exercice2;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import tp2.Exercice1.Edge;
import tp2.Exercice1.Graph;
import tp2.Exercice1.Vertex;
import tp2.utils.Tools;

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

		//Le numéro d'un sommet pris au hasard.
		Integer s = null;

		s = g.listVertex().get(Tools.RD.nextInt(q.size())).getVertex();

		List<Integer> lu = new ArrayList<Integer>();
		List<Edge> lv = new ArrayList<Edge>();

		lu.add(s);
		
		Edge minValuedEdge = null, aux;
		int u = 0, v = 0;
		while(!lu.containsAll(g.listVertexInt())){
			//On mélange la liste histoire de prendre les arêtes de manière arbitraire
			Collections.shuffle(lu);
			Iterator<Integer> itlu = lu.iterator();
			//On recherche l'arete de valeur minimale parmi toutes celles dont l'un des sommets est u
			while(itlu.hasNext()){
				u = itlu.next();
				//Pour chaque arete de issue du sommet u on regarde si le sommet v non présent dans lu est lié au sommet u
				Iterator<Edge> itlv = g.getEdge(u).iterator();
				while(itlv.hasNext()){
					aux = itlv.next();
					if(aux.getVertex()[0].getVertex() == u)
						v = aux.getVertex()[1].getVertex();
					else v = aux.getVertex()[0].getVertex();
					System.out.println("Pour "+u+" U contient "+v +"? "+lu.contains(v));
					if(!lu.contains(v)){
						aux = g.getEdge(u, v);
						if(aux != null){
							System.out.println("Arete "+u+" et "+v+" et val: "+aux.getValue());
							if(minValuedEdge == null)
								minValuedEdge = aux;
							else if(minValuedEdge.getValue() > aux.getValue()){
								minValuedEdge = aux;
							}
						}
					}
				}
			}
			//Si on n'a pas trouvé d'arête alors on retourne le graphe partiel.
			if(minValuedEdge == null){
				return lv.iterator();
			}
			//Si on en a trouvé une alors on retourne ajoute le sommet v dans l'ensemble u.
			else if(!lu.contains(minValuedEdge.getVertex()[1].getVertex())){
				lu.add(minValuedEdge.getVertex()[1].getVertex());
			}
			else {
				lu.add(minValuedEdge.getVertex()[0].getVertex());
			}
			lv.add(minValuedEdge);
			minValuedEdge = null;
		}

		return lv.iterator();
	}

	@Override
	public Iterator<Edge> runKruskal(Graph g) {
		// TODO Auto-generated method stub
		int tag = 1;
		
		List<Edge> f = new ArrayList<Edge>(), edges = g.listEdges();
		
		//La liste des objets sommets (vertex).
		List<Vertex> v = new ArrayList<Vertex>();
		//Contiendra les sommets réprésentés par leurs numéros.
		List<Integer> vi = new ArrayList<Integer>();
		
		//Tri des arêtes dans l'ordre croissant.
		Collections.sort(edges, Tools.EDGECOMPARATOR);
		
		Edge min = edges.get(0);
		Vertex v1 = null, v2 = null, aux = null;

		//On ajoute l'arête ayant le poids le plus petit (premier élément de la liste edges).
		f.add(edges.get(0));
		
		//On ajoute les sommets de l'arête à l'ensemble v.
		v.add(min.getVertex()[0]);
		min.getVertex()[0].setTag(tag);
		vi.add(min.getVertex()[0].getVertex());
		
		v.add(min.getVertex()[1]);
		min.getVertex()[1].setTag(tag);
		vi.add(min.getVertex()[1].getVertex());
		
		tag++;
		
		edges.remove(0);
		
		Iterator<Edge> it = edges.iterator();
		Iterator<Vertex> itv = null;
		boolean var;
		while(it.hasNext()){
			min = it.next();
			Vertex s1 = min.getVertex()[0], s2 = min.getVertex()[1];
			//On vérifie que l'ajout de l'arête min ne créera pas de cycles.
			
			//1er cas: les deux sommets de l'arêtes sont présents dans le graphe partiel.
			if(vi.contains(s1.getVertex()) && vi.contains(s2.getVertex())){
				itv = v.iterator();
				v1 = null; v2 = null;
				var = false;
				while(itv.hasNext() && !var){
					aux = itv.next();
					if(aux.getVertex() == s1.getVertex())
						v1 = aux;
					else if(aux.getVertex() == s2.getVertex())
						v2 = aux;
					var = (v1 != null && v2 != null);
				}
				//Si les sommets v1 et v2 ont des tags différents alors ils ne sont pas reliés (pas de création de cycle).
				if(v1.getTag() != v2.getTag()){
					itv = v.iterator();
					//On associe le tag de v1 à tout les sommets ayant le même tag que v2.
					while(itv.hasNext()){
						aux = itv.next();
						if(aux.getTag() != v2.getTag())
							aux.setTag(v1.getTag());
					}
				}
				else {//Les tags des deux sommets sont égaux alors on retourne le graphe partiel.
					return f.iterator();
				}
			}//Cas suivants:
			else if(!vi.contains(s1.getVertex()) && !vi.contains(s2.getVertex())){//- les deux sommets ne sont pas présents dans le graphe partiel;
				s1.setTag(tag);
				v.add(s1);
				vi.add(s1.getVertex());
				
				s2.setTag(tag);
				v.add(s2);
				vi.add(s2.getVertex());
				
				tag++;
			}
			else if(!vi.contains(s1.getVertex()) && vi.contains(s2.getVertex())){//- le premier sommet s1 n'est pas présent dans le graphe partiel;
				itv = v.iterator();
				while(itv.hasNext() && s1.getTag() == 0){
					aux = itv.next();
					if(aux.getVertex() == s2.getVertex())//On lui associe le tag du sommet s2 auquel il est lié (grâce à l'arête min).
						s1.setTag(aux.getTag());
				}
				v.add(s1);
				vi.add(s1.getVertex());
			}else if(vi.contains(s1.getVertex()) && !vi.contains(s2.getVertex())){//- le deuxième sommet s2 n'est pas présent dans le graphe partiel;
				itv = v.iterator();
				while(itv.hasNext() && s2.getTag() == 0){
					aux = itv.next();
					if(aux.getVertex() == s1.getVertex())//On lui associe le tag du sommet s1 auquel il est lié (grâce à l'arête min).
						s2.setTag(aux.getTag());
				}
				v.add(s2);
				vi.add(s2.getVertex());
			}
		}
		
		return f.iterator();
	}

	public static void main(String[] args){
		MST mst = new MST();

		try {
			Graph g = new ErdosRenyi().generateErdosRenyiGraph(6, (float) 0.5);

			//Construction du MST par Prim
			PrintWriter pw = new PrintWriter("graphPrim.txt");

			List<Vertex> lv = g.listVertex();
			Iterator<Vertex> it = lv.iterator();
			pw.print("graph G {\n");

			Iterator<Edge> ite = mst.runPrim(g);
			while(ite.hasNext()){
				Edge edge = ite.next();
				pw.print(edge.getVertex()[0].getVertex() + " -- " + edge.getVertex()[1].getVertex() + " [label=\"" + edge.getValue() + "\"];\n");
			}
			
			pw.print("}");

			pw.close();

			//Construction du MST par Kruskal.
			pw = new PrintWriter("graphKruskal.txt");

			pw.print("graph G {\n");

			ite = mst.runKruskal(g);
			while(ite.hasNext()){
				Edge edge = ite.next();
				pw.print(edge.getVertex()[0].getVertex() + " -- " + edge.getVertex()[1].getVertex() + " [label=\"" + edge.getValue() + "\"];\n");
			}
			
			pw.print("}");

			pw.close();
			
			//Construction du graphe d'origine.
			pw = new PrintWriter("graph.txt");
			
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
