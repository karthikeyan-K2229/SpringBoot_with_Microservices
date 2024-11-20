package com.example.department_service.controller;

import com.example.department_service.entity.Department;
import com.example.department_service.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/departments/")
@RestController
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping("save")
    public ResponseEntity<Department> saveDepartment(@RequestBody Department department)
    {
        Department saveDepartment= departmentService.saveDepartment(department);
        return new ResponseEntity<>(saveDepartment, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable("id") Long departmentId){
        Department department = departmentService.getDepartmentById(departmentId);
        return ResponseEntity.ok(department);
    }

}
