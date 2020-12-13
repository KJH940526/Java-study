package prob6;

public class Depart extends Employee {
	String department;
	
	public Depart() {
		//기본 생성자
	}

	public Depart(String name, int salary, String department) {
		// TODO Auto-generated constructor stub
		super.setName(name);
		super.setSalary(salary);
		this.department=department;
	}

	@Override
	public void getInformation() {
		System.out.printf("이름: %s  연봉: %d  부서:%s\n", super.getName(), super.getSalary(),this.department);
	}

}
