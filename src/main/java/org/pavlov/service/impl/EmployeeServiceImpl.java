package org.pavlov.service.impl;

import lombok.AllArgsConstructor;
import org.pavlov.dto.request.EmployeeRequest;
import org.pavlov.dto.response.EmployeeResponse;
import org.pavlov.exception.ResourceNotFoundException;
import org.pavlov.mapper.EmployeeMapper;
import org.pavlov.model.Employee;
import org.pavlov.repository.EmployeeRepository;
import org.pavlov.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeMapper employeeMapper;
    private final EmployeeRepository employeeRepository;

    @Override
    public void createEmployee(Employee employeeRequest) {
        //Employee employee = employeeMapper.createRequestToEntity(employeeRequest);

        employeeRepository.save(employeeRequest);
    }

    @Override
    public void updateEmployee(Long id, EmployeeRequest employeeRequest) {
        Employee employee = findByIdOrThrow(id);

        employee = employeeMapper.updateStudentFromRequest(employeeRequest, employee);

        employeeRepository.save(employee);
    }

    @Override
    public Optional<EmployeeResponse> getEmployee(Long id) {
        Employee employee = findByIdOrThrow(id);

        return Optional.ofNullable(employeeMapper.entityToResponse(employee));
    }

    @Override
    public List<EmployeeResponse> getAllEmployees() {
        return employeeRepository.findAll().stream()
                .map(employeeMapper::toResponse)
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