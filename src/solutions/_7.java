/**
 * LCA of N-ary tree 
 * Find common manager
 */
import java.util.*;

class Employee {
	String name;
	List<Employee> getReports;
	
	public Employee(String name) {
		this.name = name;
		this.getReports = new ArrayList<>();
	}
	
	public String toString() {
		return "Employee{" +
                "name='" + name + '\'' +
                '}';
	}
}

class _7 {
	public static void main(String[] args) {
		Employee samir = new Employee("samir");
        Employee dom = new Employee("dom");
        Employee michael = new Employee("michael");

        Employee peter = new Employee("peter");
        Employee porter = new Employee("porter");
        Employee bob = new Employee("bob");

        dom.getReports = Arrays.asList(bob, peter, porter);

        Employee milton = new Employee("milton");
        Employee nina = new Employee("nina");

        peter.getReports = Arrays.asList(milton, nina);

        Employee bill = new Employee("bill");
        bill.getReports = Arrays.asList(dom, samir, michael);
        
        System.out.println(findCommonManager(bill, milton, nina) + " ==peter");
        System.out.println(findCommonManager(bill, nina, porter) + " ==dom");
        System.out.println(findCommonManager(bill, nina, samir) + " ==null (bill ceo)");
        System.out.println(findCommonManager(bill, peter, nina) + " ==peter");
	}
	
	static public Employee findCommonManager(Employee ceo, Employee e1, Employee e2) {
		if (ceo == null) {
			return null;
		}
		
		if (ceo == e1) {
			return e1;
		}
		
		if (ceo == e2) {
			return e2;
		}
		
		boolean isEmployee1Manager = false;
		boolean isEmployee2Manager = false;

		for (int i = 0; i < ceo.getReports.size(); i++) {
			Employee e = findCommonManager(ceo.getReports.get(i), e1, e2);

			
			if (e == e1) {
				isEmployee1Manager = true;
			} else if (e == e2) {
				isEmployee2Manager = true;
			} else if (e != null) {
				return e;
			}
		}
		
		if (isEmployee1Manager && isEmployee2Manager) {
			return ceo;
		}
		
		if (isEmployee1Manager) {
			return e1;
		}
		
		if (isEmployee2Manager) {
			return e2;
		}
		
		return null;
	}
}

