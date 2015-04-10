package tp2.Exercice1;

public class Vertex {

	private int summit;

	private boolean marqued;
	
	private int tag = 0;
	
	public Vertex(int i){
		this.summit = i;
		this.marqued = false;
	}
	
	public int getVertex() {
		return summit;
	}
	
	public void marqued(){
		this.marqued = true;
	}
	
	public boolean isMarqued(){
		return this.marqued;
	}
	
	public void setTag(int tag){
		this.tag = tag;
	}
	
	public int getTag(){
		return this.tag;
	}
}
