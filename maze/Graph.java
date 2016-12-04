/**
 * Graph.java: Represent a graph with adjacency list representation
 */

package maze;
import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class Graph {
	
	static Vertex nil = new Vertex("leuleu"); //dummy node for null value
	private ArrayList<Vertex>[] vertexList; // adjacency list of vertex
	private int vertexNum; 
	private int maxVertex; 
	private String path; 
	private int time;
    private boolean found; 
    private int timeVisit; //Tracking when order to visit rooms

	//constructor
	public Graph(int n){
		vertexList = new ArrayList[n]; 
		for(int i = 0; i < n; i++){
			vertexList[i] = new ArrayList<Vertex>(); //initialize it with n arraylist of vertex
		}
		maxVertex = n;
		vertexNum = 0;
		path = "";
        found = false;
                
	}
	
	
	// add a vertex to the graph
	public void add(Vertex a){
		a.setIndex(vertexNum);  
		vertexList[vertexNum].add(a); 
		vertexNum++;  
	}
	
	//add an edge between 2 vevtex
	public void addEgde(Edge edge){
		for(int i = 0; i < vertexNum; i++){
			ArrayList<Vertex> currentList = vertexList[i];
			
			if(currentList.get(0) == edge.forward){ 
				currentList.add(edge.backWard);
			}
			
			if(currentList.get(0) == edge.backWard){ 
				currentList.add(edge.forward);
			}
		}
	}
	
	//print the graph adjacency list
	public String printAdjacencyList(){
		String adj = "";
		for(int i = 0; i < vertexNum; i++){
			ArrayList<Vertex> currentList = vertexList[i];
			for(int j = 0; j < currentList.size(); j++){
				if(j != currentList.size()-1)
					adj += currentList.get(j)+"->";
				else
					adj += currentList.get(j)+"";
			}
			adj+="\n";
		}
		return adj;
	}
	
	//breath first search 
	public void bfs(Vertex s){
		for(int i = 0; i < vertexNum; i++){
			Vertex u = vertexList[i].get(0);
			u.color = COLOR.WHITE;
			u.d = 0;
			u.parent = nil;
		}
                
		Queue<Vertex> q = new LinkedList<Vertex>();
		s.color = COLOR.GREY;
		s.d = 0;
		s.parent = nil;
		q.add(s);
		while(!q.isEmpty()){
			Vertex u = q.remove();
			ArrayList<Vertex> adjU = vertexList[u.index];
			for(int i = 0; i < adjU.size(); i++){
				Vertex v = adjU.get(i);
                                 
				if(v.color == COLOR.WHITE){
					v.color = COLOR.GREY;
					v.d = u.d + 1;
					v.parent = u;
					q.add(v);
				}
			}
			u.color = COLOR.BLACK;	
		}
	}
        

    //bfs search that stops when done visiting vertex k
	public void bfs_stop(Vertex s, Vertex k){
        for(int i = 0; i < vertexNum; i++){
			Vertex u = vertexList[i].get(0);
			u.color = COLOR.WHITE;
			u.d = 0;
			u.theEnd = false;
			u.visitTime = 0;
			u.parent = nil;
		    u.visitTime=0;
		    
        }
        k.theEnd = true;
        time=0;
        
		Queue<Vertex> q = new LinkedList<Vertex>();
		s.color = COLOR.GREY;
		s.d = 0;
		s.parent = nil;
		q.add(s);
		while(!q.isEmpty()){
			
			Vertex u = q.remove();
			
			ArrayList<Vertex> adjU = vertexList[u.index];
			for(int i = 0; i < adjU.size(); i++){
				Vertex v = adjU.get(i);
		                                      
				if(v.color == COLOR.WHITE){
					v.color = COLOR.GREY;
					v.d = u.d + 1;
					v.parent = u;
					q.add(v);
					
				}
			}
			u.color = COLOR.BLACK;
            u.visitTime=time;
            time++;
            // Stop when find the way to the end
            if(u.theEnd){
                return;
            }
		}
	}
        
	//dfs search that stops when it's done visiting vertex k
	public void dfs_stop(Vertex k){
	        found = false;
			for(int i = 0; i < vertexNum; i++){
				Vertex u = vertexList[i].get(0);
				u.color = COLOR.WHITE;
				u.d = 0;
	            u.visitTime=0;
				u.parent = nil;
			}
		
	        k.theEnd = true;
	                
			time = 0;
	        timeVisit = 0;
			for(int i = 0; i < vertexNum; i++){
				Vertex u = vertexList[i].get(0);
				if(u.color == COLOR.WHITE)
					dfsVisit_stop(u);
			}
    }
	
        
    //dfs_stop visit
	private void dfsVisit_stop(Vertex u){
        if(!found){
        	time++;
            u.visitTime = timeVisit;
            timeVisit++;
            u.d = time;
            
			u.color = COLOR.GREY;
			for(int i = 0; i < vertexList[u.index].size(); i++){
				Vertex v = vertexList[u.index].get(i);
                if (u.theEnd)
                {
                    found = true;
                    return;
                }
				if(v.color == COLOR.WHITE){
					v.parent = u;
					dfsVisit_stop(v);
				}
			}
			u.color = COLOR.BLACK;
			time++;
			u.f = time;
        }
	}
        
	
	//depth first search
	public void dfs(){
		for(int i = 0; i < vertexNum; i++){
			Vertex u = vertexList[i].get(0);
			u.color = COLOR.WHITE;
			u.d = 0;
			u.parent = nil;
		}
                
		time = 0;
		for(int i = 0; i < vertexNum; i++){
			Vertex u = vertexList[i].get(0);
			if(u.color == COLOR.WHITE)
				dfsVisit(u);
		}
	}
	
	//depth first search visit
	private void dfsVisit(Vertex u){
		time++;
		u.d = time;
		u.color = COLOR.GREY;
		for(int i = 0; i < vertexList[u.index].size(); i++){
			Vertex v = vertexList[u.index].get(i);
			if(v.color == COLOR.WHITE){
				v.parent = u;
				dfsVisit(v);
			}
		}
		u.color = COLOR.BLACK;
		time++;
		u.f = time;
	}
        
	//check if there is a path between 2 vertices
	public boolean hasPathBetween(Vertex a, Vertex b){
		this.bfs(a);
		while(b.parent!=nil){
			if(a == b.parent)
				return true;
			b = b.parent;
		}
		return false;
	}
	
	//called after dfs or bfs to get path to a vertex
	public String getPath(Vertex a){
        path ="";
		printPathRecursive(a);
		return path;
	}
        
	
	//recursively get the path
	private void printPathRecursive(Vertex a){
		if(a.parent!=nil)
			printPathRecursive(a.parent);
		path+=a.toString();
	}
     
	
	//check if two vertex is directly connected
    public boolean isConnected(Vertex A, Vertex B){
        for (int i=0; i < vertexList[A.index].size();i++){
            if(vertexList[A.index].get(i).equals(B))
                return true;
        }
        return false;
        
    }

}
