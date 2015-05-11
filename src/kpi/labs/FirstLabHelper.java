package kpi.labs;

public class FirstLabHelper extends LabHelper {
    private BreadthFirstSearchUndirectedGraph<Integer> graph = new BreadthFirstSearchUndirectedGraph<>();

    public int[][] getMatrixOfDistances() {
        final int vertexCount = graph.size();
        int[][] matrixOfDistances = new int[vertexCount][vertexCount];
        for (int i = 0; i < vertexCount; i++) {
            for (int j = 0; j < vertexCount; j++) {
                final int distanceBetween = graph.getDistanceBetween(i + 1, j + 1);
                matrixOfDistances[i][j] = distanceBetween;
                matrixOfDistances[j][i] = distanceBetween;
            }
        }
        return matrixOfDistances;
    }


    @Override
    protected UndirectedGraph<Integer> getGraph() {
        return graph;
    }
}
