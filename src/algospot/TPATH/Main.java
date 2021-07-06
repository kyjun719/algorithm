package algospot.TPATH;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Edge {
        public int start;
        public int dest;
        public int w;

        public Edge(int start, int dest, int w) {
            this.start = start;
            this.dest = dest;
            this.w = w;
        }

        @Override
        public int hashCode() {
            int ret = 31;
            ret = ret * 17 + start;
            ret = ret * 17 + dest;
            ret = ret * 17 + w;
            return ret;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Edge) {
                Edge o = (Edge) obj;
                return o.start == this.start &&
                        o.dest == this.dest &&
                        o.w == this.w;
            }
            return false;
        }

        @Override
        public String toString() {
            return "[" + start+"~"+ dest + ">>" + w + "]";
        }
    }

    static int N;
    static int INF = 987654321;
    static ArrayList<Edge>[] adj;
    static ArrayList<Integer> weightList = new ArrayList<>();
    static ArrayList<Edge> edgeArrayList = new ArrayList<>();

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int tc = Integer.parseInt(br.readLine());
            for (int t = 0; t < tc; t++) {
                int[] tmp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                N = tmp[0];
                int M = tmp[1];
                adj = new ArrayList[N];
                for (int i = 0; i < N; i++) {
                    adj[i] = new ArrayList<>();
                }
                edgeArrayList = new ArrayList<>();

                for (int i = 0; i < M; i++) {
                    tmp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                    int a = tmp[0];
                    int b = tmp[1];
                    int w = tmp[2];
                    adj[a].add(new Edge(a, b, w));
                    adj[b].add(new Edge(b, a, w));

                    edgeArrayList.add(new Edge(a, b, w));
                }
                createEdgeList();
                edgeArrayList.sort(new Comparator<Edge>() {
                    @Override
                    public int compare(Edge o1, Edge o2) {
                        return o1.w - o2.w;
                    }
                });

                //System.out.println(new SolveWithBinarySearch().solve());
                System.out.println(new SolveWithMinUpper().minWeightDifference());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void createEdgeList() {
        Set<Integer> edgeSet = new HashSet<>();
        for(ArrayList<Main.Edge> edges : Main.adj) {
            for(Main.Edge e : edges) {
                edgeSet.add(e.w);
            }
        }

        weightList.clear();
        weightList.addAll(edgeSet);
        weightList.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1-o2;
            }
        });
    }

    private static boolean[] seen;
    private static boolean hasPath(int low, int high) {
        seen = new boolean[Main.N];
        return dfs(0, low, high);
    }

    private static boolean dfs(int here, int low, int high) {
        seen[here] = true;
        if(here == Main.N-1) {
            return true;
        }

        for(Main.Edge e : Main.adj[here]) {
            if(e.w>= low && e.w<=high && !seen[e.dest]) {
                if(dfs(e.dest, low, high)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static class SolveWithBinarySearch {
        public int solve() {
            int low = 0;
            int high = 0;
            int ret = Main.INF;

            while(true) {
                if(hasPath(weightList.get(low), weightList.get(high))) {
                    ret = Math.min(ret, weightList.get(high)-weightList.get(low));
                    low++;
                } else {
                    if(high == weightList.size()-1) {
                        break;
                    }
                    high++;
                }
                if(low == weightList.size()) {
                    break;
                }
            }

            return ret;
        }
    }

    public static class SolveWithMinUpper {
        public int minWeightDifference() {
            int ret = INF;
            for(int i = 0; i < weightList.size(); i++) {
                //ret = Math.min(ret, minBinaryUpperBound(i)-weightList.get(i));
                ret = Math.min(ret, minKruskalUpperBound(i)-weightList.get(i));
            }
            return ret;
        }

        private int minBinaryUpperBound(int l) {
            int low = l-1;
            int high = weightList.size();
            while(low+1 < high) {
                int mid = (low+high)/2;
                if(hasPath(weightList.get(l), weightList.get(mid))) {
                    high = mid;
                } else {
                    low = mid;
                }
            }
            if(high == weightList.size()) {
                return INF;
            }
            return weightList.get(high);
        }

        private int minKruskalUpperBound(int l) {
            DisjointSet set = new DisjointSet(N);

            for(Edge e : edgeArrayList) {
                if(e.w < weightList.get(l)) {
                    continue;
                }
                set.merge(e.start, e.dest);
                if(set.find(0) == set.find(N-1)) {
                    return e.w;
                }
            }
            return INF;
        }

        private static class DisjointSet {
            int[] parent;
            int[] rank;

            public DisjointSet(int n) {
                parent = new int[n];
                rank = new int[n];
                Arrays.fill(rank, 1);
                for(int i = 0; i < n; i++) {
                    parent[i] = i;
                }
            }

            public int find(int u) {
                if(u == parent[u]) {
                    return u;
                }
                parent[u] = find(parent[u]);
                return parent[u];
            }

            public void merge(int u, int v) {
                u = find(u);
                v = find(v);
                if(u == v) {
                    return;
                }

                if(rank[u] > rank[v]) {
                    int tmp = u;
                    u = v;
                    v = tmp;
                }
                parent[u] = v;
                if(rank[u] == rank[v]) {
                    rank[v]++;
                }
            }
        }
    }
}


