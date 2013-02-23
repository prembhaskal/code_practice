package coursera.algo.week3;

import java.io.PrintWriter;
import java.util.*;

public class KargerMinCut {

	String[] graphFile;

	Graph graphMain;

	List<Integer> vertexListMain;

	public void solve(Scanner in, PrintWriter out, int vertices) {

		readFile(in, vertices);

		int trial = vertices * vertices;
		int max = 100;

		trial = Math.min(trial,max);

		int minCut = Integer.MAX_VALUE;

		for (int i=0;i<trial;i++) {
			setTheGraph(vertices);
			Graph graph = graphMain;
			List<Integer> vertexList = vertexListMain;

			int min = findKragerMinCut(graph, vertexList);

			minCut = Math.min(min, minCut);

//			System.out.println("done one contraction -- min value is " + minCut);
		}


		out.println();
		out.println("min cut value is  " + minCut);
	}

	private int findKragerMinCut(Graph graph, List<Integer> vertexList) {
		int minCount;
		Random randomSeed = new Random();

		Random randomGen = new Random(randomSeed.nextInt(100009));

		int totalVertices = vertexList.size();
		// do contraction for n-2 times, after that we should/would be remaining with only 2 nodes.
		for (int i=0;i<totalVertices-2;i++) {
			int size = vertexList.size();
			// choose random vertex vertexC
			int chooseVertexIndex = randomGen.nextInt(size);
			int chooseVertex = vertexList.get(chooseVertexIndex);

			// choose random vertex vertexR from the neighbours of vertexC;
			List<Integer> neighbours = graph.adjacencyList[chooseVertex];
			int removeVertexIndex = randomGen.nextInt(neighbours.size());
			int removeVertex = neighbours.get(removeVertexIndex);

			if (removeVertex == chooseVertex)
				throw new RuntimeException("chosen and to_be_removed index should never be the same");

			graph.contractTwoNodes(chooseVertex, removeVertex);

			// remove node VertexR from the graph.
			// but actually removed from the local list of nodes. (since graph is maintaining a array, very difficult to remove)
			// thus making sure we never read the remove vertex again.
			Iterator<Integer> iterator1 = vertexList.iterator();
			boolean nofind = false;
			while (iterator1.hasNext()) {
				if (iterator1.next()==removeVertex) {
					iterator1.remove();
					nofind = true;
				}
			}
			if (nofind==false){
				System.out.println("should not happen");
			}

			if (neighbours.isEmpty()) {
				System.out.println("should never happen");
			}

			// we are done one round of contraction, repeat for others

			if (i==totalVertices-3) {
//				System.out.println("break here");
			}
		}

		if (vertexList.size()!=2)
			throw new RuntimeException("improper contraction happened :(");

		// choose any of the two, both should have the same nodes.
		int vertex = vertexList.get(0);

		minCount = graph.adjacencyList[vertex].size();

		return minCount;
	}

	private void readFile(Scanner in, int vertices) {
		graphFile = new String[vertices];
		int i=0;
		while (in.hasNext()) {
			graphFile[i++] = in.nextLine().trim();
		}
	}

	private void setTheGraph(int vertices) {
		// 1 based index
		List<Integer>[] adjacencyList = new ArrayList[vertices+1];
		graphMain = new Graph(adjacencyList);
		vertexListMain = new ArrayList<Integer>();

		String str;

		for (int i=0;i<graphFile.length;i++){
			str = graphFile[i];
			String[] strings = str.split(" ");

			int vertex = Integer.parseInt(strings[0]);
			vertexListMain.add(vertex);
			for (int j=1;j<strings.length;j++) {
				int neighbour = Integer.parseInt(strings[j]);
				graphMain.addNeighbour(vertex, neighbour);
			}

		}

	}
}

// follows 1 based index, list at 0 will be null.
class Graph implements Cloneable {
	public List<Integer>[] adjacencyList;

	public Graph(List<Integer>[] adjacencyList) {
		this.adjacencyList = adjacencyList;
	}

	// 1 based index, so no subtraction
	public void addNeighbour(Integer node, Integer addNode) {
		List<Integer> neighbours = adjacencyList[node];
		if (neighbours == null) {
			neighbours = new ArrayList<Integer>();
			adjacencyList[node] = neighbours;
		}
		neighbours.add(addNode);
	}

	public void contractTwoNodes(Integer mainNode, Integer nodeToRemove) {
		replace(nodeToRemove, mainNode);
		mergeTwoNodes(mainNode, nodeToRemove);
		removeDuplicates(mainNode);
	}

	// replace existence of the removeVertex with the chooseVertex over the whole graph.
	private void replace( int removeVertex, int chooseVertex) {
		List<Integer> neighboursOfRemove = adjacencyList[removeVertex];

		for (int neighbour : neighboursOfRemove) {
			List<Integer> neighbours = adjacencyList[neighbour];
			for (int i=0;i<neighbours.size();i++) {
				if (neighbours.get(i)==removeVertex) {
					neighbours.set(i, chooseVertex);
				}
			}
		}
	}

	private void mergeTwoNodes(Integer destinationNode, Integer sourceNode) {
		List<Integer> neighbours = adjacencyList[destinationNode];
		List<Integer> removeNeighbours = adjacencyList[sourceNode];
		for (int removeNeighbour : removeNeighbours) {
			neighbours.add(removeNeighbour);
		}
	}

	private void removeDuplicates(Integer node) {
		List<Integer> neighbours = adjacencyList[node];
		// remove self loops from the graph, actually only need to be removed from the chooseVertex neighbours;
		Iterator<Integer> iterator2 = neighbours.iterator();
		while (iterator2.hasNext()) {
			int neighbour = iterator2.next();
			if (neighbour == node) {
				iterator2.remove();
			}
		}
	}



}
