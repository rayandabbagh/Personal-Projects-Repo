import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class YellowCoatsTests {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        Employee employee1 = new Employee("Name", "Position", "Department", 100000,
                "Male", false);
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    /**
     * Julius
     * This test case ensures that none of the string arguments are equal to null. If null is passed as an argument,
     * our code throws an IllegalArgumentException.
     */
    @Test
    public void checkForNullArgs() {
        try {
            Employee employee = new Employee("George Burdell", "Software Developer", null,
                    100000, null, true);
            fail();
        } catch (IllegalArgumentException e) {
            String expected = "Arguments cannot be null";
            assertEquals("Exception message must be correct", expected, e.getMessage());
        }
    }

    /**
     * Prisha
     * In this test case we initialize four employees with random values for their names, positions, departments,
     * salaries, genders, and departments. We set up the structure in a way that employee 1 manages all the other
     * employees in the company. We then print out all the employees and match out expected output to our
     * obtained output
     */
    @Test
    public void printEmployeesTest() {
        Employee employee1 = new Employee("Name1", "CEO", "Headquarters",
                220000.00, "Male", false);
        Employee employee2 = new Employee("Name2", "CFO", "Administration",
                172000.00, "Female", true);
        Employee employee3 = new Employee("Name3", "CRADO", "R&D",
                160000.00, "Male", true);
        Employee employee4 = new Employee("Name4", "Office Manager", "Administration",
                75000.00, "Female", false);
        employee1.addEmployee(employee4);
        employee1.addEmployee(employee3);
        employee1.addEmployee(employee2);
        YellowCoats company = new YellowCoats(employee1);
        company.printEmployees();
        Assert.assertEquals("--------------- Printing All Employees ---------------\n" +
                        "Name1, CEO, Headquarters, 220000.0, Male, false\n" +
                        "Name4, Office Manager, Administration, 75000.0, Female, false\n" +
                        "Name3, CRADO, R&D, 160000.0, Male, true\n" +
                "Name2, CFO, Administration, 172000.0, Female, true\n", outputStreamCaptor.toString());
    }

    /**
     * Rohith
     * In our code, we had a method that printed out all the female employees. However, we added a method that
     * would help us obtain a list of all the male employees in the company, which are then printed out. We
     * match out obtained output with the expected output
     */
    @Test
    public void printMaleEmployees() {
        Employee employee1 = new Employee("Name1", "CEO", "Headquarters",
                220000.00, "Male", false);
        Employee employee2 = new Employee("Name2", "CFO", "Administration",
                172000.00, "Female", true);
        Employee employee3 = new Employee("Name3", "CRADO", "R&D",
                160000.00, "Male", true);
        Employee employee4 = new Employee("Name4", "Office Manager", "Administration",
                75000.00, "Female", false);
        employee1.addEmployee(employee4);
        employee1.addEmployee(employee3);
        employee1.addEmployee(employee2);
        YellowCoats company = new YellowCoats(employee1);
        company.printMales();
        assertEquals("-------------- Printing Male Employees --------------\n" +
                "Name1, CEO, Headquarters, 220000.0, Male, false\n" +
                "Name3, CRADO, R&D, 160000.0, Male, true\n", outputStreamCaptor.toString());
    }

    /**
     * Aashna
     * In this test case, we initialized a bunch of employees with random values for their names, positions,
     * departments, salaries, and dependents. We then printed out the average salaries varying by department
     * to ensure that the mean is being calculated correctly.
     */
    @Test
    public void printAverageSalary() {
        Employee employee1 = new Employee("Name1", "CEO", "Headquarters",
                220000.00, "Male", false);
        Employee employee2 = new Employee("Name2", "CFO", "Administration",
                50000.00, "Female", true);
        Employee employee3 = new Employee("Name3", "CRADO", "Administration",
                60000.00, "Male", true);
        Employee employee4 = new Employee("Name4", "Office Manager", "R&D",
                60000.00, "Female", false);
        Employee employee5 = new Employee("Name5", "Software Developer", "Headquarters",
                140000.00, "Female", false);
        Employee employee6 = new Employee("Name6", "Prototype Investigator", "R&D",
                185000.00, "Female", false);
        employee1.addEmployee(employee2);
        employee1.addEmployee(employee3);
        employee1.addEmployee(employee4);
        employee1.addEmployee(employee5);
        employee1.addEmployee(employee6);
        YellowCoats company = new YellowCoats(employee1);
        company.printAverageSalaries();
        assertEquals("----- Printing Average Salaries Of Each Department -----\n" +
                "Average salary in the Headquarters department is $180000.00\n" +
                "Average salary in the Administration department is $55000.00\n" +
                "Average salary in the R&D department is $122500.00\n", outputStreamCaptor.toString());
    }

    /**
     * Rayan
     * This particular test case prints out a list of all the female employees with dependents. Initially, we
     * only had a method that could test all the male employees with dependents. However, we added a method that
     * would perform the same functionality but this time for female employees and print all of the female employees'
     * that have dependents.
     */
    @Test
    public void testFemaleEmployeesWithDependents() {
        Employee james = new Employee("James Borg", "CEO", "Headquarters",
                220000.00, "Male", false);
        Employee jennifer = new Employee("Jennifer Wallace", "CFO", "Administration",
                172000.00, "Female", true);
        Employee franklin = new Employee("Franklin Wong", "CRADO", "R&D",
                160000.00, "Male", true);
        Employee alicia = new Employee("Alicia Zelaya", "Office Manager",
                "Administration", 75000.00, "Female", true);
        Employee ahmad = new Employee("Ahmad Jabbar", "Admin", "Administration",
                100000.00, "Male", false);
        Employee john = new Employee("John Smith", "Research Assistant", "R&D",
                120000.00, "Male", true);
        Employee ramesh = new Employee("Ramesh Narayan", "Data Scientist", "R&D",
                152000.00, "Male", false);
        james.addEmployee(jennifer);
        james.addEmployee(franklin);
        jennifer.addEmployee(alicia);
        jennifer.addEmployee(ahmad);
        franklin.addEmployee(john);
        franklin.addEmployee(ramesh);
        YellowCoats company = new YellowCoats(james);
        company.printFemalesWithDependents();
        assertEquals("----- Printing All Female Employees with Dependents -----\n" +
                        "Jennifer Wallace, CFO, Administration, 172000.0, Female, true\n" +
                        "Alicia Zelaya, Office Manager, Administration, 75000.0, Female, true\n",
                outputStreamCaptor.toString());
    }
}