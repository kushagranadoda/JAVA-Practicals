abstract class Employee {
    String name;
    String id;

    Employee(String name, String id) {
        this.name = name;
        this.id = id;
    }

    abstract double monthlySalary();
}

class FullTime extends Employee {
    double sal;
    FullTime(String name, String id, double sal) {
        super(name, id);
        this.sal = sal;
    }
    double monthlySalary() { return sal; }
}

class PartTime extends Employee {
    double hrs, rate;
    PartTime(String name, String id, double hrs, double rate) {
        super(name, id);
        this.hrs = hrs;
        this.rate = rate;
    }
    double monthlySalary() { return hrs * rate; }
}

class Intern extends Employee {
    double stipend;
    Intern(String name, String id, double stipend) {
        super(name, id);
        this.stipend = stipend;
    }
    double monthlySalary() { return stipend; }
}

public class PayrollDemo {
    public static void main(String[] args) {
        Employee[] arr = {
            new FullTime("Alice", "E1", 50000),
            new PartTime("Bob", "E2", 80, 300),
            new Intern("Charlie", "E3", 10000)
        };

        double total = 0;

        for (Employee e : arr) {
            double sal = e.monthlySalary();
            total += sal;
            String note = (e instanceof Intern) ? " (Intern)" : "";
            System.out.println(e.name + " (" + e.id + "): Rs. " + sal + note);
        }

        System.out.println("Total payroll: Rs. " + total);
    }
}