package com.sky.service;

import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.result.PageResult;

public interface EmployeeService {

    /**
     * 员工登录
     * @param employeeLoginDTO
     * @return
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    /**
     * 添加用户
     * @param employeeDTO
     */
    void save(EmployeeDTO employeeDTO);

    PageResult page(EmployeePageQueryDTO employeePageQueryDTO);

    void startOrStop(Integer status, Long id);

    /**
     * 更新员工信息
     * @param employeeDTO
     */
    void update(EmployeeDTO employeeDTO);

    /**
     * 根据id查询员工
     * @param id
     * @return
     */
    Employee getById(Long id);
}
