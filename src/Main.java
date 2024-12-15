import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee("Иван Иванов", "IT", 50000, 6),
                new Employee("Пётр Петров", "HR", 60000, 4),
                new Employee("Сергей Сергеев", "IT", 70000, 7),
                new Employee("Сидр Сидоров", "Finance", 80000, 5),
                new Employee("Михаил Михайлов", "HR", 90000, 3),
                new Employee("Роман Романов", "IT", 100000, 12),
                new Employee("Илья Ильин", "Finance", 110000, 9),
                new Employee("Клим Климов", "IT", 120000, 11),
                new Employee("Антон Антонов", "HR", 130000, 10),
                new Employee("Виктор Викторов", "Finance", 140000, 8)
        );

        //сотрудники с опытом работы более 5 лет
        List<Employee> experienceMore5Employees = employees.stream()
                .filter(e -> e.getExperience() > 5)
                .toList();
        System.out.println("Сотрудники с опытом работы более 5 лет: ");
        experienceMore5Employees.forEach(System.out::println);

        //увеличение зарплаты всех сотрудников на 10%
        employees.forEach(s -> s.setSalary(s.getSalary() * 1.1));
        System.out.println("\nЗарплата всех сотрудников увеличена на 10%: ");
        employees.forEach(System.out::println);

        //сотрудник с минимальной зарплатой
        Optional<Employee> minSalaryEmployee = employees.stream()
                .min(Comparator.comparingDouble(Employee::getSalary));
        System.out.println("\nСотрудник с минимальной зарплатой: ");
        minSalaryEmployee.ifPresent(System.out::println);

        //группировка сотрудников по отделам
        Map<String, List<Employee>> employeesByDepartment = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
        System.out.println("\nСотрудники, сгруппированные по отделам: ");
        employeesByDepartment.forEach((department, employeesList) -> {
            System.out.println(department + ":");
            employeesList.forEach(System.out::println);
        });

        //средняя зарплата по каждому отделу
        Map<String, Double> averageSalaryByDepartment = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,
                        Collectors.averagingDouble(Employee::getSalary)));
        System.out.println("\nСредняя зарплата по каждому отделу: ");
        averageSalaryByDepartment.forEach((department, avgSalary) ->
                System.out.println(department + ": " + avgSalary));

        //проверка на наличие сотрудников с зарплатой выше 100к
        boolean hasSalaryMore100k = employees.stream()
                .anyMatch(s -> s.getSalary() > 100000);
        System.out.println("\nЕсть ли сотрудники с зарплатой выше 100000: " + hasSalaryMore100k);

        //список всех отделов без повторений
        List<String> departments = employees.stream()
                .map(Employee::getDepartment)
                .distinct()
                .toList();
        System.out.println("\nСписок всех отделов без повторений: ");
        departments.forEach(System.out::println);
    }
}