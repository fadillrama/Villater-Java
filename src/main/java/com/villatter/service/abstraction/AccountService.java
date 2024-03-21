package com.villatter.service.abstraction;

import com.villatter.dto.account.ChangePasswordDTO;
import com.villatter.dto.account.RegisterDTO;

public interface AccountService {
    public Boolean isUsernameExist(String username);
    public void register(RegisterDTO dto);
    public Boolean checkUsernamePassword(String username, String password);
    public void changePassword(ChangePasswordDTO dto);
    public String getRole(String username);
}
