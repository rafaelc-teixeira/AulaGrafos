import java.io.IOException;

class Main {
  public static void main(String[] args) throws IOException {
    // Graph Graph = new Graph(4);
    // Graph.addEdge(0, 1, 3);
    // Graph.addEdge(0, 3, 4);
    // Graph.addEdge(1, 0, 3);
    // Graph.addEdge(3, 0, 4);
    // Graph.addEdge(3, 4, 2);
    // Graph.addEdge(2, 0, 2);
    // Graph.addEdge(2, 1, 2);
    // Graph.addEdge(2, 3, 2);
    // System.out.println(Graph);
    // System.out.println("Degree of a specific node: " + Graph.degree(3));
    // System.out.println("Highest degree: " + Graph.highestDegree());
    // System.out.println("Lowest degree: " + Graph.lowestDegree());
    // System.out.println(Graph.complement());

    Graph g1 = new Graph(10);
    g1.addUnorientedEdge(7, 5, 1);
    g1.addUnorientedEdge(7, 1, 1);
    g1.addUnorientedEdge(7, 2, 1);
    g1.addUnorientedEdge(1, 0, 1);
    g1.addUnorientedEdge(1, 4, 1);
    g1.addUnorientedEdge(2, 3, 1);
    g1.addUnorientedEdge(5, 6, 1);
    g1.addUnorientedEdge(6, 8, 1);

    System.out.println(g1.buscaLargura(7));
    System.out.println("Connectado: " + g1.connected());

    Graph g2 = new Graph("graph1.txt");
    System.out.println(g2);
    System.out.println(g2.buscaProfundidade(6));

  }
}