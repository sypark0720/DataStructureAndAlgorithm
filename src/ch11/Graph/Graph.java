package ch11.Graph;

//Graph ADT
public interface Graph {
	public void Init(int n); //n: the number of vertices
	public int n(); //return the number of vertices
	public int e(); //return the current number of edges
	public int first(int v); //return v's first neighbor
	public int next(int v, int w); //return v's next neighbor after w
	public void setEdge(int i, int j, int weight);
	public void delEdge(int i, int j);
	public boolean isEdge(int i, int j);
	public int weight(int i, int j); //return the weight of edge i, j
	public void setMark(int v, int val); //set the mark value for a vertex
	public int getMark(int v);
}
