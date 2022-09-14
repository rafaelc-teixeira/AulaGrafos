import java.util.ArrayList;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

public class Graph {

  private int countNodes;
  private int countEdges;
  private int[][] adjMatrix;
  private final int MAX_VALUE = 999999999;

  public Graph(int CountNodes) {
    this.countNodes = CountNodes;
    this.adjMatrix = new int[CountNodes][CountNodes];
  }

  public Graph(String fileName) throws IOException {
    File file = new File(fileName);
    FileReader reader = new FileReader(file);
    BufferedReader bufferedReader = new BufferedReader(reader);

    // Read header
    String[] line = bufferedReader.readLine().split(" ");
    this.countNodes = (Integer.parseInt(line[0]));
    int fileLines = (Integer.parseInt(line[1]));

    // Create and fill adjMatrix with read edges
    this.adjMatrix = new int[this.countNodes][this.countNodes];
    for (int i = 0; i < fileLines; ++i) {
      String[] edgeInfo = bufferedReader.readLine().split(" ");
      int source = Integer.parseInt(edgeInfo[0]);
      int sink = Integer.parseInt(edgeInfo[1]);
      int weight = Integer.parseInt(edgeInfo[2]);
      addEdge(source, sink, weight);
    }
    bufferedReader.close();
    reader.close();
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

  public boolean nanOriented() {

    for (int i = 1; i < this.adjMatrix.length; i++) {
      for (int j = i + 1; j < this.adjMatrix.length; j++) {
        if (this.adjMatrix[i][j] != this.adjMatrix[j][i]) {
          return true;
        }
      }
    }
    return false;
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

  public ArrayList<Integer> buscaProfundidadeREC(int s) {
    int[] desc = new int[this.countNodes];
    ArrayList<Integer> R = new ArrayList<>();
    buscaProfundidadeRECAUX(s, desc, R);
    return R;
  }

  public void buscaProfundidadeRECAUX(int s, int[] desc, ArrayList<Integer> R) {
    desc[s] = 1;
    R.add(s);
    for (int v = 0; v < this.adjMatrix[s].length; ++v) {
      if (this.adjMatrix[s][v] != 0 && desc[v] == 0) {
        buscaProfundidadeRECAUX(v, desc, R);
      }
    }
  }

  public ArrayList<Integer> buscaProfundidade(int s) {
    int[] desc = new int[this.countNodes];
    ArrayList<Integer> S = new ArrayList<>();
    ArrayList<Integer> R = new ArrayList<>();
    desc[s] = 1;
    S.add(s);
    R.add(s);

    while (S.size() > 0) {
      int u = S.get(S.size() - 1);
      int exist = existeAdj(u, desc);
      if (exist != -1) {
        S.add(exist);
        R.add(exist);
        desc[exist] = 1;
      } else {
        S.remove(S.size() - 1);
      }
    }
    return R;
  }

  public void FloydWarshaw() {
    int[][] dist = new int[this.countNodes][this.countNodes];
    int[][] pred = new int[this.countNodes][this.countNodes];

    for (int i = 0; i < this.countNodes - 1; i++) {
      for (int j = 0; j < this.countNodes - 1; j++) {
        if (i == j) {
          dist[i][j] = 0;
        } else if (this.adjMatrix[i][j] != 0) {
          dist[i][j] = adjMatrix[i][j];
          pred[i][j] = i;
        } else {
          dist[i][j] = this.MAX_VALUE;
          pred[i][j] = i;
        }
      }
    }

        for (int j = 0; j < this.countNodes - 1; j++) {
          if (dist[i][j] > dist[i][k] + dist[k][j]) {
            dist[i][j] = dist[i][k] + dist[k][j];
            pred[i][j] = dist[k][j];
          }
        }
      }
    }

    System.out.println("Grafo dist: ");
    for (int k = 0; k < dist.length; k++) {
      for (int i = 0; i < dist.length; i++) {
        System.out.print(dist[k][i] + " ");
      }
      System.out.println();
    }

    System.out.println("Grafo pred: ");
    for (int k = 0; k < pred.length; k++) {
      for (int i = 0; i < pred.length; i++) {
        System.out.print(pred[k][i] + " ");
      }
      System.out.println();
    }

  }

  public int existeAdj(int pos, int[] desc) {
    for (int i = 0; i < this.adjMatrix[pos].length; i++) {
      if (this.adjMatrix[pos][i] != 0 && desc[i] == 0) {
        return i;
      }
    }
    return -1;
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