package kpi.labs;

public class SecondLab {
    public static void main(String[] args) {
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
        final SecondLabHelper labHelper = new SecondLabHelper();
        labHelper.setMatrixOfIncidences(matrix);

        System.out.println("Введіть від якої вершини ви хочете почати пошук");
        final int startVertex = Utils.getInteger(1, labHelper.getGraph().size());

        System.out.println("Введіть до якої вершини ви хочете вести пошук");
        final int finalVertex = Utils.getInteger(1, labHelper.getGraph().size());

        System.out.println("Шлях");
        System.out.println(labHelper.getPathBetween(startVertex, finalVertex));
    }
}
