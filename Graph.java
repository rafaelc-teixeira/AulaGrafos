public class Graph {

  private int countNodes;
  private int countEdges;
  private int[][] adjMatrix;

  public Graph(int CountNodes) {
    this.countNodes = CountNodes;
    this.adjMatrix = new int[CountNodes][CountNodes];
  }

  public int getCountNodes() {
    return this.countNodes;
  }

  public int getCountEdges() {
    return this.countNodes;
  }

  public int[][] getAdjMatrix() {
    return this.adjMatrix;
  }

  public String toString() {
    String str = "";

    for (int i = 0; i < this.adjMatrix.length; i++) {
      for (int j = 0; j < this.adjMatrix[i].length; j++) {
        str += this.adjMatrix[i][j] + "\t";
      }
      str += "\n";
    }
    return str;
  }

  public void addEdge(int source, int sink, int weight) {
    if (source < 0 || source > this.countNodes - 1
        || sink < 0 || sink > this.countNodes - 1 || weight <= 0) {
      System.err.println("Invalid input");
      return;
    }

    this.adjMatrix[source][sink] = weight;
    this.countEdges++;
  }

  public int degree(int pos) {
    if (pos < 0 || pos > this.countNodes - 1) {
      System.err.println("Invalid input");
    }
    int count = 0;
    for (int i = 0; i < this.adjMatrix[pos].length; i++) {
      if (this.adjMatrix[pos][i] != 0) {
        count++;
      }
    }
    return count;
  }

}