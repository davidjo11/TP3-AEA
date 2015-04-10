package tp2.Exercice1;

public class Edge {

	private Vertex s1, s2;
	
	private Float value;
	
	public Edge(int i, int j){
		this.s1 = new Vertex(i);
		this.s2 = new Vertex(j);
	}
	
	public Edge(int i, int j, Float value){
		this.s1 = new Vertex(i);
		this.s2 = new Vertex(j);
		this.value = value;
	}
	
	public Edge(Vertex v1, Vertex v2){
		this.s1 = v1;
		this.s2 = v2;
	}
	
	public Edge(Vertex v1, Vertex v2, Float value){
		this.s1 = v1;
		this.s2 = v2;
		this.value = value;
	}
	
	public Vertex[] getVertex(){
		return new Vertex[]{this.s1, this.s2};
	}
	
	public Float getValue(){
		return this.value;
	}
}
