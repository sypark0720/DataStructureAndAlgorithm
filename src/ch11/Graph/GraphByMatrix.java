package ch11.Graph;

import java.util.LinkedList;
import java.util.Queue;

public class GraphByMatrix implements Graph {

	/*
	 * Fields 
	 * */
	private int[][] matrix;
	private int numEdge;	//Number of Edges
	private int[] Mark;
	
	/*
	 * Constructors
	 */
	public GraphByMatrix(){}
	public GraphByMatrix(int n){
		Init(n);
	}
	

	/*
	 * Methods
	 */
	@Override
	public void Init(int n) {
		Mark = new int[n];
		matrix = new int[n][n];
		numEdge = 0;
	}

	@Override
	public int n() { //return number of vertex
		return Mark.length;
	}

	@Override
	public int e() { //return number of edge
		return numEdge;
	}
	
	@Override
	public int first(int v) {
		for(int i=0; i<Mark.length; i++)
			if(matrix[v][i]!=0)
				return i;
		return Mark.length; // No edge for this vertex
	}

	/*
	 * return v's next neighbor after w
	 */
	@Override
	public int next(int v, int w) { 
		for (int i=w+1; i<Mark.length; i++)
			if(matrix[v][i] !=0)
				return i;
		return Mark.length; //No next edge;
	}

	@Override
	public void setEdge(int i, int j, int weight) {
		assert weight !=0 :"Cannot set weight to 0";
		if(matrix[i][j]==0) numEdge++;
		matrix[i][j] = weight;
	}

	@Override
	public void delEdge(int i, int j) {
		if(matrix[i][j] !=0) numEdge--;
		matrix[i][j]=0;

	}

	@Override
	public boolean isEdge(int i, int j) {
		return matrix[i][j] != 0;
	}

	@Override
	public int weight(int i, int j) {
		return matrix[i][j];
	}

	@Override
	public void setMark(int v, int val) {
		Mark[v] = val;
	}

	@Override
	public int getMark(int v) {
		return Mark[v];
	}
	
	
	//Depth first search
	static void DFS(Graph G, int v){ //v: vertex
		G.setMark(v, 1); //1: visited, 0: unvisited
		System.out.println(v);
		for(int w = G.first(v); w<G.n(); w=G.next(v, w))
			if(G.getMark(w) == 0)
				DFS(G,w);
		}
	
	//Breadth first search(queue-based)
	static void BFS(Graph G, int start){
		Queue<Integer> Q = new LinkedList<Integer>();
		Q.offer(start);
		G.setMark(start, 1);
		while(Q.size()>0){
			int v= Q.poll();
			for(int w = G.first(v); w<G.n(); w=G.next(v, w))
				if(G.getMark(w)==0){
					G.setMark(w, 1);
					Q.offer(w);
				}
		}
	}

	//Topological Sort-recursive
	static void topsort(Graph G){
		for(int i=0;i<G.n(); i++){
			G.setMark(i, 0);
		}
		for(int i=0;i<G.n(); i++){
			if(G.getMark(i) == 0)
				tophelp(G,i);
		}
	}
	static void tophelp(Graph G, int v){
		G.setMark(v, 1);
		for (int w=G.first(v); w<G.n(); w=G.next(v,w))
			if(G.getMark(w)==0)
				tophelp(G,w);
		System.out.println(v);
	}
	
	//Topological Sort- Queue
	static void topsortQ(Graph G){
		Queue<Integer> Q = new LinkedList<Integer>();
		int[] Count = new int[G.n()];
		int v;
		//Initialize
		for(v=0; v<G.n(); v++) Count[v]=0;
		//Add to v's prereq count
		for(v=0; v<G.first(v); v++)
			for(int w= G.first(v); w<G.n(); w=G.next(v, w))
				Count[w]++;
		//Initialize Queue
		for(v=0; v<G.n(); v++)
			if(Count[v]==0)
				Q.offer(v);
		//Process the vertices
		while(Q.size()>0){
			v = Q.poll().intValue();
			System.out.println(v);
			for(int w = G.first(v); w<G.n(); w=G.next(v, w)){
				Count[w]--;
				if (Count[w]==0)
					Q.offer(w);
			}
		}
	}

}
