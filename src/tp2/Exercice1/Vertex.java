package tp2.Exercice1;

public class Vertex {

	private int summit;

	private boolean mark;
		
	private int tag = 0;
	
	public Vertex(int i){
		this.summit = i;
		this.mark = false;
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

}
