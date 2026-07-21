package br.com.fintrackerhub.identify.user.services;

import br.com.fintrackerhub.exception.business.BusinessException;
import br.com.fintrackerhub.identify.user.entity.User;
import br.com.fintrackerhub.identify.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    public User register(String email, String password) {
        if (userRepository.existsByEmail(email)) {
            throw new BusinessException("Email já cadastrado");
        }
        String passwordHash = passwordEncoder.encode(password);
        User userNew = User.create(email, passwordHash);

        return userRepository.save(userNew);
    }

    public boolean validatePassword(String password, String passwordHash) {
        return passwordEncoder.matches(password, passwordHash);
    }
}
