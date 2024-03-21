package com.villatter.rest;

import com.villatter.component.JwtManager;
import com.villatter.dto.account.RequestTokenDTO;
import com.villatter.dto.account.ResponseTokenDTO;
import com.villatter.service.abstraction.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/account")
public class AccountRestController extends AbstractRestController {

    @Autowired
    private JwtManager jwtManager;

    @Autowired
    private AccountService service;


    @Operation(
            summary = "Digunakan untuk me-request JWT (Token).",
            description = "Request ini akan mengembalikan username, role dan token."
    )
    @ApiResponse(
            responseCode = "200",
            content = { @Content(
                    mediaType = "application/json",
                    schema = @Schema( implementation = ResponseTokenDTO.class )
            )}
    )
    @PostMapping("/authenticate")
    public ResponseEntity<Object> post(@RequestBody RequestTokenDTO request){
        var isAuthenticated = service.checkUsernamePassword(request.getUsername(), request.getPassword());
        if (isAuthenticated){
            var role = service.getRole(request.getUsername());
            var token = jwtManager.generateToken(
                    request.getUsername(),
                    request.getSubject(),
                    role,
                    request.getAudience(),
                    request.getSecretKey()
            );
            var response = new ResponseTokenDTO(request.getUsername(), role, token);
            return ResponseEntity.status(200).body(response);
        }
        return ResponseEntity.status(401).body("Username atau Password salah !");
    }
}
