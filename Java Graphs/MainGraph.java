import java.util.ArrayList;

public class MainGraph {
    
    private ArrayList<Vertex> vertices;
    private boolean isWeighted;
    private boolean isDirected;

    public MainGraph(boolean inputIsWeighted, boolean inputIsDirected) {
        this.vertices = new ArrayList<Vertex>();
        this.isWeighted = inputIsWeighted;
        this.isDirected = inputIsDirected;
    }

    public Vertex addVertex(String data) {
        Vertex newVertex = new Vertex(data);
        this.vertices.add(newVertex);
        return newVertex;
    }

    public void addEdge(Vertex vertex1, Vertex vertex2, Integer weight) {
        if (!this.isWeighted) {
            weight = null;
        }
        vertex1.addEdge(vertex2, weight);
        if (!this.isDirected) {
            vertex2.addEdge(vertex1, weight);
        }
    }

    public void removeEdge(Vertex vertex1, Vertex vertex2) {
        vertex1.removeEdge(vertex2);
        if (!this.isDirected) {
            vertex2.removeEdge(vertex1);
        }
    }

    public void removeVertex(Vertex vertex) {
        this.vertices.remove(vertex);
    }

    public ArrayList<Vertex> getVertices() {
		return this.vertices;
	}

	public boolean isWeighted() {
		return this.isWeighted;
	}

	public boolean isDirected() {
		return this.isDirected;
	}

	public Vertex getVertexByValue(String value) {
		for(Vertex v: this.vertices) { 
			if (v.getData() == value) {
				return v;
			}
		}

		return null;
	}

    public Vertex getStartingVertex() {
		return this.getVertices().get(0);
	}

    public static void depthFirstTraversal(Vertex start, ArrayList<Vertex> visitedVertices) {
		System.out.println(start.getData());

		for (Edge e: start.getEdges()) {
			Vertex neighbor = e.getEnd();

			if (!visitedVertices.contains(neighbor)) {
				visitedVertices.add(neighbor);
				depthFirstTraversal(neighbor, visitedVertices);
			}
		}
	}

	public static void breadthFirstSearch(Vertex start, ArrayList<Vertex> visitedVertices) {
		Queue visitQueue = new Queue();
		visitQueue.enqueue(start);
		while (!visitQueue.isEmpty()) {
			Vertex current = visitQueue.dequeue();
			System.out.println(current.getData());

			for (Edge e: current.getEdges()) {
				Vertex neighbor = e.getEnd();
				if (!visitedVertices.contains(neighbor)) {
					visitedVertices.add(neighbor);
					visitQueue.enqueue(neighbor);
				}
			}
		}
	}

	public static void main(String[] args) {
        MainGraph busNetwork = new MainGraph(true, true);
        Vertex cliftonStation = busNetwork.addVertex("Clifton");
        Vertex capeMayStation = busNetwork.addVertex("Cape May");
        Vertex hobokenStation = busNetwork.addVertex("Hoboken");
        Vertex jerseyCityStation = busNetwork.addVertex("Jersey City");
        Vertex newarkStation = busNetwork.addVertex("Newark");
        Vertex patersonStation = busNetwork.addVertex("Paterson");

        busNetwork.addEdge(cliftonStation, capeMayStation, 1000);
        busNetwork.addEdge(cliftonStation, hobokenStation, 2000);
        busNetwork.addEdge(capeMayStation, jerseyCityStation, 3000);
        busNetwork.addEdge(hobokenStation, newarkStation, 1000);
        busNetwork.addEdge(jerseyCityStation, patersonStation, 2000);
        
		Vertex startingVertex = busNetwork.getStartingVertex();
		ArrayList<Vertex> visitedVertices1 = new ArrayList<Vertex>();
		ArrayList<Vertex> visitedVertices2 = new ArrayList<Vertex>();
		visitedVertices1.add(startingVertex);
		visitedVertices2.add(startingVertex);
		System.out.println("DFS:");
		depthFirstTraversal(startingVertex, visitedVertices1);
		System.out.println("BFS:");
		breadthFirstSearch(startingVertex, visitedVertices2);

		//create a cycle
		busNetwork.addEdge(patersonStation, cliftonStation, 1000);
	}
}
