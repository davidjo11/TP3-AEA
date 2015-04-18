package tp2.Exercice1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import tp2.Exercice1.exception.VertexAlreadyExistException;
import tp2.Exercice1.exception.VertexNotFoundException;

public class ValuedGraph implements Graph {

	private List<Vertex> v;

	private List<Edge> e;
	
	public ValuedGraph() {
		// TODO Auto-generated constructor stub
		this.v = new ArrayList<Vertex>();
		this.e = new ArrayList<Edge>();
	}

	public ValuedGraph(int n){
		this.v = new ArrayList<Vertex>(n);
		this.e = new ArrayList<Edge>();
	}

	public List<Vertex> getV() {
		return v;
	}

	public List<Edge> getE() {
		return e;
	}

	@Override
	public void addVertex(Vertex v) {
		// TODO Auto-generated method stub
		this.v.add(v);
	}

	@Override
	public void addVertexNumber(int i) throws VertexAlreadyExistException {
		// TODO Auto-generated method stub
		Iterator<Vertex> it = this.v.iterator();
		while(it.hasNext()){
			if(it.next().getVertex() == i){
				throw new VertexAlreadyExistException();
			}
		}
		this.v.add(new Vertex(i));
	}

	@Override
	public void addEdge(Vertex v1, Vertex v2, Float value) throws VertexNotFoundException {
		// TODO Auto-generated method stub
		Vertex t1= null, t2 = null, aux = null;
		Iterator<Vertex> it = this.v.iterator();

		while(it.hasNext()){
			aux = it.next();
			if(aux.getVertex() == v1.getVertex())
				t1 = aux;
			else if(aux.getVertex() == v2.getVertex())
				t2 = aux;
		}
		if(t1 == null || t2 == null)
			throw new VertexNotFoundException();
		//on augmente le degré de chaque sommet
		t1.setDegree(t1.getDegree()+1);
		t2.setDegree(t2.getDegree()+1);
		this.e.add(new Edge(t1,t2, value));
	}

	@Override
	public void addEdge(int i, int j, Float value) throws VertexNotFoundException {
		// TODO Auto-generated method stub
		Vertex t1= null, t2 = null, aux = null;
		Iterator<Vertex> it = this.v.iterator();

		while(it.hasNext()){
			aux = it.next();
			if(aux.getVertex() ==i)
				t1 = aux;
			else if(aux.getVertex() == j)
				t2 = aux;
		}
		if(t1 == null || t2 == null)
			throw new VertexNotFoundException();
		//on augmente le degré de chaque sommet
		t1.setDegree(t1.getDegree()+1);
		t2.setDegree(t2.getDegree()+1);
		this.e.add(new Edge(t1, t2, value));
	}

	@Override
	public List<Integer> listVertexInt(){

		List<Integer> res = new ArrayList<Integer>();

		Iterator<Vertex> it = this.v.iterator();

		while(it.hasNext())
			res.add(it.next().getVertex());

		return res;
	}

	@Override
	public Vertex getVertex(int i) throws VertexNotFoundException {
		// TODO Auto-generated method stub
		Iterator<Vertex> it = this.v.iterator();

		while(it.hasNext()){
			Vertex v = it.next();
			if(v.getVertex() == i)
				return v;
		}
		throw new VertexNotFoundException();
	}

	/**
	 * GetEdge retourne l'arete composee par les sommets dep et arr si elle existe, null sinon
	 * @param dep
	 * @param arr
	 * @return l'arete si elle existe, null sinon
	 */
	public Edge getEdge(int v1, int v2){
		Iterator<Edge> it = this.e.iterator();
		Edge edge;
		while(it.hasNext()){
			edge = it.next();
			if((edge.getVertex()[0].getVertex() == v1 && edge.getVertex()[1].getVertex() == v2)){
				return edge;
			}
			else if(edge.getVertex()[1].getVertex() == v1 && edge.getVertex()[0].getVertex() == v2)
				return edge;
		}
		return null;
	}


	/**
	 * getEdge(int v) retourne l'ensemble des edges ayant pour sommet v.
	 * @param vertex
	 * @return
	 */
	public List<Edge> getEdge(int vertex){
		Iterator<Edge> it = this.e.iterator();
		List<Edge> res  = new ArrayList<Edge>();
		Edge aux = null;
		while(it.hasNext()){
			aux = it.next();
			if(aux.getVertex()[0].getVertex() == vertex || aux.getVertex()[1].getVertex() == vertex)
				res.add(aux);
		}

		return res;
	}


	@Override
	public Iterator<Edge> getSortedEdgeIterator() {
		// TODO Auto-generated method stub

		return null;
	}

	@Override
	public List<Edge> listEdges() {
		// TODO Auto-generated method stub
		return this.e;
	}

	@Override
	public List<Vertex> listVertex() {
		// TODO Auto-generated method stub
		return this.v;
	}

	/**
	 * Retourne true si tout les sommets sont marqués, false sinon.
	 */
	@Override
	public boolean allMarked() {
		// TODO Auto-generated method stub
		Iterator<Vertex> it = this.v.iterator();
		Vertex v ;
		while(it.hasNext()){
			v = it.next();
			if(!v.isMarked())
				return false;
		}
		return true;
	}

	/**
	 * Retourne l'ensemble des sommets non marqués.
	 */
	@Override
	public List<Vertex> getMarkedVertex() {
		// TODO Auto-generated method stub
		List<Vertex> res = new ArrayList<Vertex>();
		Iterator<Vertex> it = this.v.iterator();
		Vertex v ;
		while(it.hasNext()){
			v = it.next();
			if(v.isMarked())
				res.add(v);
		}
		return res;
	}

	/**
	 * Supprime la marque sur chaque sommet que comporte le graphe.
	 */
	@Override
	public void unmarkAll() {
		// TODO Auto-generated method stub
		Iterator<Vertex> it = this.v.iterator();
		while(it.hasNext())
			it.next().unmark();
	}

	@Override
	public boolean[][] listEdges(int vertex) {
		// TODO Auto-generated method stub
		return null;
	}

}
