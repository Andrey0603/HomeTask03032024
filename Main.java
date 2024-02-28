import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите данные: Фамилия Имя Отчество дата_рождения номер_телефона пол");

        String input = scanner.nextLine();
        String[] inputData = input.split(" ");

        if (inputData.length != 6) {
            System.out.println("Ошибка: введено неверное количество данных.");
            return;
        }

        String surname = inputData[0];
        String firstName = inputData[1];
        String lastName = inputData[2];
        String birthDate = inputData[3];
        Long phoneNumber;
        try {
            phoneNumber = Long.parseLong(inputData[4]);
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: неверный формат номера телефона.");
            return;
        }
        String gender = inputData[5];

        try {
            validateData(surname, firstName, lastName, birthDate, phoneNumber, gender);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(surname + ".txt", true))) {
            String line = surname + " " + firstName + " " + lastName + " " + birthDate + " " + phoneNumber + " " + gender;
            writer.write(line);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void validateData(String surname, String firstName, String lastName, String birthDate, Long phoneNumber, String gender) {
        if (!birthDate.matches("\\d{2}\\.\\d{2}\\.\\d{4}")) {
            throw new IllegalArgumentException("неверный формат даты рождения");
        }

        if (!(gender.equals("f") || gender.equals("m"))) {
            throw new IllegalArgumentException("пол должен быть 'f' или 'm'");
        }
    }
}
