package kpi.labs;

import java.util.*;
import java.util.stream.Collectors;

public class TerryUndirectedGraph<T> extends UndirectedGraph<T> {
    public Optional<List<T>> getPathBetween(T firstNode, T secondNode) {
        if (firstNode.equals(secondNode)) {
            return Optional.of(Collections.singletonList(firstNode));
        }

        final Set<TwoWaysPair<T>> passedEdges = new HashSet<>();

        final List<T> nextRoots = new LinkedList<>();
        nextRoots.addAll(graphNodes.get(firstNode));
        final List<T> parents = new LinkedList<>();
        parents.add(firstNode);
        while (!nextRoots.isEmpty()) {
            T parent = parents.get(parents.size() - 1);
            final int whereIndex = nextRoots.size() - 1;
            final T where = nextRoots.get(whereIndex);
            nextRoots.remove(whereIndex);
            if (!graphNodes.get(parent).contains(where)) {
                parents.remove(parent);
                parent = parents.get(parents.size() - 1);
            }
            if (secondNode.equals(where)) {
                parents.add(secondNode);
                return Optional.of(parents);
            }

            final TwoWaysPair<T> relation = new TwoWaysPair<>(parent, where);
            passedEdges.add(relation);

            final T finalParent = parent;
            final Set<T> relations = graphNodes.get(where).stream()
                    .filter((elem) -> elem != finalParent && !passedEdges.contains(new TwoWaysPair<>(where, elem))).collect(Collectors.toSet());
            nextRoots.addAll(relations);
            parents.add(where);
        }
        return Optional.empty();
    }
}
