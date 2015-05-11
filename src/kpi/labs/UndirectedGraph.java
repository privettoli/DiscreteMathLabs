package kpi.labs;

import java.util.*;

public abstract class UndirectedGraph<T> {
    protected Map<T, Set<T>> graphNodes = new HashMap<>();

    public void addVertex(T vertex) {
        graphNodes.put(vertex, new HashSet<>());
    }

    public void addEdge(T between, T and) {
        graphNodes.get(between).add(and);
        graphNodes.get(and).add(between);
    }

    public int size() {
        return graphNodes.size();
    }
}
