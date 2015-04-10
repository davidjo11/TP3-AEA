package tp2.Exercice2;

import java.util.Random;

import tp2.Exercice1.Graph;
import tp2.Exercice1.ValuedGraph;
import tp2.Exercice1.exception.VertexNotFoundException;

public class ErdosRenyi implements RandomGraphGenerator {

	@Override
	public Graph generateErdosRenyiGraph(int n, float p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		Graph g = new ValuedGraph();
		
		for(int i=1; i<=n; i++){
			try{
				g.addVertexNumber(i);
			}
			catch(Exception e){}
		}
		
		Random rd = new Random();
		
		int max = (int)Math.pow(new Double(""+n), 4.0);
		
		for(int i=1;i<n; i++){
			for(int j=i+1; j<=n;j++){
				Float aux = rd.nextFloat();
				if(aux < p){
					try {
						g.addEdge(i, j, (float)rd.nextInt(max));
					} catch (VertexNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		
		return g;
	}

}
