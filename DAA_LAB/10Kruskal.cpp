#include <bits/stdc++.h>
using namespace std;

struct Edge {
    int src, dest, weight;
};

class Graph {
    int V, E;
    Edge* edge;
private: 
    int e=0;
public:
    Graph(int V, int E) {
        this->V = V;
        this->E = E;
        edge = new Edge[E];
    }

    void addEdge(int u, int v, int w) {
        edge[e].src = u;
        edge[e].dest = v;
        edge[e].weight = w;
        e++;
    }

    // Find set of an element i
    int find(int parent[], int i) {
        if (parent[i] == -1) {
            return i;
        }
        return find(parent, parent[i]);
    }

    // Union of two sets
    void Union(int parent[], int x, int y) {
        parent[x] = y;
    }

    // Comparison function for sorting edges by weight
    static int compare(const void* a, const void* b) {
        Edge* a1 = (Edge*)a;
        Edge* b1 = (Edge*)b;
        return a1->weight > b1->weight;
    }

    // Kruskal's algorithm to find MST
    void kruskalMST() {
        Edge result[V]; // to store the MST
        int e = 0; // index variable for result[]
        int i = 0; // index variable for sorted edges
        int parent[V]; // parent array for Union-Find

        // Initialize parent array as -1
        memset(parent, -1, sizeof(parent));

        // Sort edges in non-decreasing order by weight
        qsort(edge, E, sizeof(Edge), compare);

        // Iterate through all sorted edges
        while (e < V - 1 && i < E) {
            Edge next_edge = edge[i++];

            int x = find(parent, next_edge.src);
            int y = find(parent, next_edge.dest);

            // If including this edge does not cause a cycle, add it to the result
            if (x != y) {
                result[e++] = next_edge;
                Union(parent, x, y);
            }
        }

        // Print the edges of MST
        cout << "Edges of MST:" << endl;
        int cost = 0;
        for (i = 0; i < e; i++) {
            cout << result[i].src << " -- " << result[i].dest << " : " << result[i].weight << endl;
            cost+=result[i].weight;
        }
        cout << "Cost of MST: " << cost << endl;
    }
};

int main() {
    int V, E;
    cout << "Enter number of vertices and edges: ";
    cin >> V >> E;

    Graph g(V, E);

    cout << "Enter the source, destination and weight of each edge:" << endl;
    for (int i = 0; i < E; i++) {
        int u, v, w;
        cout << "Edge " << i + 1 << ": ";
        cin >> u >> v >> w;
        g.addEdge(u, v, w);
    }

    g.kruskalMST();

    return 0;
}