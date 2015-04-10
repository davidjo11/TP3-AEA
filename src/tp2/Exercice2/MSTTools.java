package tp2.Exercice2;

import java.util.Iterator;

import tp2.Exercice1.Edge;
import tp2.Exercice1.Graph;


public interface MSTTools {
    public Iterator<Edge> runPrim(Graph g);
    public Iterator<Edge> runKruskal(Graph g);
}
