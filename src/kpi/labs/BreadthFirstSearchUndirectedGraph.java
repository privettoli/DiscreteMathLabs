package kpi.labs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class BreadthFirstSearchUndirectedGraph<T> extends UndirectedGraph<T> {
    public int getDistanceBetween(T firstVertex, T secondVertex) {
        int distance = 0;
        if (firstVertex.equals(secondVertex)) {
            return distance;
        }

        final Set<T> visited = new HashSet<>(graphNodes.size());
        Queue<T> next = new LinkedList<>(graphNodes.get(firstVertex));
        visited.add(firstVertex);
        while (!next.isEmpty()) {
            distance++;
            final Queue<T> newNext = new LinkedList<>();
            for (T node : next) {
                if (node.equals(secondVertex)) {
                    return distance;
                }
                visited.add(node);
                graphNodes.get(node).stream()
                        .filter(relation -> !visited.contains(relation))
                        .forEach(newNext::add);
            }
            next = newNext;
        }
        return Integer.MAX_VALUE;
    }
}
