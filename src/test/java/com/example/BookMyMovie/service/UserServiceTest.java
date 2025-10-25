package com.example.BookMyMovie.service;

import com.example.BookMyMovie.dto.AuthResponse;
import com.example.BookMyMovie.dto.LoginRequest;
import com.example.BookMyMovie.dto.RegisterRequest;
import com.example.BookMyMovie.dto.UserRegisterationResponse;
import com.example.BookMyMovie.exception.DuplicateIdFoundException;
import com.example.BookMyMovie.exception.IdDoesNotExistException;
import com.example.BookMyMovie.exception.InvalidCredentialsException;
import com.example.BookMyMovie.model.UserProfile;
import com.example.BookMyMovie.repository.UserRepository;
import com.example.BookMyMovie.security.JwtUtil;
import net.bytebuddy.dynamic.DynamicType;
import org.apache.catalina.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    public UserService userService;

    @Mock
    public UserRepository userRepository;
    @Mock
    public PasswordEncoder passwordEncoder;
    @Mock
    public JwtUtil jwtUtil;

    @BeforeEach
    void setup()
    {

    }

    //Add user
    @Test
    void shouldAddUserIfIdIsNotPresent()
    {
        UserProfile userProfile=new UserProfile();
        userProfile.setUserId(1);

        when(userRepository.existsById(1)).thenReturn(false);
        userService.add(userProfile);
        verify(userRepository,times(1)).save(userProfile);
    }

    @Test
    void ShouldNotAddUserIfIdIsPresent()
    {
        UserProfile userProfile=new UserProfile();
        userProfile.setUserId(1);

        when(userRepository.existsById(1)).thenReturn(true);
        assertThrows(DuplicateIdFoundException.class,() ->userService.add(userProfile));
    }

    //Delete user
    @Test
    void ShouldDeleteUserIfIdIsPresent()
    {
        UserProfile userProfile=new UserProfile();
        userProfile.setUserId(1);

        when(userRepository.existsById(1)).thenReturn(true);
        userService.delete(userProfile);
        verify(userRepository,times(1)).delete(userProfile);
    }

    @Test
    void ShouldNotDeleteUserIfIdNotExists()
    {
        UserProfile userProfile=new UserProfile();
        userProfile.setUserId(1);

        when(userRepository.existsById(1)).thenReturn(false);
        assertThrows(IdDoesNotExistException.class, ()->userService.delete(userProfile));
    }

    //update user
    @Test
    void ShouldUpdateUserIfIdExists()
    {
        UserProfile userProfile=new UserProfile();
        userProfile.setUserId(1);

        when(userRepository.existsById(1)).thenReturn(true);
        userService.update(userProfile);
        verify(userRepository,times(1)).save(userProfile);
    }

    @Test
    void ShouldNotUpdateUserIfIdNOtExists()
    {
        UserProfile userProfile=new UserProfile();
        userProfile.setUserId(1);

        when(userRepository.existsById(1)).thenReturn(false);
        assertThrows(IdDoesNotExistException.class, ()->userService.update(userProfile));

    }
    @Test
    void ShouldNotRegisterIfEmailIdAlreadyExists()
    {
        RegisterRequest registerRequest=new RegisterRequest();
        registerRequest.setEmail("abc@gmail.com");

        when(userRepository.existsByEmail("abc@gmail.com")).thenReturn(true);
        assertThrows(DuplicateIdFoundException.class, ()->userService.register(registerRequest));

    }

    @Test
    void ShouldRegisterIfEmailIdDoesNotExists()
    {
        RegisterRequest registerRequest=new RegisterRequest();
        registerRequest.setFirstName("Ambika");
        registerRequest.setLastName("garg");
        registerRequest.setMobileNo(989129819L);
        registerRequest.setEmail("abc@gmail.com");
        registerRequest.setPassword("aba@@@");

//       UserProfile userProfile = new UserProfile("abc@gmail.com","aba@@@",UserProfile.Role.ADMIN);

        when(passwordEncoder.encode("aba@@@")).thenReturn("encodedpwd");
        when(userRepository.existsByEmail("abc@gmail.com")).thenReturn(false);
        //when(jwtUtil.generateToken("abc@gmail.com", UserProfile.Role.ADMIN)).thenReturn("MockToken");

        UserRegisterationResponse userRegisterationResponse = userService.register(registerRequest);

        verify(userRepository,times(1)).save(any(UserProfile.class));
        //assertEquals("MockToken", authResponse.getToken());
        assertEquals("abc@gmail.com",userRegisterationResponse.getEmail());
        assertEquals(UserProfile.Role.CUSTOMER, userRegisterationResponse.getRole());
    }

    @Test
    void ShouldNotAllowLogInWhenEmailIdNotFound()
    {
        LoginRequest request=new LoginRequest();
        request.setEmail("acv");
        when(userRepository.findByEmail(request.getEmail())).thenReturn(Optional.empty());
        assertThrows(InvalidCredentialsException.class, ()->userService.login(request));
    }

    @Test
    void ShouldNotAllowSamePassword()
    {
        UserProfile userProfile = new UserProfile();
        userProfile.setPassword("abc");

        Optional<UserProfile> userProfileOptional = Optional.of(userProfile);

        LoginRequest request=new LoginRequest();
        request.setEmail("acv");
        request.setPassword("abc");

        when(userRepository.findByEmail(request.getEmail())).thenReturn(userProfileOptional);
        when(passwordEncoder.matches(request.getPassword(),userProfile.getPassword())).thenReturn(false);

        assertThrows(InvalidCredentialsException.class, ()->userService.login(request));
    }

    @Test
    void ShouldLoginAfterComparingTheCredentials()
    {

        UserProfile userProfile=new UserProfile();
        userProfile.setEmail("abc@gmail.com");
        userProfile.setRole(UserProfile.Role.ADMIN);
        userProfile.setPassword("abc");

        Optional<UserProfile> userProfileOptional = Optional.of(userProfile);

        LoginRequest request=new LoginRequest();
        request.setEmail("acv");
        request.setPassword("abc");

        when(userRepository.findByEmail(request.getEmail())).thenReturn(userProfileOptional);
        when(passwordEncoder.matches(request.getPassword(),userProfile.getPassword())).thenReturn(true);

        when(jwtUtil.generateToken(userProfile.getEmail(),userProfile.getRole())).thenReturn("Mocktoken");
        AuthResponse authResponse =userService.login(request);

        assertEquals("Mocktoken",authResponse.getToken());
        assertEquals("abc@gmail.com",authResponse.getEmail());
        assertEquals(UserProfile.Role.ADMIN,authResponse.getRole());
    }


}


