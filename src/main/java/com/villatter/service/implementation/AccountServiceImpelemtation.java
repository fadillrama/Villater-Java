package com.villatter.service.implementation;

import com.villatter.dto.account.ChangePasswordDTO;
import com.villatter.dto.account.RegisterDTO;
import com.villatter.dto.utility.ApplicationUserDetails;
import com.villatter.entity.Account;
import com.villatter.repository.AccountRepository;
import com.villatter.service.abstraction.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpelemtation implements UserDetailsService, AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var account = accountRepository.findById(username).get();

        if (account == null){
            throw new UsernameNotFoundException("User not found !");
        } else {
            var userDetail = new ApplicationUserDetails(account);
            return userDetail;
        }
    }

    @Override
    public Boolean isUsernameExist(String username){
        var total = accountRepository.accountExistingUser(username);
        if (total > 0){
            return true;
        }
        return false;
    }

    @Override
    public void register(RegisterDTO dto){
        var entity = new Account();
        entity.setUsername(dto.getUsername());
        var hashPassword = passwordEncoder.encode(dto.getPassword());
        entity.setPassword(hashPassword);
        entity.setRole(dto.getRole());
        accountRepository.save(entity);
    }

    @Override
    public Boolean checkUsernamePassword(String username, String password){
        var isAuthenticated = false;
        var entity = accountRepository.findById(username).get();
        if (entity != null){
            var hashPassword = entity.getPassword();
            isAuthenticated = passwordEncoder.matches(password, hashPassword);
        }
        return isAuthenticated;
    }

    @Override
    public void changePassword(ChangePasswordDTO dto){
        var entity = accountRepository.findById(dto.getUsername()).get();
        var hashNewPassword = passwordEncoder.encode(dto.getPassword());

        entity.setPassword(hashNewPassword);
        accountRepository.save(entity);
    }

    @Override
    public String getRole(String username){
        return accountRepository.findById(username).get().getRole();
    }
}
