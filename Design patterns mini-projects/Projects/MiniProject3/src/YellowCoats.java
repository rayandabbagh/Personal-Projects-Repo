import java.util.ArrayList;

public class YellowCoats {
    Employee ceo;

    public YellowCoats(Employee ceo) {
        this.ceo = ceo;
    }

    public void printEmployees() {
        System.out.println("--------------- Printing All Employees ---------------");
        rPrintEmployees(ceo);
    }

    private void rPrintEmployees(Employee curr) {
        int count = 1;
        curr.print();
        for (int i = 0; i < curr.getManages().size(); i++) {
            rPrintEmployees(curr.getManages().get(i));
            count++;
        }
    }

    public void printMalesWithDependents() {
        System.out.println("----- Printing All Male Employees with Dependents -----");
        rPrintMalesWithDependents(ceo);
    }

    private void rPrintMalesWithDependents(Employee curr) {
        if (curr.getGender().equals("Male") && curr.getDependents()) {
            curr.print();
        }
        for (int i = 0; i < curr.getManages().size(); i++) {
            rPrintMalesWithDependents(curr.getManages().get(i));
        }
    }

    public void printFemales() {
        System.out.println("-------------- Printing Female Employees --------------");
        rPrintFemales(ceo);
    }

    private void rPrintFemales(Employee curr) {
        if (curr.getGender().equals("Female")) {
            curr.print();
        }
        for (int i = 0; i < curr.getManages().size(); i++) {
            rPrintFemales(curr.getManages().get(i));
        }
    }

    public void printAverageSalaries() {
        ArrayList<String> departments = new ArrayList<>();
        getDepartments(departments, ceo);
        System.out.println("----- Printing Average Salaries Of Each Department -----");
        for (int i = 0; i < departments.size(); i++) {
            printAverageSalary(departments.get(i));
        }
    }

    private void printAverageSalary(String department) {
        ArrayList<Employee> e = new ArrayList<>();
        getEmployeesInDepartment(department, ceo, e);
        double total = 0;
        for (Employee emp : e) {
            total += emp.getSalary();
        }
        System.out.printf("Average salary in the %s department is $%.2f\n", department, total / e.size());
    }

    public void getEmployeesInDepartment(String department, Employee curr, ArrayList<Employee> e) {
        if (curr.getDepartment().equals(department)) {
            e.add(curr);
        }
        for (int i = 0; i < curr.getManages().size(); i++) {
            getEmployeesInDepartment(department, curr.getManages().get(i), e);
        }
    }

    public void getDepartments(ArrayList<String> departments, Employee curr) {
        if (!departments.contains(curr.getDepartment())) {
            departments.add(curr.getDepartment());
        }
        for (int i = 0; i < curr.getManages().size(); i++) {
            getDepartments(departments, curr.manages.get(i));
        }
    }

    public void printFemalesWithDependents() {
        System.out.println("----- Printing All Female Employees with Dependents -----");
        rPrintFemalesWithDependents(ceo);
    }

    private void rPrintFemalesWithDependents(Employee curr) {
        if (curr.getGender().equals("Females") && curr.getDependents()) {
            curr.print();
        }
        for (int i = 0; i < curr.getManages().size(); i++) {
            rPrintFemalesWithDependents(curr.getManages().get(i));
        }
    }


    public void printMales() {
        System.out.println("-------------- Printing Male Employees --------------");
        rPrintMales(ceo);
    }

    private void rPrintMales(Employee curr) {
        if (curr.getGender().equals("Male")) {
            curr.print();
        }
        for (int i = 0; i < curr.getManages().size(); i++) {
            rPrintMales(curr.getManages().get(i));
        }
    }
}
