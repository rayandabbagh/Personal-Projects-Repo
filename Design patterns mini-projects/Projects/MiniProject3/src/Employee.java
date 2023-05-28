import java.util.ArrayList;

public class Employee {
    ArrayList<Employee> manages = new ArrayList<>();
    String name;
    String position;
    String department;
    double salary;
    String gender;
    boolean dependents;

    public Employee(String name, String position, String department, double salary, String gender, boolean dependents) {
        if (name != null && position != null && department != null && gender != null) {
            this.name = name;
            this.position = position;
            this.department = department;
            this.salary = salary;
            this.gender = gender;
            this.dependents = dependents;
        } else {
            throw new IllegalArgumentException("Arguments cannot be null");
        }
    }

    public void addEmployee(Employee e) {
        manages.add(e);
    }

    public String getName() { return name; }

    public String getPosition() { return position; }

    public String getDepartment() { return department; }

    public double getSalary() { return salary; }

    public String getGender() { return gender; }

    public boolean getDependents() { return dependents; }

    public ArrayList<Employee> getManages() { return manages; }

    public void print() {
       System.out.println(name + ", " + position + ", " + department + ", " + salary + ", " + gender + ", " + dependents);
    }
}
