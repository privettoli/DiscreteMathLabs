package kpi.labs;

public class FirstLab {
    public static void main(String... args) {
        System.out.println("Ввести з консолі (так) чи згенерувати автоматично?");
        final boolean[][] matrix;
        if (Utils.yesOrNo()) {
            matrix = Utils.getMatrixOfIncidencesUsingConsoleInput();
        } else {
            matrix = Utils.getRandomMatrixOfIncidences();
        }

        System.out.println("Виводимо матрицю інцидентності");
        Utils.printMatrix(matrix);

        // Створюємо допоміжний клас для роботи з графом та матрицями
        final FirstLabHelper firstLabHelper = new FirstLabHelper();
        firstLabHelper.setMatrixOfIncidences(matrix);

        System.out.println("Матриця відстаней:");
        final int[][] matrixOfDistances = firstLabHelper.getMatrixOfDistances();
        printMatrix(matrixOfDistances);
    }

    private static void printMatrix(int[][] matrixOfDistances) {
        for (int[] matrixOfDistance : matrixOfDistances) {
            for (int value : matrixOfDistance) {
                System.out.print(value == Integer.MAX_VALUE ? "-" : value);
                System.out.print(' ');
            }
            System.out.println();
        }
    }
}