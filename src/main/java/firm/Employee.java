package firm;

import javax.persistence.*;


@Entity
public class Employee {

    @Id
    @GeneratedValue
//    @Column(precision = 0, scale = 4) // gdyby trzeba było ograniczyc id do postaci XXXX bez przecinków
    private Long id;

    @Transient
    private Salary salary;

    @Column(length = 30)
    private String firstName;

    @Column(length = 30)
    private String lastName;

    @Column(scale = 8, precision = 0) // 10^8 groszy moze zarabiac ziomeczek
    private Long salaryGr; // płaca w groszach

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Salary getSalary() {
        return new Salary((int) (salaryGr / 100), (int) (salaryGr % 100)); // rzutujemy w int
    }

    public void setSalary(Salary salary) {
        this.salary = salary;
        if (salary != null) {
            this.salaryGr = salary.getZlote() * 100L + salary.getGrosze(); //100L zeby byl long
        } else {
            this.salaryGr = 0L;
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    //=====================================

    @OneToOne
    private Address address;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @ManyToOne
    private Department department;

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
