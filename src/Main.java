import java.util.Random;

public class Main {

    private final static Random RANDOM = new Random();
    private final static String[] NAMES = {"Сергей", "Антон", "Роман", "Александр", "Михаил", "Ярослав"};
    private final static String[] SURNAMES = {"Ульянов", "Багромян", "Рокоссовский", "Жуков", "Панов", "Тихомиров"};
    private final static String[] PATRONYMICS_NAMES = {"Федорович", "Геннадьевич", "Алексеевич", "Георгиевич", "Владимирович", "Вячеславович"};

    private final static Employee[] EMPLOYEES = new Employee[10];

    public static void initEmployees() {
        for (int i = 0; i < EMPLOYEES.length; i++) {
            String fullName = SURNAMES[RANDOM.nextInt(0, SURNAMES.length)] + " " +
                    NAMES[RANDOM.nextInt(0, NAMES.length)] + " " +
                    PATRONYMICS_NAMES[RANDOM.nextInt(0, PATRONYMICS_NAMES.length)];
            EMPLOYEES[i] = new Employee(fullName, RANDOM.nextInt(1, 6), RANDOM.nextInt(50_000, 250_000));
        }
    }

    public static void main(String[] args) {
        initEmployees();
        print();
    }

    public static void print() {
        for (Employee employee : EMPLOYEES) {
            System.out.println(employee);
        }
    }
}