public class Employee {
    private static int idGererator = 1;
    private final int id;
    private final String fullName;
    private int department;
    private double salary;

    public Employee(String fullName, int department, double salary) { // изменил int на double для salary
        id = idGererator++;
        this.fullName = fullName;
        this.department = department;
        this.salary = salary;
    }
    public void setSalary(double salary) {
        this.salary = salary;
    }
    public void setDepartment(int department) {
        this.department = department;
   }
    public int getId() {
        return id;
    }
    public String getFullName() {
        return fullName;
    }
    public int getDepartment() {
        return department;
    }
    public double getSalary() {
        return salary;
    }
    @Override
    public String toString() {
        return "id=" + id +
        ", fullName='" + fullName + '\\' +
        ", department=" + department +
        ", salary=" + salary;
    }
}