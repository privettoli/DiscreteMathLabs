package kpi.labs;

import java.util.*;

public class Utils {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static int getInteger(int max) {
        return getInteger(1, max);
    }

    public static boolean[][] getMatrixOfIncidencesUsingConsoleInput() {
        while (true) {
            System.out.println("Введення матриці інцидентності");

            System.out.println("Введіть кількість вершин");
            final int vertexCount = getInteger(2, 30);

            System.out.println("Введіть кількість ребер");
            final int edgesCount = getInteger(calculateMaxEdgeCount(vertexCount));

            System.out.println("Ввводіть рядки за допомогою пробілу та ентеру");
            final boolean[][] matrix = new boolean[vertexCount][];
            for (int i = 0; i < vertexCount; i++) {
                matrix[i] = getVertexLineUsingConsoleInput(edgesCount, vertexCount);
            }

            if (validateMatrix(matrix)) {
                return matrix;
            } else {
                System.out.println("Ввведена матриця некорректна");
                System.out.println();
            }
        }
    }

    public static boolean[][] getRandomMatrixOfIncidences() {
        System.out.println("Введіть максимальну кількість вершин при генерації");
        final int vertexCountRandomLimit = getInteger(2, Integer.MAX_VALUE);
        final Random random = new Random();
        final int vertexCount = random.nextInt(vertexCountRandomLimit - 2) + 2;
        System.out.println("Кількість вершин - " + vertexCount);
        final int maxEdgeCount = calculateMaxEdgeCount(vertexCount);
        int edgesCount = maxEdgeCount;
        if (maxEdgeCount != 2) {
            System.out.println("Введіть максимальну кількість ребер при генерації");
            final int edgesCountRandomLimit = getInteger(2, maxEdgeCount);
            edgesCount = random.nextInt(edgesCountRandomLimit);
        }
        edgesCount = edgesCount < 2 ? maxEdgeCount : edgesCount;
        System.out.println("Кількість ребер - " + edgesCount);
        final boolean[][] matrix = new boolean[vertexCount][edgesCount];
        final Set<TwoWaysPair<Integer>> uniqueEdges = new HashSet<>();
        while (uniqueEdges.size() < edgesCount) {
            final int first = random.nextInt(vertexCount);
            int second = first;
            while (first == second) {
                second = random.nextInt(vertexCount);
            }
            uniqueEdges.add(new TwoWaysPair<>(first, second));
        }
        int i = 0;
        for (TwoWaysPair<Integer> uniqueEdge : uniqueEdges) {
            matrix[uniqueEdge.getFirst()][i] = true;
            matrix[uniqueEdge.getSecond()][i] = true;
            i++;
        }
        return matrix;
    }

    public static int calculateMaxEdgeCount(int vertexCount) {
        if (vertexCount < 2) {
            return 0;
        }
        return vertexCount * (vertexCount - 1) / 2;
    }

    private static boolean validateMatrix(boolean[][] matrix) {
        for (int i = 0; i < matrix[0].length; i++) {
            int wasTrueCount = 0;
            for (boolean[] vertexLine : matrix) {
                final boolean value = vertexLine[i];
                if (value) {
                    wasTrueCount++;
                }
            }
            if (wasTrueCount != 2) {
                return false;
            }
        }
        return true;
    }

    public static int getInteger(int min, int max) {
        while (true) {
            final Optional<Integer> validationResult = validateNumber(SCANNER.nextLine(), min, max);
            if (validationResult.isPresent()) {
                return validationResult.get();
            } else {
                System.out.println(String.format("Введіть число в діапазоні [%d;%d]", min, max));
            }
        }
    }

    public static boolean[] getVertexLineUsingConsoleInput(int edgesCount, int vertexCount) {
        while (true) {
            final String[] line = SCANNER.nextLine().trim().split(" ");
            if (line.length == edgesCount) {
                final Optional<boolean[]> validateVertexLineString = validateVertexLineString(line, vertexCount);
                if (validateVertexLineString.isPresent()) {
                    return validateVertexLineString.get();
                }
            }
            System.out.println("Невалідна строка");
        }
    }

    private static Optional<boolean[]> validateVertexLineString(String[] line, int vertexCount) {
        final boolean[] vertexLine = new boolean[line.length];
        int countOfOne = 0;
        for (int i = 0; i < line.length; i++) {
            final Optional<Integer> validateNumber = validateNumber(line[i], 0, 1);
            if (!validateNumber.isPresent() || (validateNumber.get() == 1 && ++countOfOne == vertexCount)) {
                return Optional.<boolean[]>empty();
            }
            vertexLine[i] = validateNumber.get() == 1;
        }
        return Optional.of(vertexLine);
    }

    public static Optional<Integer> validateNumber(String string, int min, int max) {
        if (string.matches("[+|-]?[0-9]+")) {
            final int number = Integer.parseInt(string);
            if (number >= min && number <= max) {
                return Optional.of(number);
            }
        }
        return Optional.empty();
    }

    public static void printMatrix(boolean[][] matrix) {
        Arrays.stream(matrix).map(Utils::booleanArrayToIntArray).forEach(Utils::print);
    }

    private static int[] booleanArrayToIntArray(boolean[] array) {
        final int[] intArray = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            intArray[i] = array[i] ? 1 : 0;
        }
        return intArray;
    }

    private static void print(int[] elements) {
        for (int element : elements) {
            System.out.print(element);
            System.out.print(' ');
        }
        System.out.println();
    }

    public static boolean yesOrNo() {
        return SCANNER.nextLine().equalsIgnoreCase("так");
    }
}
