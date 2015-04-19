package tp3.Exercice1;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import tools.Tools;
import tp2.Exercice1.Edge;
import tp2.Exercice1.Graph;
import tp2.Exercice1.Vertex;
import tp2.Exercice2.ErdosRenyi;
import tp3.Exercice1.exceptions.NotEnoughColorsException;

public class Coloration {

	public void runNaif(Graph g) throws NotEnoughColorsException {
		List<Vertex> vertex = g.listVertex();
		int mincolor = 0;
		Iterator<Vertex> itv = vertex.iterator();
		Vertex v = null, voisin = null;

		// Tableau qui pour le sommet i, contient dans voisins[i], l'ensemble
		// des sommets adjacents à celui-ci.
		Edge[][] voisins = new Edge[vertex.size()][1];

		// Pour le sommet n°i, si vColor[i][j] (j étant le rgb d'un couleur) est
		// égal à true alors un des voisins du sommet i est coloré par la
		// couleur j.
		// Rappel: à l'initialisation met toutes les cases du tableau à false.
		boolean[][] vColor = new boolean[vertex.size()][Tools.colorMax];
		int aux = 0;

		while (itv.hasNext()) {
			v = itv.next();
			voisins[v.getVertex() - 1] = g.getEdge(v.getVertex()).toArray(voisins[v.getVertex() - 1]);
		}

		itv = vertex.iterator();
		while (itv.hasNext()) {
			v = itv.next();
			mincolor = 0;
			aux = v.getVertex() - 1;

			// On recherche la couleur la plus petite.
			while (mincolor < Tools.colorMax && vColor[v.getVertex() - 1][mincolor]) {
				// vColor[v.getVertex()][i] == true alors un voisin de aux est
				// coloré par la couleur mincolor.
				mincolor += 1;
			}
			if (mincolor > Tools.colorMax) {
				throw new NotEnoughColorsException();
			}
			// Mise à jour du tableau des couleurs pour les voisins du sommet v
			for (int j = 0; j < voisins[aux].length; j++) {
				if (voisins[aux][j].getVertex()[0] == v) {
					voisin = voisins[aux][j].getVertex()[1];
					// On indique pour le voisin voisin non coloré de v, qu'une
					// couleur a été ajouté si
					// vColor[voisin.getVertex()][mincolor] == false.
					if (!vColor[voisin.getVertex() - 1][mincolor] && voisin.getColor() == -1) {
						vColor[voisin.getVertex() - 1][mincolor] = true;
					}// Si le voisin n'est pas coloré et que
						// vColor[voisin.getVertex()][mincolor] == false ou si
						// le voisin est coloré alors on ne fait rien.
				} else {
					voisin = voisins[aux][j].getVertex()[0];
					// On indique pour le voisin v non coloré de x, qu'une
					// couleur a été ajouté si vColor[v.getVertex()][mincolor]
					// == false.
					if (!vColor[voisin.getVertex() - 1][mincolor] && voisin.getColor() == -1) {
						vColor[voisin.getVertex() - 1][mincolor] = true;
					}// Si le voisin n'est pas coloré et que
						// vColor[voisin.getVertex()][mincolor] == false ou si
						// le voisin est coloré alors on ne fait rien. }
				}
			}
			v.setColor(mincolor);
		}
	}

