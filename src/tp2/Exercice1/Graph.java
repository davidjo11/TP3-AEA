package tp2.Exercice1;

import java.util.List;

import tp2.Exercice1.exception.VertexAlreadyExistException;
import tp2.Exercice1.exception.VertexNotFoundException;


public interface Graph {

	/**
	 * Permet d'ajouter le sommet (vertex) numéro i au graphe.
	 * @param i un sommet
	 * @throws VertexAlreadyExistException
	 */
	public void addVertexNumber(int i) throws VertexAlreadyExistException;

	/**
	 * Ajoute l'arete (Edge) liant les sommets v1 et v2 en y associant le poids value. 
	 * @param v1 un sommet
	 * @param v2 un sommet
	 * @param value le poids de l'arete
	 * @throws VertexNotFoundException Déclenchée si l'un des sommets n'existe pas.
	 */
	public void addEdge(Vertex v1, Vertex v2, Float value) throws VertexNotFoundException;

	/**
	 * Ajoute l'arete (Edge) liant les sommets i et j en y associant le poids value.
	 * @param i un sommet
	 * @param j un sommet
	 * @param value le poids de l'arete
	 * @throws VertexNotFoundException Declenchee si l'un des sommets n'existe pas.
	 */
	public void addEdge(int i, int j, Float value) throws VertexNotFoundException;

	/**
	 * Retourne le sommet (vertex) de numero i si il est présent dans le graphe.
	 * @param i le numero du sommet
	 * @return le sommet de numro i present dans le graphe
	 * @throws VertexNotFoundException déclenchée si le sommet de numéro i n'est pas dans le graphe.
	 */
	public Vertex getVertex(int i) throws VertexNotFoundException;

	/**
	 * Retourne sous la forme d'une liste l'ensemble des aretes.
	 * @return l'ensemble des aretes.
	 */
	public List<Edge> listEdges();

	/**
	 * retourne la liste des sommets du graphe.
	 * @return la liste des sommets
	 */
	public List<Vertex> listVertex();

	/**
	 * Retourne la liste des numeros des sommets
	 * @return la liste des numeros des sommets
	 */
	public List<Integer> listVertexInt();

	/**
	 * Retourne la liste des aretes liant le sommet de numero v à un autre sommet
	 * @param v le sommet de numero v
	 * @return la liste des aretes comportant le sommet de numero v
	 */
	public List<Edge> getEdge(int v);

	/**
	 * Retourne l'arete liant les sommets v1 et v2 si elle existe, null sinon.
	 * @param v1
	 * @param v2
	 * @return l'arete liant les sommets v1 et v2, null sinon
	 */
	public Edge getEdge(int v1, int v2);

	/**
	 * Retourne l'ensemble des sommets marques.
	 * @return la liste des sommets marques
	 */
	public List<Vertex> getMarkedVertex();

	/**
	 * Retourne vrai si tous les sommets du graphe sont marques, false sinon.
	 * @return vrai si tous les sommets sont marques false sinon
	 */
	public boolean allMarked();

	/**
	 * Enleve le marquage sur tout les sommets.
	 */
	public void unmarkAll();

	/**
	 * Retourne le nombre de couleurs differentes utilisées par les sommets du graphe.
	 * @return le nombre de couleurs differentes.
	 */
	public int getNbColors();
}
