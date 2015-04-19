package tp2.Exercice1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import tools.Tools;
import tp2.Exercice1.exception.VertexAlreadyExistException;
import tp2.Exercice1.exception.VertexNotFoundException;

public class ValuedGraph implements Graph {

	/**
	 * Liste des sommets du graphes
	 */
	private List<Vertex> v;

	/**
	 * Liste des aretes du graphes
	 */
	private List<Edge> e;
	
	/**
	 * constructeur qui initialise les listes des sommets et des aretes
	 */
	public ValuedGraph() {
		this.v = new ArrayList<Vertex>();
		this.e = new ArrayList<Edge>();
	}

	/**
	 * constructeur qui initialise la liste des sommets avec une liste de taille n
	 * et initialise la liste des aretes
	 * @param n
	 */
	public ValuedGraph(int n){
		this.v = new ArrayList<Vertex>(n);
		this.e = new ArrayList<Edge>();
	}


	/**
	 * Permet d'ajouter un sommet au graphe à partir de son numéro 
	 */
	public void addVertexNumber(int i) throws VertexAlreadyExistException {
		Iterator<Vertex> it = this.v.iterator();
		while(it.hasNext()){
			if(it.next().getVertex() == i){
				throw new VertexAlreadyExistException();
			}
		}
		this.v.add(new Vertex(i));
	}

	/**
	 * permet d'ajouter une arete entre 2 sommets.
	 * On incrémente par la meme occasion le degré des sommets concernés
	 */
	public void addEdge(Vertex v1, Vertex v2, Float value) throws VertexNotFoundException {
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


	/**
	 * permet d'ajouter une arete entre 2 sommets a partir de leurs numéros
	 * On incrémente par la meme occasion le degré des sommets concernés
	 */
	public void addEdge(int i, int j, Float value) throws VertexNotFoundException {
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

	/**
	 * Récupere les numéros de tous les sommets du graphe 
	 */
	public List<Integer> listVertexInt(){
		List<Integer> res = new ArrayList<Integer>();
		Iterator<Vertex> it = this.v.iterator();
		while(it.hasNext())
			res.add(it.next().getVertex());
		return res;
	}

	
	public Vertex getVertex(int i) throws VertexNotFoundException {
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

	/**
	 * renvoi la liste des aretes du graphe
	 * @return
	 */
	public List<Edge> listEdges() {
		return this.e;
	}

	/**
	 * Renvoi la liste des sommets du graphe
	 * @return
	 */
	public List<Vertex> listVertex() {
		return this.v;
	}

	/**
	 * Retourne true si tout les sommets sont marqués, false sinon.
	 */
	public boolean allMarked() {
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
	 * Retourne l'ensemble des sommets marqués.
	 */
	public List<Vertex> getMarkedVertex() {
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
	public void unmarkAll() {
		Iterator<Vertex> it = this.v.iterator();
		while(it.hasNext())
			it.next().unmark();
	}


	public int getNbColors() {
		Set<String> list = new HashSet<String>();
		Iterator<Vertex> it = this.v.iterator();
		while(it.hasNext()){
			list.add(Tools.COLORS[it.next().getColor()]);
		}
		return list.size();
	}

}
