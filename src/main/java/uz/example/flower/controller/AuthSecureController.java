package uz.example.flower.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import uz.example.flower.model.JSend;
import uz.example.flower.model.dto.db.CheckCode;
import uz.example.flower.model.dto.db.LoginDto;
import uz.example.flower.model.dto.db.RegisterDto;
import uz.example.flower.model.entity.User;
import uz.example.flower.payload.request.ChangePasswordDto;
import uz.example.flower.payload.request.UserUpdateDto;
import uz.example.flower.service.UserService;
import uz.example.flower.service.tools.SecurityUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("api/secure/")
public class AuthSecureController {
    private final UserService userService;
    private final SecurityUtils securityUtils;

    public AuthSecureController(UserService userService, SecurityUtils securityUtils) {
        this.userService = userService;
        this.securityUtils = securityUtils;
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody LoginDto login) {
        JSend send = userService.signIn(login);
        return new ResponseEntity<>(send, HttpStatus.valueOf(send.getCode()));
    }

    @PostMapping("register")
    public ResponseEntity<?> register(@RequestBody RegisterDto register) {
        JSend user = userService.saveUser(register);
        return new ResponseEntity<>(user, HttpStatus.valueOf(user.getCode()));
    }
    
    @PostMapping("logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        SecurityContextHolder.clearContext();
        try {
            request.logout();
        } catch (ServletException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok("Log out successful");
    }

    @PutMapping("update")
    public ResponseEntity<?> updateData(@RequestBody @Valid UserUpdateDto userUpdate) {
        JSend response = userService.updateUser(userUpdate);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }

    @PostMapping("reset_password")
    public ResponseEntity<?> changePassword(@RequestBody @Valid ChangePasswordDto passwordDto) {
        JSend response = userService.changePassword(passwordDto);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }
}