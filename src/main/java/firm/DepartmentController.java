package firm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@Controller
public class DepartmentController {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private DepartmentRepository departmentRepository;


    @RequestMapping(value = "firm/{id}/department/{idDepartment}", method = PUT)
    @ResponseBody
    public Employee setDepartment(@PathVariable("id") Long id,
                                  @PathVariable("idDepartment") Long idDepartment) {
        Employee old = employeeRepository.getOne(id);
        old.setDepartment(departmentRepository.getOne(idDepartment));
        employeeRepository.save(old);
        return employeeRepository.getOne(id);
    }
}
