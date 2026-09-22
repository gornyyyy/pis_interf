class DateInfo
{
    int dd;
    int mm;
    int yyyy;

    @Override
    public String toString() {
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

    @Override
    public String toString() {
        return "Данные пациента:\n" +
                "Паспорт: " + passport + "\n" +
                "ФИО: " + name + "\n" +
                "Дата рождения: " + birth_date + "\n" +
                "Телефон: " + phone + "\n" +
                "Температура: " + String.format("%.2f", temperature);
    }
}

void main()
{
    Scanner scanner = new Scanner(System.in);



    System.out.println("хаюшки");
}