package firm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@Controller
public class EmployeeController {
    @Autowired
    private EmployeeRepository repEmployee;
    @Autowired
    private AddressRepository repAddress;

    @RequestMapping("/firm")
    @ResponseBody
    //public List<Employee> getAll() {
        //return rep.findAll();
    //}
    public Page<Employee> getall(Pageable pageable) {
        return repEmployee.findAll(pageable);
    }

    //DWA SPOSOBY NA PODWYŻKĘ

    @RequestMapping("/firm/{id}/increase/{raise}") // tu podwyzka podawana w url
    @ResponseBody
    public void salaryRaise(@PathVariable("id") Long id, @PathVariable("raise") long raise) {
        repEmployee.salaryRaise(id, raise);
    }

    @RequestMapping("/firm/{id}/increase") // tu podwyzka podawana w body (np. w json)
    @ResponseBody
    public void salaryRaise2(@PathVariable("id") Long id, @RequestBody Salary salaryRaise) {
        Employee employee = repEmployee.getOne(id);
        if (employee != null) {
            employee.setSalary(employee.getSalary().add(salaryRaise));
            repEmployee.save(employee);
        }
    }

    @RequestMapping("firm/{id}")
    @ResponseBody
    public Employee getOne(@PathVariable("id") Long id) {
        return repEmployee.findById(id).orElse(null);
    }

    @RequestMapping("firm/salary/{salaryGr}")
    @ResponseBody
    public List<Employee> getBySalaryGr(@PathVariable("salaryGr") Long salaryGr) {
        return repEmployee.findBySalaryGr(salaryGr);
    }

    @RequestMapping("firm/salaryGreaterThan/{salaryGr}")
    @ResponseBody
    public List<Employee> getSalaryGreaterThan(@PathVariable("salaryGr") Long salaryGr) {
        return repEmployee.findAllBySalaryGrGreaterThan(salaryGr);
    }

    @RequestMapping("firm/lastName/{name}")
    @ResponseBody
    public List<Employee> getByLastName(@PathVariable("name") String lastName) {
        return repEmployee.findByLastName(lastName);
    }

    @RequestMapping("firm/byLastName")
    @ResponseBody
    public List<Employee> getAllOrderByLastName() {
        return repEmployee.findByOrderByLastName();
    }

    @RequestMapping("firm/size")
    @ResponseBody
    public long size() {
        return repEmployee.count();
    }

    @RequestMapping(value = "/firm", method = RequestMethod.POST)
    @ResponseBody
    public Employee create(@RequestBody Employee employee) {
        employee.setId(null);
        return repEmployee.save(employee);
    }

    @RequestMapping(value = "/firm/{id}", method = PUT)
    @ResponseBody
    public Employee updateOrCreatePut(@PathVariable("id") Long id, @RequestBody Employee employee) {
        employee.setId(id);
        return repEmployee.save(employee);
    }

    @RequestMapping(value = "/firm/{id}", method = RequestMethod.PATCH)
    @ResponseBody
    public Employee updateOrCreatePatch(@PathVariable("id") Long id, @RequestBody Employee employee) {
        Employee old = repEmployee.getOne(id);
        if (old == null) {
            return repEmployee.save(employee);
        }
        if (employee.getSalary() != null) old.setSalary(employee.getSalary());
        if (employee.getLastName() != null) old.setLastName(employee.getLastName());
        if (employee.getFirstName() != null) old.setFirstName((employee.getFirstName()));
        return repEmployee.save(old);
    }

    @RequestMapping(value = "/firm/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable("id") Long id) {
        repEmployee.deleteById(id);
    }

    //nie działa ale nic lepszego nie przyszło mi do głowy
    @RequestMapping("firm/byInitials/{initials}")
    @ResponseBody
    public List<Employee> findByInitials(@PathVariable("initials") String initials) {
        char fst = initials.charAt(0);
        char lst = initials.charAt(1);
        return repEmployee.findAllByInitials(fst, lst);
    }

    @RequestMapping(value = "/firm/{id}/address/{idAddress}", method = PUT)
    @ResponseBody
    public Employee setAddress(@PathVariable("id") Long id,
                               @PathVariable("idAddress") Long idAddress) {
        Employee old = repEmployee.getOne(id);
        old.setAddress(repAddress.getOne(idAddress));
        repEmployee.save(old);
        return repEmployee.getOne(id);
    }

    @RequestMapping("firm/city/{miasto}") // pobierze pracowników z miasta
    @ResponseBody
    public List<Employee> getByCity(@PathVariable("miasto")String miasto) {
        return repEmployee.findAllByCity(miasto);
    }
}