	/*
	 * 1 : Ordonner les sommets par ordre décroissant de degré 
	 * 2 : Colorer le sommet de degré max avec 1 
	 * 3 : Choisir un sommet x non encore colorié
	 * ayant un DSAT max (si plusieurs sommets sont disponibles alors choisir
	 * celui qui a le deg max) 4 : Colorier x avec la plus petite couleur
	 * possible 5 : Répéter 3 jusqu'à ce que tous les sommets soit coloriés
	 */
	public void runDSATUR(Graph g) throws NotEnoughColorsException {

		/*
		 *  ************************** IMPORTANT ******************************
		 * 
		 * On va se servir du tag pour gérer le degré de saturation d'un sommet.
		 */
		List<Vertex> vertex = new ArrayList<Vertex>(g.listVertex());
		Iterator<Vertex> itv;
		Vertex v, x;

		// Tableau qui pour le sommet i, contient dans voisins[i], l'ensemble
		// des sommets adjacents à celui-ci.
		Edge[][] voisins = new Edge[vertex.size()][1];
		int mincolor = 0, i = 0;

		// Pour le sommet n°i, si vColor[i][j] (j étant le rgb d'un couleur) est
		// égal à true alors un des voisins du sommet i est coloré par la
		// couleur j.
		// Rappel: à l'initialisation met toutes les cases du tableau à false.
		boolean[][] vColor = new boolean[vertex.size()][Tools.colorMax];

		// 1
		Collections.sort(vertex, Tools.VERTEXDEGCOMPARATOR);

		itv = vertex.iterator();
		// Construction d'un tableau à 2 dimensions, voisins[i] contient un
		// tableau contenant l'ensemble des edges ayant le vertex n°i pour
		// sommet.
		while (itv.hasNext()) {
			v = itv.next();
			voisins[v.getVertex() - 1] = g.getEdge(v.getVertex()).toArray(voisins[v.getVertex() - 1]);
			// Au départ, aucun sommet n'est coloré => DSATUR(v) = DEG(v)
			v.setTag(v.getDegree());
		}

		// 2 (on mettra une couleur au hasard histoire que le sommet soit
		// coloré)
		vertex.get(0).setColor(0);

		// Valeur de aux courante : numéro du sommet de deg max.
		int aux = vertex.get(0).getVertex() - 1;
		// On met le degré de saturation de chaque voisin de vertex.get(0) à 1
		// car on vient de le colorier.
		for (i = 0; i < voisins[aux].length; i++) {
			if (voisins[aux][i].getVertex()[0].getVertex() == aux + 1) {
				v = voisins[aux][i].getVertex()[1];
				// On indique pour le voisin v de vertex.get(0) qu'une couleur a
				// été ajouté.
				v.setTag(1);
				vColor[v.getVertex() - 1][0] = true;
			} else {
				v = voisins[aux][i].getVertex()[0];
				// On indique pour le voisin v de vertex.get(0) qu'une couleur a
				// été ajouté.
				v.setTag(1);
				vColor[v.getVertex() - 1][0] = true;
			}
		}

		vertex.remove(0);

		while (vertex.size() > 0) {
			// Tri des sommets en fonction du degré de saturation dans l'ordre
			// décroissant (voir VertexDsaturComparator).
			Collections.sort(vertex, Tools.VERTEXDSATURCOMPARATOR);

			// x = sommet de degré de saturation le + élevé.
			x = vertex.get(0);
			aux = x.getVertex() - 1;

			// Si le degré de saturation (tag) du sommet est égal à son degré,
			// alors aucun de ces voisins n'est coloré, du coup on le colore
			// avec la plus petite couleur (Black => rgb = 0 = minColor).
			if (x.getDegree() == x.getTag())
				x.setColor(0);
			else {// Sinon il faut parcourir l'ensemble des voisins

				mincolor = 0;

				// On recherche la couleur la plus petite.
				while (mincolor < Tools.colorMax && vColor[aux][mincolor]) {
					// vColor[aux][mincolor] == true alors un voisin de aux est
					// coloré par la couleur mincolor.
					mincolor += 1;
				}

				if (mincolor > Tools.colorMax) {
					throw new NotEnoughColorsException();
				}
				// Mise à jour du tableau des couleurs pour les voisins du
				// sommet x
				for (int j = 0; j < voisins[aux].length; j++) {
					if (voisins[aux][j].getVertex()[0] == x) {
						v = voisins[aux][j].getVertex()[1];
						// On indique pour le voisin v non coloré de x, qu'une
						// couleur a été ajouté si
						// vColor[v.getVertex()][mincolor] == false.
						if (!vColor[v.getVertex() - 1][mincolor] && v.getColor() == -1) {
							// On augmente le degré de saturation pour le
							// prochain tour.

							v.setTag(v.getTag() + 1);
							vColor[v.getVertex() - 1][mincolor] = true;
						}// Si le voisin n'est pas coloré et que
							// vColor[v.getVertex()][mincolor] == false ou si le
							// voisin est coloré alors on ne fait rien.
					} else {
						v = voisins[aux][j].getVertex()[0];

						// On indique pour le voisin v non coloré de x, qu'une
						// couleur a été ajouté si
						// vColor[v.getVertex()][mincolor] == false.
						if (!vColor[v.getVertex() - 1][mincolor] && v.getColor() == -1) {

							// On augmente le degré de saturation pour le
							// prochain tour.
							v.setTag(v.getTag() + 1);
							vColor[v.getVertex() - 1][mincolor] = true;
						}// Si le voisin n'est pas coloré et que
							// vColor[v.getVertex()][mincolor] == false ou si le
							// voisin est coloré alors on ne fait rien. }
					}
				}
				x.setColor(mincolor);
			}
			vertex.remove(0);
		}
	}

