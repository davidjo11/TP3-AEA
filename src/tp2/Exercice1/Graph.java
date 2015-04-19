package tp2.Exercice1;

import java.util.Iterator;
import java.util.List;

import tp2.Exercice1.exception.VertexAlreadyExistException;
import tp2.Exercice1.exception.VertexNotFoundException;


public interface Graph {
     public void addVertex(Vertex v);
     
     public void addVertexNumber(int i) throws VertexAlreadyExistException;
     
     public void addEdge(Vertex v1, Vertex v2, Float value) throws VertexNotFoundException;
     
     public void addEdge(int i, int j, Float value) throws VertexNotFoundException;
     
     public Vertex getVertex(int i) throws VertexNotFoundException;
     
     public Iterator<Edge> getSortedEdgeIterator();
     
     public List<Edge> listEdges();
     
     public boolean[][] listEdges(int vertex);
     
     public List<Vertex> listVertex();
     
     public List<Integer> listVertexInt();

     public List<Edge> getEdge(int v);
     
     public Edge getEdge(int v1, int v2);
     
     public List<Vertex> getMarkedVertex();
     
     public boolean allMarked();
     
     public void unmarkAll();
     
     public int getNbColors();
}
