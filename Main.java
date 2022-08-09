class Main {
  public static void main(String[] args) {
    Graph Graph = new Graph(4);
    Graph.addEdge(0, 1, 3);
    Graph.addEdge(0, 3, 4);
    Graph.addEdge(1, 0, 3);
    Graph.addEdge(3, 0, 4);
    Graph.addEdge(3, 4, 2);
    System.out.println(Graph);
    System.out.println(Graph.degree(3));
  }
}