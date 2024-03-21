package com.villatter.dto.account;

import com.villatter.validation.ComparePassword;
import com.villatter.validation.UniqueUsername;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ComparePassword(message = "Password tidak cocok !!!")
public class RegisterDTO {

    @UniqueUsername(message = "Username sudah dipakai !!!")
    @Size(max = 20, message = "Tidak boleh lebih dari 20 karakter !!!")
    @NotBlank(message = "Username harus diisi !!!")
    private String username;

    @Size(max = 20, message = "Tidak boleh lebih dari 20 karakter !!!")
    @NotBlank(message = "Password harus diisi !!!")
    private String password;

    @Size(max = 20, message = "Tidak boleh lebih dari 20 karakter !!!")
    @NotBlank(message = "Password Confirmation harus diisi !!!")
    private String passwordConfirmation;

    @NotBlank(message = "Role harus diisi !!!")
    private String role;
}
