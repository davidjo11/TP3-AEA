package tp2.Exercice2;

import tp2.Exercice1.Graph;


public interface RandomGraphGenerator {
     Graph generateErdosRenyiGraph(int n, float p) throws IllegalArgumentException;
}	
