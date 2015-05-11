package kpi.labs;

import java.util.List;
import java.util.Optional;

public class SecondLabHelper extends LabHelper {
    private TerryUndirectedGraph<Integer> terryUndirectedGraph = new TerryUndirectedGraph<>();

    public String getPathBetween(Integer vertexOne, Integer vertexTwo) {
        final Optional<List<Integer>> pathBetween = terryUndirectedGraph.getPathBetween(vertexOne, vertexTwo);
        if (pathBetween.isPresent()) {
            return pathBetween.get().toString();
        }
        return "Немає шляху з " + vertexOne + " до " + vertexTwo;
    }

    @Override
    protected UndirectedGraph<Integer> getGraph() {
        return terryUndirectedGraph;
    }
}
