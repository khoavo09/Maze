/**
 * Edge.java: Represent an Edge in a maze
 */
package maze;

public class Edge {
	Vertex forward;
	Vertex backWard;
	
	public Edge(Vertex forward, Vertex backward){
		this.forward = forward;
		this.backWard = backward;
	}
	
}
