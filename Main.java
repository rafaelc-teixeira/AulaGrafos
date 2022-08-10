class Main {
  public static void main(String[] args) {
    Graph Graph = new Graph(4);
    Graph.addEdge(0, 1, 3);
    Graph.addEdge(0, 3, 4);
    Graph.addEdge(1, 0, 3);
    Graph.addEdge(3, 0, 4);
    Graph.addEdge(3, 4, 2);
    Graph.addEdge(2, 0, 2);
    Graph.addEdge(2, 1, 2);
    Graph.addEdge(2, 3, 2);
    System.out.println(Graph);
    System.out.println("Degree of a specific node: " + Graph.degree(3));
    System.out.println("Highest degree: " + Graph.highestDegree());
    System.out.println("Lowest degree: " + Graph.lowestDegree());
    System.out.println(Graph.complement());
  }
}