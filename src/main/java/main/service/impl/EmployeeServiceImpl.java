package main.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import main.entity.Employee;
import main.mapper.EmployeeMapper;
import main.service.EmployeeService;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
}
