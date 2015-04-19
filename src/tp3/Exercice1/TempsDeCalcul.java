package tp3.Exercice1;

import java.util.Iterator;
import java.util.Scanner;

import tp2.Exercice1.Graph;
import tp2.Exercice1.Vertex;
import tp2.Exercice2.ErdosRenyi;
import tp3.Exercice1.exceptions.NotEnoughColorsException;

public class TempsDeCalcul {

	public static void main(String[] args) {

		Coloration col = new Coloration();
		float pasP = (float) 0.2;
		int pasN = 50;
		int compteur = 0;
		int nbCouleursNaif = 0;
		int nbCouleursDSATUR = 0;
		int nbCouleursWelshPowell = 0;
		
		long tempsExecNaif = 0;
		long tempsExecDSATUR = 0;
		long tempsExecWelshPowell = 0;
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Saisir le nombre de sommet");
		int nbSommets = 0;
		try{
			nbSommets = Integer.parseInt(sc.nextLine());
		}catch(Exception e){
			System.err.println("Veuillez saisir un entier");
			System.exit(-1);
		}
		
		System.out.println("Saisir la probabilité (entre 0 et 1) de produire des aretes");
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
			while (compteur < 50) {
				Graph g = new ErdosRenyi().generateErdosRenyiGraph(nbSommets, (float)probabilite);

				long start;

				/***************************************************************************************
				 * Algorithme naif
				 ****************************************************************************************/
				// Algo naïf
				start = System.currentTimeMillis();
				col.runNaif(g);
				tempsExecNaif += System.currentTimeMillis() - start;
				nbCouleursNaif += g.getNbColors();
				
				/***************************************************************************************
				 * DSATUR
				 ****************************************************************************************/
				// Algo DSATUR
				Iterator<Vertex> it = g.listVertex().iterator();
				while (it.hasNext()) {
					Vertex v = it.next();
					v.setTag(0);
					v.setColor(-1);
				}
				start = System.currentTimeMillis();
				col.runDSATUR(g);
				tempsExecDSATUR += System.currentTimeMillis() - start;
				nbCouleursDSATUR += g.getNbColors();
				/***************************************************************************************
				 * Welsh Powell
				 ****************************************************************************************/
				// Algo Welsh Powell
				start = System.currentTimeMillis();
				col.runWelshPowell(g);
				tempsExecWelshPowell += System.currentTimeMillis() - start;
				nbCouleursWelshPowell += g.getNbColors();

				compteur++;
			}
			
			System.out.println("Algorithme naif");
			
			System.out.println("Temps d'execution moyen " + tempsExecNaif/50 + " ms");
			System.out.println("Nombre de couleurs en moyenne " + nbCouleursNaif/50);
			System.out.println("");
			
			
			System.out.println("Algorithme DSATUR");
			System.out.println("Temps d'execution moyen " + tempsExecDSATUR/50 + " ms");
			System.out.println("Nombre de couleurs en moyenne " + nbCouleursDSATUR/50);
			System.out.println("");
			
			System.out.println("Algorithme WelshPowell");
			System.out.println("Temps d'execution moyen " + tempsExecWelshPowell/50 + " ms");
			System.out.println("Nombre de couleurs en moyenne " + nbCouleursWelshPowell/50);

		} catch (NotEnoughColorsException e) {
			e.printStackTrace();
		}
		

	}
}
