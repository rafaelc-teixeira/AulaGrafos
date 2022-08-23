import java.util.ArrayList;

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

  public int highestDegree() {
    int highest = 0;

    for (int i = 0; i < this.adjMatrix.length; i++) {
      int degree = this.degree(i);
      if (highest < degree) {
        highest = degree;
      }
    }
    return highest;
  }

  public int lowestDegree() {
    int lowest = this.adjMatrix.length;

    for (int i = 0; i < this.adjMatrix.length; i++) {
      int degree = this.degree(i);
      if (lowest > degree) {
        lowest = degree;
      }
    }
    return lowest;
  }

  public Graph complement() {
    Graph newGraph = new Graph(this.countNodes);

    for (int i = 0; i < this.adjMatrix.length; i++) {
      for (int j = 0; j < this.adjMatrix.length; j++) {
        if (this.adjMatrix[i][j] == 0 && i != j) {
          newGraph.addEdge(i, j, 1);
        }
      }
    }
    return newGraph;
  }

  public float density() {
    return (float) this.countEdges / (this.countNodes * (this.countNodes - 1));
  }

  public boolean subGraph(Graph g2) {
    if (g2.countNodes > this.countNodes || g2.countEdges > this.countEdges)
      return false;
    for (int i = 0; i < g2.adjMatrix.length; ++i) {
      for (int j = 0; j < g2.adjMatrix[i].length; ++j) {
        if (g2.adjMatrix[i][j] != 0 && this.adjMatrix[i][j] == 0)
          return false;
      }
    }
    return true;
  }

  public ArrayList<Integer> buscaLargura(int s) {
    int[] desc = new int[this.countNodes];
    ArrayList<Integer> Q = new ArrayList<>();
    ArrayList<Integer> R = new ArrayList<>();
    desc[s] = 1;
    Q.add(s);
    R.add(s);

    while (Q.size() > 0) {
      int u = Q.remove(0);
      for (int v = 0; v < this.adjMatrix[u].length; v++) {
        if (this.adjMatrix[u][v] != 0) {
          if (desc[v] == 0) {
            Q.add(v);
            R.add(v);
            desc[v] = 1;
          }
        }
      }
    }
    return R;
  }

  public void addUnorientedEdge(int u, int v, int w) {
    if (u < 0 || u > this.countNodes - 1
        || v < 0 || v > this.countNodes - 1 || w <= 0) {
      System.err.println("Invalid input: " + u + v + w);
      return;
    }
    this.adjMatrix[u][v] = w;
    this.adjMatrix[v][u] = w;
    this.countEdges += 2;
  }

  public boolean connected() {
    return this.buscaLargura(7).size() == this.countNodes;
  }

}