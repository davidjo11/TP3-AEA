package tp2.Exercice1;


public class Vertex {

	private int summit;

	private boolean mark;
	
	private int degree;
	
	private int color;
	
	private int tag;
	
	public Vertex(int i){
		this.summit = i;
		this.mark = false;
		this.tag = 0;
		this.degree = 0;
		this.color = -1;
	}
	
	public int getVertex() {
		return summit;
	}
	
	public void mark(){
		this.mark = true;
	}
	
	public void unmark(){
		this.mark = false;
	}
	
	public boolean isMarked(){
		return this.mark;
	}
	
	public void setTag(int tag){
		this.tag = tag;
	}
	
	public int getTag(){
		return this.tag;
	}

	public void setDegree(int deg){
		this.degree = deg;
	}
	
	public int getDegree(){
		return this.degree;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}
}
