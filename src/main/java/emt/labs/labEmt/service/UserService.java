package emt.labs.labEmt.service;

import emt.labs.labEmt.model.User;
import emt.labs.labEmt.model.dto.JwtResponseDto;
import emt.labs.labEmt.model.dto.LoginDto;
import emt.labs.labEmt.model.dto.RegisterDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    User findById(String username);

    List<User> findAll();

    JwtResponseDto signInUser(LoginDto loginDto);

    void signUpUser(RegisterDto registerDto);
}
