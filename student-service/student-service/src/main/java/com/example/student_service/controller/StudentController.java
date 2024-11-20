package com.example.student_service.controller;

import com.example.student_service.entity.ResponseDto;
import com.example.student_service.entity.Student;
import com.example.student_service.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users")
@AllArgsConstructor
public class StudentController{

    @Autowired
    private StudentService userService;

    @PostMapping
    public ResponseEntity<Student> saveUser(@RequestBody Student user) {
        Student savedUser = userService.saveUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseDto> getUser(@PathVariable("id") Long userId) {
        ResponseDto responseDto = userService.getUser(userId);
        return ResponseEntity.ok(responseDto);
    }
}