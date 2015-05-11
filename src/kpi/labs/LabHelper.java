package kpi.labs;

import java.util.stream.IntStream;

public abstract class LabHelper {
    public void setMatrixOfIncidences(boolean[][] matrix) {
        final UndirectedGraph<Integer> graph = getGraph();
        IntStream.rangeClosed(1, matrix.length).forEach(graph::addVertex);

        for (int edgeIndex = 0; edgeIndex < matrix[0].length; edgeIndex++) {
            Integer edgeVertexOne = null;
            Integer edgeVertexTwo = null;
            for (int vertexIndex = 0; vertexIndex < matrix.length; vertexIndex++) {
                final boolean value = matrix[vertexIndex][edgeIndex];
                if (value) {
                    if (edgeVertexOne == null) {
                        edgeVertexOne = vertexIndex + 1;
                    } else {
                        edgeVertexTwo = vertexIndex + 1;
                        break;
                    }
                }
            }
            if (edgeVertexOne == null || edgeVertexTwo == null) {
                throw new IllegalStateException("Where is vertex of the " + (edgeIndex + 1) + " edge??");
            }
            graph.addEdge(edgeVertexOne, edgeVertexTwo);
        }
    }

    protected abstract UndirectedGraph<Integer> getGraph();
}
