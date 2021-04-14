package emt.labs.labEmt.service.impl;

import emt.labs.labEmt.model.User;
import emt.labs.labEmt.model.dto.JwtResponseDto;
import emt.labs.labEmt.model.dto.LoginDto;
import emt.labs.labEmt.model.dto.RegisterDto;
import emt.labs.labEmt.model.exceptions.UserAlreadyExistsException;
import emt.labs.labEmt.repository.RoleRepository;
import emt.labs.labEmt.repository.UserRepository;
import emt.labs.labEmt.security.jwt.JwtUtils;
import emt.labs.labEmt.security.services.UserDetailsImpl;
import emt.labs.labEmt.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public User findById(String username) {
        return this.userRepository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    @Override
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public JwtResponseDto signInUser(LoginDto loginDto) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        JwtResponseDto jwtResponseDto = new JwtResponseDto();
        jwtResponseDto.setAccessToken(jwt);
        jwtResponseDto.setUsername(userDetails.getUsername());
        jwtResponseDto.setRoles(roles);

        return jwtResponseDto;
    }

    @Override
    public void signUpUser(RegisterDto registerDto) {
        if (userRepository.findById(registerDto.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException(registerDto.getUsername());
        }

        User user = new User();
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setRole(this.roleRepository.findAll().stream().filter(r -> r.getName().name().equals(registerDto.getRole())).findFirst().get());

        userRepository.save(user);
    }
}
