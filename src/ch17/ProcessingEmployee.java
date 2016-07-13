package ch17;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class ProcessingEmployee {

	public static void main(String []args){
		
		Employee[] employees = {
				   new Employee("Jason", "Red", 5000, "IT"),
				   new Employee("Ashley", "Green", 7600, "IT"),
				   new Employee("Matthew", "Indigo", 3587.5, "Sales"),
				   new Employee("James", "Indigo", 4700.77, "Marketing"),
				   new Employee("Luke", "Indigo", 6200, "IT"),
				   new Employee("Jason", "Blue", 3200, "Sales"),
				   new Employee("Wendy", "Brown", 4236.4, "Marketing")};
		
		List<Employee> list = Arrays.asList(employees);
		System.out.println("Complete employees list: ");
		Predicate<Employee> fourToSixThousand = e -> (e.getSalary() >= 4000 && e.getSalary() <= 6000);
		list.stream()
		    .filter(fourToSixThousand)
		    .sorted(Comparator.comparing(Employee::getSalary))
		    .forEach(System.out::println);

	}
}
