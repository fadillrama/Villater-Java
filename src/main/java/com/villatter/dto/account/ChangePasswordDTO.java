package com.villatter.dto.account;

import com.villatter.validation.CheckAuthentication;
import com.villatter.validation.ComparePassword;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jdk.jfr.Label;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@CheckAuthentication(message = "Old Password salah !!!", label = "oldPasswordLabel")
@ComparePassword(message = "Password tidak cocok !!!", label = "passwordConfirmationLabel") // TODO
public class ChangePasswordDTO {
    private String username;

    @NotBlank(message = "Old Password harus diisi !!!")
    private String oldPassword;

    @NotBlank(message = " New Password harus diisi !!!")
    @Size(max = 20, message = "Tidak boleh lebih dari 20 karakter !!!")
    private String password;

    @Size(max = 20, message = "Tidak boleh lebih dari 20 karakter !!!")
    @NotBlank(message = "Password Confirmation harus diisi !!!")
    private String passwordConfirmation;
}