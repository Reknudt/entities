package org.pavlov.service.impl;

import lombok.AllArgsConstructor;
import org.pavlov.exception.ResourceNotFoundException;
import org.pavlov.model.Employee;
import org.pavlov.repository.EmployeeRepository;
import org.pavlov.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public void createEmployee(Employee employeeRequest) {
        //Employee employee = employeeMapper.createRequestToEntity(employeeRequest);

        employeeRepository.save(employeeRequest);
    }

    @Override
    public void updateEmployee(Long id, Employee employeeRequest) {
        Employee employee = findByIdOrThrow(id);

//        employee = employeeMapper.updateEmployeeFromRequest(employeeRequest, employee);

        employee.setName(employeeRequest.getName());
        employee.setBoss_id(employeeRequest.getBoss_id());
        employee.setDepartment_id(employeeRequest.getDepartment_id());

        employeeRepository.save(employee);
    }

    @Override
    public Optional<Employee> getEmployee(Long id) {
        Employee employee = findByIdOrThrow(id);

        return Optional.ofNullable(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll().stream()
                .toList();
    }

    @Override
    public void deleteEmployee(Long id) {

        employeeRepository.deleteById(id);
    }

    private Employee findByIdOrThrow(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Resource not found"));
    }
}