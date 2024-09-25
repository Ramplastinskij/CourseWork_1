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
        System.out.println();
        System.out.println("Сумма затрат на ЗП сотрудников: " + calculateSummaOfSalaries());
        System.out.println();
        System.out.println("Сотрудник с минимальной ЗП: " + findEmployeeWithMinSalaries());
        System.out.println("Сотрудник с максимальной ЗП: " + findEmployeeWithMaxSalaries());
        System.out.println();
        System.out.println("Средняя ЗП сотрудников: " + calculateAverageOfSalaries());
        System.out.println();
        printFullNames();
        System.out.println();
        indexSalaries(10);
        System.out.println();
        int setDepartment = 3;
        double percentage = 10;
        processDepartment(setDepartment, percentage);
        System.out.println();
        double salaryThreshold = 100000; // Установите нужную зарплату
        printEmployeesWithSalaryLessThan(salaryThreshold);
        printEmployeesWithSalaryGreaterOrEqual(salaryThreshold);
    }
    public static void print() {
        for (Employee employee : EMPLOYEES) {
            System.out.println(employee);
        }
    }
    public static double calculateSummaOfSalaries() {
        double summa = 0;
        for (Employee employee : EMPLOYEES) {
            summa += employee.getSalary();
        }
        return summa;
    }
    public static Employee findEmployeeWithMinSalaries() {
        Employee employeeWithMinSalary = null;

        for (Employee employee : EMPLOYEES) {
            if (employeeWithMinSalary == null || employee.getSalary() < employeeWithMinSalary.getSalary()) {
                employeeWithMinSalary = employee;
            }
        }
        return employeeWithMinSalary;
    }
    public static Employee findEmployeeWithMaxSalaries() {
        Employee employeeWithMaxSalary = null;

        for (Employee employee : EMPLOYEES) {
            if (employeeWithMaxSalary == null || employee.getSalary() > employeeWithMaxSalary.getSalary()) {
                employeeWithMaxSalary = employee;
            }
        }
        return employeeWithMaxSalary;
    }
    public static double calculateAverageOfSalaries() {
        return calculateSummaOfSalaries() / EMPLOYEES.length;
    }
    public static void printFullNames() {
        for (Employee employee : EMPLOYEES) {
            System.out.println(employee.getFullName());
        }
    }
    public static void indexSalaries(double percentage) {
        for (Employee employee : EMPLOYEES) {
            double oldSalary = employee.getSalary();
            double newSalary = oldSalary + (oldSalary * percentage / 100);
            employee.setSalary(newSalary);
            System.out.printf("Сотрудник: %s, Старая ЗП: %.2f, Новая ЗП: %.2f%n",
                    employee.getFullName(), oldSalary, newSalary);
        }
    }
    public static void processDepartment(int setDepartment, double percentage) {
        Employee[] departmentEmployees = getDepartmentEmployees(setDepartment);
        if (departmentEmployees.length == 0) {
            System.out.println("No employees in department " + setDepartment);
            return;
        }
        Employee employeeWithMinSalary = findEmployeeWithMinSalaries(departmentEmployees);
        Employee employeeWithMaxSalary = findEmployeeWithMaxSalaries(departmentEmployees);

        double summaOfSalaries = calculateSummaOfSalaries(departmentEmployees);
        double averageOfSalaries = calculateAverageOfSalaries(departmentEmployees, summaOfSalaries);

        System.out.println("Department " + setDepartment);
        System.out.println("Сотрудник с минимальной ЗП: " + employeeWithMinSalary);
        System.out.println("Сотрудник с максимальной ЗП: " + employeeWithMaxSalary);
        System.out.println("Сумма затрат на ЗП сотрудников: " + summaOfSalaries);
        System.out.println("Средняя ЗП сотрудников: " + averageOfSalaries);
        indexSalaries(departmentEmployees, percentage);
        printDepartmentEmployees(departmentEmployees);
    }
    public static Employee[] getDepartmentEmployees(int setDepartment) {
        Employee[] departmentEmployees = new Employee[0];

        for (Employee employee : EMPLOYEES) {
            if (employee.getDepartment() == setDepartment) {
                departmentEmployees = addEmployeeToDepartmentArray(departmentEmployees, employee);
            }
        }
        return departmentEmployees;
    }
    public static Employee[] addEmployeeToDepartmentArray(Employee[] departmentEmployees, Employee employee) {
        Employee[] newDepartmentEmployees = new Employee[departmentEmployees.length + 1];
        System.arraycopy(departmentEmployees, 0, newDepartmentEmployees, 0, departmentEmployees.length);
        newDepartmentEmployees[departmentEmployees.length] = employee;
        return newDepartmentEmployees;
    }
    public static Employee findEmployeeWithMinSalaries(Employee[] employees) {
        Employee employeeWithMinSalary = null;

        for (Employee employee : employees) {
            if (employeeWithMinSalary == null || employee.getSalary() < employeeWithMinSalary.getSalary()) {
                employeeWithMinSalary = employee;
            }
        }
        return employeeWithMinSalary;
    }
    public static Employee findEmployeeWithMaxSalaries(Employee[] employees) {
        Employee employeeWithMaxSalary = null;

        for (Employee employee : employees) {
            if (employeeWithMaxSalary == null || employee.getSalary() > employeeWithMaxSalary.getSalary()) {
                employeeWithMaxSalary = employee;
            }
        }
        return employeeWithMaxSalary;
    }
    public static double calculateSummaOfSalaries(Employee[] employees) {
        double summa = 0;
        for (Employee employee : employees) {
            summa += employee.getSalary();
        }
        return summa;
    }
    public static double calculateAverageOfSalaries(Employee[] employees, double summa) {
        return summa / employees.length;
    }
    public static void indexSalaries(Employee[] employees, double percentage) {
        for (Employee employee : employees) {
            double oldSalary = employee.getSalary();
            double newSalary = oldSalary + (oldSalary * percentage / 100);
            employee.setSalary(newSalary);
            System.out.printf("Сотрудник: %s, Старая ЗП: %.2f, Новая ЗП: %.2f%n",
                    employee.getFullName(), oldSalary, newSalary);
        }
    }
    public static void printDepartmentEmployees(Employee[] employees) {
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }
    public static void printEmployeesWithSalaryLessThan(double salary) {
        System.out.println("Сотрудники с зарплатой меньше " + salary + ":");
        for (Employee employee : EMPLOYEES) {
            if (employee.getSalary() < salary) {
                System.out.println("id: " + employee.getId() + ", ФИО: " + employee.getFullName() + ", ЗП: " + employee.getSalary());
            }
        }
    }

    public static void printEmployeesWithSalaryGreaterOrEqual(double salary) {
        System.out.println("Сотрудники с зарплатой больше или равной " + salary + ":");
        for (Employee employee : EMPLOYEES) {
            if (employee.getSalary() >= salary) {
                System.out.println("id: " + employee.getId() + ", ФИО: " + employee.getFullName() + ", ЗП: " + employee.getSalary());
            }
        }
    }
}
