import java.util.Scanner;
import java.util.regex.Pattern;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


class DateInfo
{
    int dd;
    int mm;
    int yyyy;

    public DateInfo(int dd, int mm, int yyyy)
    {
        this.dd = dd;
        this.mm = mm;
        this.yyyy = yyyy;
    }

    @Override
    public String toString()
    {
        return String.format("%04d-%02d-%02d", yyyy, mm, dd);
    }
}

class Patient
{
    String passport;
    String name;
    DateInfo birth_date;
    String phone;
    double temperature;
    String skin_color;

    public Patient(String passport, String name, DateInfo birth_date, String phone, double temperature, String skin_color)
    {
        this.passport = passport;
        this.name = name;
        this.birth_date = birth_date;
        this.phone = phone;
        this.temperature = temperature;
        this.skin_color = skin_color;
    }

    @Override
    public String toString() {
        return "Данные пациента:\n" +
                "Паспорт: " + passport + "\n" +
                "Имя: " + name + "\n" +
                "Дата рождения: " + birth_date + "\n" +
                "Телефон: " + phone + "\n" +
                "Температура: " + String.format("%.2f", temperature) + "\n" +
                "Цвет кожи; " + skin_color;
    }
}


void main()
{
    List<Patient> patients = new ArrayList<>();

    while(true) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Система регистрации пациентов");

        String passport = getValidPassport(scanner);
        String name = getValidName(scanner);
        DateInfo birth_date = getValidDate(scanner);
        String phone = getValidPhone(scanner);
        System.out.println("Добавьте температуру: ");
        double temperature = scanner.nextDouble();
        String skin_color = getValidSkinColor(scanner);

        Patient patient = new Patient(passport, name, birth_date, phone, temperature, skin_color);
        patients.add(patient);

        System.out.println("Данные успешно сохранены");
        System.out.print("Хотите ли вы ввести еще пациента (да/нет): ");
        String answer = scanner.nextLine().trim().toLowerCase();
        if (!answer.equals("да")) {
            scanner.close();
            break;
        }

    }

    System.out.println("\nСписок всех пациентов");
    for (int i = 0; i < patients.size(); i++) {
        System.out.println("\nПациент #" + (i + 1));
        System.out.println(patients.get(i));
        System.out.println("----------------------------");
    }
}

String getValidPassport(Scanner scanner) {
    while (true) {
        System.out.print("Введите номер паспорта (формат: ss ss-nnnnnn): ");
        String input = scanner.nextLine().trim();
        if (Pattern.matches("^\\d{2} \\d{2}-\\d{6}$", input)) {
            return input;
        }
        System.out.println("Ошибка: неверный формат паспорта. Попробуйте снова.\n");
    }
}

String getValidName(Scanner scanner) {
    while (true) {
        System.out.print("Введите имя пациента: ");
        String input = scanner.nextLine().trim();
        if (!input.isEmpty()) {
            return input;
        }
        System.out.println("Ошибка: имя не может быть пустым. Попробуйте снова.\n");
    }
}

DateInfo getValidDate(Scanner scanner) {
    while (true) {
        System.out.print("Введите дату рождения (формат: yyyy-mm-dd): ");
        String input = scanner.nextLine().trim();
        if (Pattern.matches("^\\d{4}-\\d{2}-\\d{2}$", input)) {
            try {

                LocalDate date = LocalDate.parse(input, DateTimeFormatter.ISO_LOCAL_DATE);
                return new DateInfo(date.getDayOfMonth(), date.getMonthValue(), date.getYear());
            }
            catch (DateTimeParseException e)
            {
                System.out.println("Ошибка: некорректная дата. Попробуйте снова.\n");
            }
        }
        else
        {
            System.out.println("Ошибка: неверный формат даты. Попробуйте снова.\n");
        }
    }
}

String getValidPhone(Scanner scanner) {
    while (true) {
        System.out.print("Введите номер телефона (формат: +X(XXX) XXX-XX-XX или X(XXX) XXX-XXXX): ");
        String input = scanner.nextLine().trim();
        String regex = "^(\\+\\d\\(\\d{3}\\) \\d{3}-\\d{2}-\\d{2}|\\d\\(\\d{3}\\) \\d{3}-\\d{4})$";
        if (Pattern.matches(regex, input)) {
            return input;
        }
        System.out.println("Ошибка: неверный формат телефона. Попробуйте снова.\n");
    }
}

double getValidTemperature(Scanner scanner) {
    while (true) {
        System.out.print("Введите температуру (формат: XX.XX): ");
        String input = scanner.nextLine().trim();
        if (Pattern.matches("^\\d{2}\\.\\d{2}$", input)) {
            return Double.parseDouble(input);
        }
        System.out.println("Ошибка: неверный формат температуры. Попробуйте снова.\n");
    }
}

String getValidSkinColor(Scanner scanner) {
    while (true) {
        System.out.print("Введите цвет кожи в формате RGB (от 0 до 255 три числа через пробел): ");
        String input = scanner.nextLine().trim();

        if (Pattern.matches("^\\d{1,3} \\d{1,3} \\d{1,3}$", input)) {
            String[] parts = input.split(" ");
            int r = Integer.parseInt(parts[0]);
            int g = Integer.parseInt(parts[1]);
            int b = Integer.parseInt(parts[2]);

            if (r >= 0 && r <= 255 && g >= 0 && g <= 255 && b >= 0 && b <= 255) {
                return input;
            }
        }
        System.out.println("Ошибка: неверный формат. Каждое значение должно быть от 0 до 255. Попробуйте снова.\n");
    }
}
