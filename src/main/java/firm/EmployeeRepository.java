package firm;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Transactional
    @Modifying
    @Query("UPDATE Employee e SET e.salaryGr = e.salaryGr + ?2 WHERE e.id = ?1")
    void salaryRaise(Long id, long raise);
    List<Employee> findBySalaryGr(Long salaryGr);
    List<Employee> findAllBySalaryGrGreaterThan(Long salaryGr);
    List<Employee> findByLastName(String lastName);
    List<Employee> findByOrderByLastName();
    @Query(value = "SELECT * FROM Employee WHERE first_Name LIKE ?1% AND last_Name LIKE ?2%", nativeQuery = true)
    List<Employee> findAllByInitials(char fst, char lst);

    @Query(value = "SELECT * FROM employee inner join address on employee.address_id = address.id where city = ?1",
        nativeQuery = true)
    List<Employee> findAllByCity(String miasto);
}