	public void runWelshPowell(Graph g) throws NotEnoughColorsException {
		List<Vertex> vertex = g.listVertex();
		int mincolor = 0;
		Iterator<Vertex> itv = vertex.iterator();
		Vertex v = null;
		Edge[][] voisins = new Edge[vertex.size()][1];

		boolean[][] vColor = new boolean[vertex.size()][Tools.colorMax];
		int aux = 0;

		while (itv.hasNext()) {
			v = itv.next();
			voisins[v.getVertex() - 1] = g.getEdge(v.getVertex()).toArray(voisins[v.getVertex() - 1]);
		}
		// on trie selon l'ordre des degrés des sommets
		Collections.sort(vertex, Tools.VERTEXDEGCOMPARATOR);
		
		
		for (int i = 0; i < vertex.size(); i++) {
			Vertex x = vertex.get(i);
			aux = x.getVertex() - 1;
			mincolor = 0;

			// on récupere la couleur la moins utilisée par les voisins
			while (mincolor < Tools.colorMax && vColor[aux][mincolor]) {
				mincolor += 1;
			}

			if (mincolor > Tools.colorMax) {
				throw new NotEnoughColorsException();
			}
			
			// on met a jour le tableau des voisins
			for (int j = 0; j < voisins[aux].length; j++) {
				if (voisins[aux][j].getVertex()[0] == x) {
					v = voisins[aux][j].getVertex()[1];
					if (!vColor[v.getVertex() - 1][mincolor]) {	
						vColor[v.getVertex() - 1][mincolor] = true;
					}
				} else {
					v = voisins[aux][j].getVertex()[0];
					if (!vColor[v.getVertex() - 1][mincolor]) {
						vColor[v.getVertex() - 1][mincolor] = true;
					}
				}
			}
			
			x.setColor(mincolor);
		}
	}

