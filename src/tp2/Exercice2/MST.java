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
		Vertex s = null;

		s = g.listVertex().get(Tools.RD.nextInt(q.size()));

		//Initialisation du tableau des aretes.
		Edge[][] edgeU = new Edge[g.listVertex().size()][1];

		for(int i=1;i<=g.listVertex().size();i++){
			edgeU[i-1] = g.getEdge(i).toArray(edgeU[i-1]);
		}

		List<Vertex> lu = new ArrayList<Vertex>();
		List<Edge> lv = new ArrayList<Edge>();
		//Ce tableau associera l'indice i (pour le sommet i du graphe) au nombre de sommets non marqués auxquels le sommet i est liés.
		int[] vertexAllMarked = new int[g.listVertex().size()];

		//Tout est initialisé à false.
		boolean[][] edges = new boolean[g.listVertex().size()][g.listVertex().size()];

		s.mark();
		lu.add(s);

		Iterator<Vertex> itlu ;
		Edge minValuedEdge = null, aux;
		Iterator<Edge> itlv ;
		Vertex u = null, v = null;
		//Au lieu de faire un ensemble pour les sommmets u et un autre pour les sommets v, on marque le sommet si celui est dans l'ensemble u.
		//Cela évite d'utiliser la fonction contains.
		while(!g.allMarked()){
			//On mélange la liste histoire de prendre les arêtes de manière arbitraire.
			//			Collections.shuffle(lu);
//			System.out.println("================================> New turn!\nLu contient "+lu.size());
			itlu = lu.iterator();
			//On recherche l'arete de valeur minimale parmi toutes celles dont l'un des sommets est u.
			while(itlu.hasNext()){
				u = itlu.next();
				//				System.out.println(u.getVertex()+" son cpt: "+vertexAllMarked[u.getVertex()-1]);
				//Si tout les sommets auxquels u est liés ne pas sont marqués.
				//				if(vertexAllMarked[u.getVertex()-1] > -1){
				//				List<Edge> laux = g.getEdge(u.getVertex());
				//				itlv = laux.iterator();
//				System.out.println(u.getVertex()+" a "+edgeU[u.getVertex()-1].length+" voisins: , il en reste "+vertexAllMarked[u.getVertex()-1]+".");
				for(int i=0;i<edgeU[u.getVertex()-1].length;i++){
					aux = edgeU[u.getVertex()-1][i];
					//On récupère le sommet v.
					if(aux.getVertex()[0] == u)
						v = aux.getVertex()[1];
					else v = aux.getVertex()[0];
					//						System.out.println(""+u.getVertex()+"est lié à "+v.getVertex() +", ce dernier est-il marqué? "+v.isMarked());
					//Si le sommet v n'est pas dans la liste lu on la compare avec l'arête de valeur minimale courante.
					if(!v.isMarked()){
						//on augmente le compteur du sommet u car on vient de "découvrir" un sommet v non marqué.
						if(!edges[u.getVertex()-1][v.getVertex()-1]){
							vertexAllMarked[u.getVertex()-1]++;
							edges[u.getVertex()-1][v.getVertex()-1] = true;
						}
						//							System.out.println("Arete "+u.getVertex()+" et "+v.getVertex()+" et val: "+aux.getValue());
						//Compte le nb de sommets non marqués.
						if(minValuedEdge == null){
							minValuedEdge = aux;
						}
						else if(minValuedEdge.getValue() > aux.getValue()){
							minValuedEdge = aux;
						}
						//						}
					}
					//Si à la fin du parcours des arêtes contenant le sommet u, aucune n'a été retenue (minEgdeU = null) cela implique que:
					// - soit le sommet n'a pas d'arêtes
					// - soit tous les sommets auxquels il est lié sont marqués.
				}
			}
			//Si on n'a pas trouvé d'arête alors on retourne le graphe partiel.
			if(minValuedEdge == null){
				return lv.iterator();
			}
			//Si on en a trouvé une alors on retourne ajoute le sommet v dans l'ensemble u.
			else if(!minValuedEdge.getVertex()[1].isMarked()){
				//edges[u][i] sera à vrai si le sommet i est marqué.  
				vertexAllMarked[minValuedEdge.getVertex()[0].getVertex()-1]-- ;
				if(vertexAllMarked[minValuedEdge.getVertex()[0].getVertex()-1] == 0){
					//					vertexAllMarked[minValuedEdge.getVertex()[0].getVertex()-1] = -1;
					lu.remove(minValuedEdge.getVertex()[0]);
				}
				lu.add(minValuedEdge.getVertex()[1]);
				minValuedEdge.getVertex()[1].mark();
//				System.out.println(minValuedEdge.getVertex()[1].getVertex()+" ajouté! ("+minValuedEdge.getVertex()[1].isMarked()+")");
			}
			else {
				//Si le sommet que l'on vient de marqué est lié au sommet i, alors on diminue de 1 son nb de sommets liés non marqués.
				vertexAllMarked[minValuedEdge.getVertex()[1].getVertex()-1]-- ;
				if(vertexAllMarked[minValuedEdge.getVertex()[1].getVertex()-1] == 0){
					//					vertexAllMarked[minValuedEdge.getVertex()[1].getVertex()-1] = -1;
					lu.remove(minValuedEdge.getVertex()[1]);
				}
				lu.add(minValuedEdge.getVertex()[0]);
				minValuedEdge.getVertex()[0].mark();
//				System.out.println(minValuedEdge.getVertex()[0].getVertex()+" ajouté! ("+minValuedEdge.getVertex()[0].isMarked()+")");
			}
			lv.add(minValuedEdge);
			minValuedEdge = null;
		}

		return lv.iterator();
	}

	@SuppressWarnings("unused")
	@Override
	public Iterator<Edge> runKruskal(Graph g) {
		// TODO Auto-generated method stub
		int tag = 1;

		List<Edge> f = new ArrayList<Edge>(), edges = g.listEdges();

		//La liste des objets sommets (vertex).
		List<Vertex> v = new ArrayList<Vertex>();

		//Tri des arêtes dans l'ordre croissant.
		Collections.sort(edges, Tools.EDGECOMPARATOR);

		Edge min = edges.get(0), edge = null;
		Vertex v1 = null, v2 = null, aux = null;

		//On ajoute l'arête ayant le poids le plus petit (premier élément de la liste edges).
		f.add(edges.get(0));

		//On ajoute les sommets de l'arête à l'ensemble v.
		//		v.add(min.getVertex()[0]);
		min.getVertex()[0].setTag(tag);

		//		v.add(min.getVertex()[1]);
		min.getVertex()[1].setTag(tag);

		tag++;

		edges.remove(0);

		Iterator<Edge> it = edges.iterator();
		Iterator<Edge> itf = null;
		//Encore une fois (comme avec Prim), l'utilisation des tags nous évite d'utiliser des méthodes comme contains.
		while(it.hasNext()){
			min = it.next();
			v1 = min.getVertex()[0];
			v2 = min.getVertex()[1];
			//On vérifie que l'ajout de l'arête min ne créera pas de cycles.

			//1er cas: les deux sommets de l'arêtes sont présents dans le graphe partiel (autrement dit leurs tagues sont différents de zéro).
			if(v1.getTag() != 0 && v2.getTag() != 0){
				//Si les sommets v1 et v2 ont des tags différents alors ils ne sont pas reliés (pas de création de cycle).
				if(v1.getTag() != v2.getTag()){
					//Itérateur sur le graphe partiel (ensemble des arêtes courant).
					itf = f.iterator();
					//On associe le tag de v1 à tout les sommets ayant le même tag que v2.
					while(itf.hasNext()){
						edge = itf.next();
						//Sachant que 2 sommets liés par une arête dans le graphe partiel partagent le même tag.
						if(edge.getVertex()[0].getTag() == v2.getTag()){
							edge.getVertex()[0].setTag(v1.getTag());
							edge.getVertex()[1].setTag(v1.getTag());
						}
					}
					//On ajoute l'arête au graphe  partiel.
					f.add(min);
				}//Sinon on ajoute pas l'arête (=> cycle).
			}//Cas suivants:
			else if(v1.getTag() == 0 && v2.getTag() == 0){//- les deux sommets ne sont pas présents dans le graphe partiel;
				v1.setTag(tag);
				//				v.add(v1);

				v2.setTag(tag);
				//				v.add(v2);

				f.add(min);
				tag++;
			}
			else if(v1.getTag() == 0 && v2.getTag() != 0){//- seul le premier sommet s1 n'est pas présent dans le graphe partiel;
				v1.setTag(v2.getTag());
				//				v.add(v1);

				f.add(min);
			}
			else if(v1.getTag() != 0 && v2.getTag() == 0){//- le deuxième sommet s2 n'est pas présent dans le graphe partiel;
				v2.setTag(v1.getTag());
				//				v.add(v2);

				f.add(min);
			}
		}

		return f.iterator();
	}

	public static void main(String[] args){
		MST mst = new MST();

		try {
			Graph g = new ErdosRenyi().generateErdosRenyiGraph(1000, (float) 0.5);
			PrintWriter pw;

			List<Vertex> lv = g.listVertex();
			Iterator<Vertex> it = lv.iterator();
			Iterator<Edge>  ite;
			long start;

			Edge edge;

			//Construction du graphe d'origine.
			pw = new PrintWriter("graph.txt");
			pw.print("graph G {\n");

			it = lv.iterator();
			while(it.hasNext()){
				pw.print(it.next().getVertex()+";\n");
			}

			ite = g.listEdges().iterator();
			while(ite.hasNext()){
				edge = ite.next();
				pw.print(edge.getVertex()[0].getVertex() + " -- " + edge.getVertex()[1].getVertex() + " [label=\"" + edge.getValue() + "\"];\n");
			}

			pw.print("}");
			pw.close();

			//Construction du MST par Prim
			pw = new PrintWriter("graphPrim.txt");
			pw.print("graph G {\n");

			start = System.currentTimeMillis();
			ite = mst.runPrim(g);
			System.out.println("Temps d'exéc pour Prim: "+ ((System.currentTimeMillis() - start))+"ms.");
			while(ite.hasNext()){
				edge = ite.next();
				pw.print(edge.getVertex()[0].getVertex() + " -- " + edge.getVertex()[1].getVertex() + " [label=\"" + edge.getValue() + "\"];\n");
			}

			pw.print("}");
			pw.close();

			//L'utilisation de Prim a pour conséquence le marquage d'une partie des sommets du graphe.
			g.unmarkAll();

			//Construction du MST par Kruskal.
			pw = new PrintWriter("graphKruskal.txt");

			pw.print("graph G {\n");

			start = System.currentTimeMillis();
			ite = mst.runKruskal(g);
			System.out.println("Temps d'exéc pour Kruskal: "+ ((System.currentTimeMillis() - start))+"ms.");
			while(ite.hasNext()){
				edge = ite.next();
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
