package com.adib.authentificationservice.services;

import com.adib.authentificationservice.models.AppRole;
import com.adib.authentificationservice.models.AppUser;

import java.util.List;

public interface AccountService {
    AppUser addNewUser(AppUser appUser);
    AppRole addNewRole(AppRole appRole);
    void addRoleToUser(String username,String roleName);
    AppUser loadUserByUsername(String username);
    List<AppUser> listUsers();

}
