package br.com.fintrackerhub;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import br.com.fintrackerhub.identify.user.entity.User;
import br.com.fintrackerhub.identify.user.repository.UserRepository;
import br.com.fintrackerhub.identify.user.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private final String email = "test@fintrackerhub.com";
    private final String rawPassword = "SecurePassword123";
    private final String hashedPassword = "$2a$10$eImiTxuWV5j78RF5EXw83e.2K3dH3b1a2c3d4e5f6g7h8i9j0k1l2";

    @Test
    void shouldRegisterUserAndHashPasswordSuccessfully() {
        // Arrange
        User mockUser = User.create(email, hashedPassword);

        when(userRepository.existsByEmail(email)).thenReturn(false);
        when(passwordEncoder.encode(rawPassword)).thenReturn(hashedPassword);
        when(userRepository.save(any(User.class))).thenReturn(mockUser);

        // Act
        User savedUser = userService.register(email, rawPassword);

        // Assert
        assertNotNull(savedUser);
        verify(userRepository, times(1)).existsByEmail(email);
        verify(passwordEncoder, times(1)).encode(rawPassword);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void shouldValidateCorrectPasswordSuccessfully() {
        // Arrange
        when(passwordEncoder.matches(rawPassword, hashedPassword)).thenReturn(true);

        // Act
        boolean isValid = userService.validatePassword(rawPassword, hashedPassword);

        // Assert
        assertTrue(isValid);
        verify(passwordEncoder, times(1)).matches(rawPassword, hashedPassword);
    }

    @Test
    void shouldReturnFalseWhenPasswordIsIncorrect() {
        // Arrange
        String wrongPassword = "WrongPassword123";
        when(passwordEncoder.matches(wrongPassword, hashedPassword)).thenReturn(false);

        // Act
        boolean isValid = userService.validatePassword(wrongPassword, hashedPassword);

        // Assert
        assertFalse(isValid);
        verify(passwordEncoder, times(1)).matches(wrongPassword, hashedPassword);
    }
}