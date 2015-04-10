package tp2.Exercice1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import tp2.Exercice1.exception.VertexAlreadyExistException;
import tp2.Exercice1.exception.VertexNotFoundException;

public class ValuedGraph implements Graph {

	private boolean oriented;

	private List<Vertex> v;

	private List<Edge> e;

	public ValuedGraph() {
		// TODO Auto-generated constructor stub
		this.v = new ArrayList<Vertex>();
		this.e = new ArrayList<Edge>();
	}

	public ValuedGraph(boolean oriented){
		this.v = new ArrayList<Vertex>();
		this.e = new ArrayList<Edge>();
		this.oriented = oriented;
	}

	public boolean isOriented() {
		return oriented;
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
		boolean t1= false, t2 = false;
		Iterator<Vertex> it = this.v.iterator();

		while(it.hasNext()){
			if(it.next().getVertex() == v1.getVertex())
				t1 = true;
			else if(it.next().getVertex() == v2.getVertex())
				t2 = true;
		}
		if(!t1 || !t2)
			throw new VertexNotFoundException();
		this.e.add(new Edge(v1,v2, value));

	}

	@Override
	public void addEdge(int i, int j, Float value) throws VertexNotFoundException {
		// TODO Auto-generated method stub
		boolean t1= false, t2 = false;
		Iterator<Vertex> it = this.v.iterator();

		while(it.hasNext()){
			Vertex v = it.next();
			if(v.getVertex() ==i)
				t1 = true;
			else if(v.getVertex() == j)
				t2 = true;
		}
		if(!t1 || !t2)
			throw new VertexNotFoundException();
		this.e.add(new Edge(new Vertex(i), new Vertex(j), value));
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
			if((edge.getVertex()[0].getVertex() == v1 && edge.getVertex()[1].getVertex() == v2) || edge.getVertex()[1].getVertex() == v1 && edge.getVertex()[0].getVertex() == v2){
				return edge;
			}
		}
		return null;
	}


	/**
	 * getEdge(int v) retourne l'ensemble des Edge ayant pour sommet v.
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

}