	public static void main(String[] args) {

		Coloration col = new Coloration();
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.println("Saisir le nombre de sommet: ");
		int nbSommets = 0;
		try{
			nbSommets = Integer.parseInt(sc.nextLine());
		}catch(Exception e){
			System.err.println("Veuillez saisir un entier.");
			System.exit(-1);
		}
		
		System.out.println("Saisir la probabilité (entre 0 et 1) de produire des aretes: ");
		float probabilite = 0;
		try{
			probabilite = Float.parseFloat(sc.nextLine());
			if( probabilite < 0 || probabilite > 1){
				System.err.println("Entre 0 et 1");
				System.exit(1);
			}
		}catch(Exception e){
			System.err.println("Veuillez saisir un flottant");
		}
		
		try {
			Graph g = new ErdosRenyi().generateErdosRenyiGraph(nbSommets, (float)probabilite);
			PrintWriter pw;

			List<Vertex> lv = g.listVertex();
			Iterator<Vertex> it = lv.iterator();
			Iterator<Edge> ite;
			Vertex v = null;
			long start;

			Edge edge;

			// Construction du graphe d'origine.
			pw = new PrintWriter("graph.txt");
			pw.print("graph G {\n");

			it = lv.iterator();
			while (it.hasNext()) {
				pw.print(it.next().getVertex() + ";\n");
			}

			ite = g.listEdges().iterator();
			while (ite.hasNext()) {
				edge = ite.next();
				pw.print(edge.getVertex()[0].getVertex() + " -- " + edge.getVertex()[1].getVertex() + " [label=\""
						+ edge.getValue() + "\"];\n");
			}

			pw.print("}");
			pw.close();

			/***************************************************************************************
												Algorithme naif
			****************************************************************************************/
			System.out.println("********************************************************************");
			System.out.println("			  Algorithme naif");
			System.out.println("********************************************************************");
			// Algo naïf
			pw = new PrintWriter("graphNaif.txt");
			pw.print("graph G {\n");

			System.out.println("Start Naif");
			start = System.currentTimeMillis();
			col.runNaif(g);
			System.out.println("Temps d'exéc pour l'algo Naïf: " + ((System.currentTimeMillis() - start)) + "ms.");
			System.out.println("Nombre de couleurs utilisées " + g.getNbColors());

			it = lv.iterator();
			while (it.hasNext()) {
				v = it.next();
				pw.print(v.getVertex() + " [color=\"" + Tools.COLORS[v.getColor()] + "\"];\n");
			}

			ite = g.listEdges().iterator();
			while (ite.hasNext()) {
				edge = ite.next();
				pw.print(edge.getVertex()[0].getVertex() + " -- " + edge.getVertex()[1].getVertex() + " [label=\""
						+ edge.getValue() + "\"];\n");
			}

			pw.print("}");
			pw.close();

			// On remet les attributs color et tag à leurs valeurs initiales
			it = g.listVertex().iterator();
			while (it.hasNext()) {
				v = it.next();
				v.setTag(0);
				v.setColor(-1);
			}


			/***************************************************************************************
													DSATUR
			****************************************************************************************/
			System.out.println("********************************************************************");
			System.out.println("			  Algorithme DSATUR");
			System.out.println("********************************************************************");
			// Algo DSATUR
			pw = new PrintWriter("graphDSATUR.txt");
			pw.print("graph G {\n");

			System.out.println("Start DSATUR");
			start = System.currentTimeMillis();
			col.runDSATUR(g);
			System.out.println("Temps d'exéc pour l'algo DSATUR: " + ((System.currentTimeMillis() - start)) + "ms.");
			System.out.println("Nombre de couleurs utilisées " + g.getNbColors());
			it = lv.iterator();
			while (it.hasNext()) {
				v = it.next();
				pw.print(v.getVertex() + " [color=\"" + Tools.COLORS[v.getColor()] + "\"];\n");
			}

			ite = g.listEdges().iterator();
			while (ite.hasNext()) {
				edge = ite.next();
				pw.print(edge.getVertex()[0].getVertex() + " -- " + edge.getVertex()[1].getVertex() + " [label=\""
						+ edge.getValue() + "\"];\n");
			}

			pw.print("}");
			pw.close();

			
			/***************************************************************************************
												Welsh Powell
			 ****************************************************************************************/
			System.out.println("********************************************************************");
			System.out.println("			  Algorithme WelshPowell");
			System.out.println("********************************************************************");
			// Algo Welsh Powell
			pw = new PrintWriter("graphWelshPowell.txt");
			pw.print("graph G {\n");

			System.out.println("Start WelshPowell");
			start = System.currentTimeMillis();
			col.runWelshPowell(g);
			System.out.println("Temps d'exéc pour l'algo WelshPowell: " + ((System.currentTimeMillis() - start)) + "ms.");
			System.out.println("Nombre de couleurs utilisées " + g.getNbColors());

			it = lv.iterator();
			while (it.hasNext()) {
				v = it.next();
				pw.print(v.getVertex() + " [color=\"" + Tools.COLORS[v.getColor()] + "\"];\n");
			}

			ite = g.listEdges().iterator();
			while (ite.hasNext()) {
				edge = ite.next();
				pw.print(edge.getVertex()[0].getVertex() + " -- " + edge.getVertex()[1].getVertex() + " [label=\""
						+ edge.getValue() + "\"];\n");
			}

			pw.print("}");
			pw.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NotEnoughColorsException e) {
			e.printStackTrace();
		}
	}

}
