import java.math.BigDecimal;
import java.util.Scanner;

public class DepositCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        BigDecimal deposit = getValidBigDecimal(scanner);
        int percent = getValidInt(
                scanner,
                "Введите годовую процентную ставку (%): ",
                "Ошибка: процентная ставка должна быть от 1 до 100.",
                1,
                100
        );
        int multiplier = getValidInt(scanner,
                "Введите кратность увеличения вклада: ",
                "Ошибка: кратность увеличения вклада должна быть больше 1.",
                2,
                Integer.MAX_VALUE
        );

        double years = Math.log(multiplier) / Math.log(1 + percent / 100.0);
        int roundedYears = (int) Math.ceil(years);

        System.out.println("\nСумма вклада увеличится в " + multiplier + " раза через " + roundedYears + " лет.");
        scanner.close();
    }

    private static BigDecimal getValidBigDecimal(Scanner scanner) {
        while (true) {
            System.out.print("Введите сумму вклада: ");
            if (scanner.hasNextBigDecimal()) {
                BigDecimal value = scanner.nextBigDecimal();
                if (value.compareTo(BigDecimal.ZERO) > 0) {
                    return value;
                }
                System.out.println("Ошибка: Число должно быть больше 0.");
            } else {
                System.out.println("Ошибка: Неверный формат числа. Введите положительное число.");
                scanner.next();
            }
        }
    }

    private static int getValidInt(Scanner scanner, String prompt, String errorMessage, int minValue, int maxValue) {
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                int value = scanner.nextInt();
                if (value >= minValue && value <= maxValue) {
                    return value;
                }
                System.out.println(errorMessage);
            } else {
                System.out.println("Ошибка: Введите целое число.");
                scanner.next();
            }
        }
    }
}
