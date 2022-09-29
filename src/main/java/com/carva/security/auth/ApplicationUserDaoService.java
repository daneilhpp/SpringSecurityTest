package com.carva.security.auth;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.carva.security.security.ApplicationUserRole.*;

@Repository("security")
public class ApplicationUserDaoService implements ApplicationUserDao{
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationUserDaoService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
        return getApplicationUsers().
                stream().
                filter(applicationUser -> username.equals(applicationUser.getUsername())).
                findFirst();
    }

    private List<ApplicationUser> getApplicationUsers()
    {
        List<ApplicationUser> applicationUsers = Lists.newArrayList(
            new ApplicationUser(
                    STUDENT.getGrantedAuthorities(),
                    passwordEncoder.encode("123456"),
                    "jobaisreal",
                    true,
                    true,
                    true,
                    true
            ),
            new ApplicationUser(
                    ADMIN.getGrantedAuthorities(),
                    passwordEncoder.encode("password"),
                    "gustavahr",
                    true,
                    true,
                    true,
                    true
            ),
            new ApplicationUser(
                    ADMINTRAINEE.getGrantedAuthorities(),
                    passwordEncoder.encode("121212"),
                    "daniel",
                    true,
                    true,
                    true,
                    true
            )
        );
        return applicationUsers;
    }
}
