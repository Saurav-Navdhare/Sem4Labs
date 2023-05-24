import java.util.*;

class Edge implements Comparable<Edge> {
    int u, v, w;

    Edge(int u, int v, int w) {
        this.u = u;
        this.v = v;
        this.w = w;
    }

    public int compareTo(Edge other) {
        return this.w - other.w;
    }
}

class UnionFind {
    int[] parent;
    int[] rank;
    UnionFind(int n) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    void unionSet(int x, int y) {
        int xRoot = find(x);
        int yRoot = find(y);
        if (xRoot == yRoot) {
            return;
        }
        if (rank[xRoot] < rank[yRoot]) {
            parent[xRoot] = yRoot;
        } else if (rank[xRoot] > rank[yRoot]) {
            parent[yRoot] = xRoot;
        } else {
            parent[yRoot] = xRoot;
            rank[xRoot]++;
        }
    }
}

public class Kruskal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of vertices: ");
        int vertices = sc.nextInt();
        System.out.print("Enter number of edges: ");
        int edge = sc.nextInt();
        Edge[] edges = new Edge[edge];
        System.out.println("Enter all source destination information, 0 being the first vertex : ");
        for (int i = 0; i < edge; i++) {
            System.out.print("Enter source : ");
            int u = sc.nextInt();
            System.out.print("Enter Destination: ");
            int v = sc.nextInt();
            System.out.print("Enter Weight: ");
            int w = sc.nextInt();
            System.out.println(i+1 +". Edge with u = "+u+", v = "+v+", w = "+w+ " is created...");
            edges[i] = new Edge(u, v, w);
        }
        sc.close();
        UnionFind uf = new UnionFind(vertices);
        //sorting is done based on weight.
        Arrays.sort(edges);
        int weight = 0;
        for (int i = 0; i < edge; i++) {
            int u = edges[i].u;
            int v = edges[i].v;
            int w = edges[i].w;
            if (uf.find(u) != uf.find(v)) {
                uf.unionSet(u, v);
                System.out.printf("(%d, %d) -> %d\n", u, v, w);
                weight += w;
            }
        }
        System.out.printf("Weight of minimum spanning tree: %d\n", weight);
    }
}
