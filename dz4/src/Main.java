import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.SplittableRandom;

public class Main {

    private static final long[] N_VALUES = {
            1_000_000L,
            10_000_000L,
            100_000_000L,
            1_000_000_000L,
            10_000_000_000L,
            100_000_000_000L
    };

    private static final String RESULTS_DIR = "results";
    private static final String RESULTS_FILE = "results/pi_monte_carlo_results.csv";

    public static void main(String[] args) {

        createResultsDirectory();

        try (BufferedWriter writer =
                     new BufferedWriter(new FileWriter(RESULTS_FILE))) {

            writer.write("N,pi_estimate,absolute_error,execution_time_seconds,time_per_point");
            writer.newLine();

            for (long n : N_VALUES) {

                System.out.println("Calculating for N = " + n);

                long startTime = System.nanoTime();

                long inside = calculatePointsInsideCircle(n);

                long endTime = System.nanoTime();

                double executionTime =
                        (endTime - startTime) / 1_000_000_000.0;

                double piEstimate =
                        4.0 * inside / n;

                double error =
                        Math.abs(piEstimate - Math.PI);

                double timePerPoint =
                        executionTime / n;

                writer.write(
                        n + "," +
                                piEstimate + "," +
                                error + "," +
                                executionTime + "," +
                                timePerPoint
                );

                writer.newLine();

                System.out.println("PI = " + piEstimate);
                System.out.println("Error = " + error);
                System.out.println("Time = " + executionTime);
                System.out.println("----------------------------");
            }

            System.out.println("Results saved.");

        } catch (IOException e) {

            System.out.println(e.getMessage());
        }
    }

    private static long calculatePointsInsideCircle(long n) {

        SplittableRandom random = new SplittableRandom();

        long inside = 0;

        for (long i = 0; i < n; i++) {

            double x = random.nextDouble();
            double y = random.nextDouble();

            if (x * x + y * y <= 1.0) {
                inside++;
            }
        }

        return inside;
    }

    private static void createResultsDirectory() {

        File directory = new File(RESULTS_DIR);

        if (!directory.exists()) {
            directory.mkdirs();
        }
    }
}