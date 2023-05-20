package main.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import main.common.R;
import main.entity.Employee;
import main.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


    /**
     * <h2>Login</h2>
     */
    @PostMapping("/login")
    public R<Employee> login(HttpServletRequest request, @RequestBody Employee employee) {

        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername, employee.getUsername());
        Employee emp = employeeService.getOne(queryWrapper);

        if (emp == null) {
            return R.error("User name or password incorrect!");
        }

        if (!emp.getPassword().equals(password)) {
            return R.error("User name or password incorrect!");
        }

        if (emp.getStatus() == 0) {
            return R.error("User name or password incorrect!");
        }

        request.getSession().setAttribute("employee", emp.getId());
        return R.success(emp);

    }


    /**
     * <h2>logout</h2>
     */
    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request) {
        request.getSession().removeAttribute("employee");
        return R.success("logout success");
    }


    /**
     * <h2>Add new employee</h2>
     */
    @PostMapping
    public R<String> save(HttpServletRequest request, @RequestBody Employee employee) {
        log.info("Add employee，information:{}", employee.toString());
        String password = employee.getIdNumber().substring(employee.getIdNumber().length() - 6);
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        employee.setPassword(password);

        employeeService.save(employee);

        return R.success("Added employee success");
    }


    /**
     * <h2>use name to search employee</h2>
     */
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name) {
        log.info("Search information，current page:{}，lists in page:{}，search condition:{}", page, pageSize, name);
        Page<Employee> pageParam = new Page<>(page, pageSize);
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotEmpty(name), Employee::getName, name);
        queryWrapper.orderByDesc(Employee::getUpdateTime);
        employeeService.page(pageParam, queryWrapper);
        return R.success(pageParam);
    }

    /**
     * <h2>use id to edit employee information</h2>
     */
    @PutMapping
    public R<String> update(HttpServletRequest request, @RequestBody Employee employee) {
        log.info("Edit employee information，employee:{}", employee);

        employeeService.updateById(employee);
        return R.success("Employee updated success");
    }


    /**
     * <h2>use id to search employee</h2>
     */
    @GetMapping("/{id}")
    public R<Employee> getById(@PathVariable Long id) {
        log.info("search employee，id:{}", id);
        Employee employee = employeeService.getById(id);
        if (employee != null) {
            return R.success(employee);
        }
        return R.error("The employee does not exist!");
    }

}

