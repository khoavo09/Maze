/**
 * GraphJUnitTest.java: Tester for Graph.java
 */

package maze;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GraphJUnitTest {
	private Graph myGraph;
	private Vertex<Character> a;
	private Vertex<Character> b;
	private Vertex<Character> c;
	private Vertex<Character> d;
	private Vertex<Character> e;
	private Vertex<Character> f;
	private Vertex<Character> h;
	private Vertex<Character> i;
	private Vertex<Character> j;
	
	//add all vertices and some edges
	@Before public void setUp(){
		myGraph = new Graph(9);
		a = new Vertex<Character>('a');
		b = new Vertex<Character>('b');
		c = new Vertex<Character>('c');
		d = new Vertex<Character>('d');
		e = new Vertex<Character>('e');
		f = new Vertex<Character>('f');
		h = new Vertex<Character>('h');
		i = new Vertex<Character>('i');
		j = new Vertex<Character>('j');
		
		//add vertices to graph
		myGraph.add(a);
		myGraph.add(b);
		myGraph.add(c);
		myGraph.add(d);
		myGraph.add(e);
		myGraph.add(f);
		myGraph.add(h);
		myGraph.add(i);
		myGraph.add(j); 
				
		//add edge to graph
		myGraph.addEgde(new Edge(a,b));
		myGraph.addEgde(new Edge(b,c));
		myGraph.addEgde(new Edge(b,e));
		myGraph.addEgde(new Edge(c,f));
		myGraph.addEgde(new Edge(d,h));
		myGraph.addEgde(new Edge(e,i));
		myGraph.addEgde(new Edge(h,i));
		myGraph.addEgde(new Edge(j,i));
	}
	
	
	@Test public void printAdjancencyList(){
		String expected = "[a]->[b]\n"
						+ "[b]->[a]->[c]->[e]\n"
						+ "[c]->[b]->[f]\n"
						+ "[d]->[h]\n"
						+ "[e]->[b]->[i]\n"
						+ "[f]->[c]\n"
						+ "[h]->[d]->[i]\n"
						+ "[i]->[e]->[h]->[j]\n"
						+ "[j]->[i]\n";
		String result = myGraph.printAdjacencyList();
		assertEquals(expected, result);
	}
	
	@Test public void testBFS_1(){
		 myGraph.bfs(a);
		 String path = myGraph.getPath(j);
		 String expected = "[a][b][e][i][j]";
		 assertEquals(expected, path);
	}
	
	@Test public void testBFS_2(){
		 myGraph.bfs(a);
		 String path = myGraph.getPath(a);
		 String expected = "[a]";
		 assertEquals(expected, path);
	}
	
	@Test public void testDFS_1(){
		 myGraph.dfs();
		 String path = myGraph.getPath(j);
		 String expected = "[a][b][e][i][j]";
		 assertEquals(expected, path);
	}
	
	@Test public void testDFS_2(){
		myGraph.dfs();
		String path = myGraph.getPath(a);
		String expected = "[a]";
		assertEquals(expected, path);
	}
	
	@Test public void testBFS_stop(){
		myGraph.bfs_stop(a, c);
		String path = myGraph.getPath(c);
		String noPath = myGraph.getPath(d);
		String expectPath = "[a][b][c]";
		String expectNoPath = "[d]";
		assertEquals(expectPath, path);
		assertEquals(expectNoPath, noPath);
	}
	
	@Test public void testDFS_stop(){
		myGraph.dfs_stop(c);
		String path = myGraph.getPath(c);
		String noPath = myGraph.getPath(d);
		String expectPath = "[a][b][c]";
		String expectNoPath = "[d]";
		assertEquals(expectPath, path);
		assertEquals(expectNoPath, noPath);
	}
	
	
	
	
	
}
