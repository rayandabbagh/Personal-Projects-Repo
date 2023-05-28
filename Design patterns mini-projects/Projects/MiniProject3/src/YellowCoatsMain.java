public class YellowCoatsMain {

    public static void main(String[] args) {
        Employee james = new Employee("James Borg", "CEO", "Headquarters", 220000.00, "Male", false);
        Employee jennifer = new Employee("Jennifer Wallace", "CFO", "Administration", 172000.00, "Female", true);
        Employee franklin = new Employee("Franklin Wong", "CRADO", "R&D", 160000.00, "Male", true);
        Employee alicia = new Employee("Alicia Zelaya", "Office Manager", "Administration", 75000.00, "Female", false);
        Employee ahmad = new Employee("Ahmad Jabbar", "Admin", "Administration", 100000.00, "Male", false);
        Employee john = new Employee("John Smith", "Research Assistant", "R&D", 120000.00, "Male", true);
        Employee ramesh = new Employee("Ramesh Narayan", "Data Scientist", "R&D", 152000.00, "Male", false);
        james.addEmployee(jennifer);
        james.addEmployee(franklin);
        jennifer.addEmployee(alicia);
        jennifer.addEmployee(ahmad);
        franklin.addEmployee(john);
        franklin.addEmployee(ramesh);
        YellowCoats company = new YellowCoats(james);

        company.printEmployees();
        company.printMalesWithDependents();
        company.printFemales();
        company.printAverageSalaries();

        Employee joyce = new Employee("Joyce English", "ML Engineer", "R&D", 140000.00, "Female", false);
        ramesh.addEmployee(joyce);
        System.out.println("\nNow with Joyce added as en employee:\n");

        company.printEmployees();
        company.printMalesWithDependents();
        company.printFemales();
        company.printAverageSalaries();
    }
}
