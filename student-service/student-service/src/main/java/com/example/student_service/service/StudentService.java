package com.example.student_service.service;

import com.example.student_service.entity.DepartmentDto;
import com.example.student_service.entity.ResponseDto;
import com.example.student_service.entity.Student;
import com.example.student_service.entity.UserDto;
import com.example.student_service.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class StudentService {
    @Autowired
    private StudentRepository userRepository;
    @Autowired
    private RestTemplate restTemplate;

    public Student saveUser(Student user) {
        return userRepository.save(user);
    }


    public ResponseDto getUser(Long userId) {
        ResponseDto responseDto = new ResponseDto();
        Student user = userRepository.findById(userId).get();
        UserDto userDto = mapToUser(user);
        ResponseEntity<DepartmentDto> responseEntity= restTemplate.
                getForEntity("http://localhost:8081/api/departments/" + user.getDepartmentId(),
                DepartmentDto.class);
        DepartmentDto departmentDto = responseEntity.getBody();
        responseDto.setUser(userDto);
        responseDto.setDepartment(departmentDto);
        return responseDto;



    }
    private UserDto mapToUser(Student user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        return userDto;
    }

}
