package com.example.orchidservice.controller;

import com.example.orchidservice.dto.AccountDTO;
import com.example.orchidservice.dto.ApiResponse;
import com.example.orchidservice.dto.LoginRequestDTO;
import com.example.orchidservice.dto.LoginResponseDTO;
import com.example.orchidservice.dto.RegisterRequestDTO;
import com.example.orchidservice.dto.RegisterResponseDTO;
import com.example.orchidservice.pojo.Account;
import com.example.orchidservice.service.imp.IAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final IAccountService accountService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<RegisterResponseDTO>> register(@Valid @RequestBody RegisterRequestDTO request) {
        RegisterResponseDTO response = accountService.register(request);
        return ResponseEntity.ok(ApiResponse.<RegisterResponseDTO>builder()
            .code(1000)
            .message("Registration successful")
            .result(response)
            .build());
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponseDTO>> login(@Valid @RequestBody LoginRequestDTO request) {
        LoginResponseDTO response = accountService.login(request);
        return ResponseEntity.ok(ApiResponse.<LoginResponseDTO>builder()
            .code(1000)
            .message("Login successful")
            .result(response)
            .build());
    }

    @PreAuthorize("hasRole('SuperAdmin') or hasRole('Admin') or (hasRole('User') and #id == authentication.principal.accountId)")
    @GetMapping("/profile/{id}")
    public ResponseEntity<ApiResponse<AccountDTO>> getAccount(@PathVariable Integer id) {
        Optional<Account> accountOpt = accountService.getAccountById(id);
        if (accountOpt.isEmpty()) {
            return ResponseEntity.badRequest()
                .body(ApiResponse.<AccountDTO>builder()
                    .code(1004)
                    .message("Account not found")
                    .build());
        }

        Account account = accountOpt.get();
        AccountDTO accountDTO = AccountDTO.builder()
            .accountId(account.getAccountId())
            .accountName(account.getAccountName())
            .email(account.getEmail())
            .roleId(account.getRole().getRoleId())
            .roleName(account.getRole().getRoleName())
            .build();

        return ResponseEntity.ok(ApiResponse.<AccountDTO>builder()
            .code(1000)
            .message("Account retrieved successfully")
            .result(accountDTO)
            .build());
    }
    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<Void>> logout(@RequestHeader("Authorization") String authHeader) {
        String token = null;
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
        }

        accountService.logout(token);

        return ResponseEntity.ok(ApiResponse.<Void>builder()
                .code(1000)
                .message("Logged out successfully")
                .build());
    }
}
