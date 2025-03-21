package com.example.dio.security.config;

import com.example.dio.security.util.UserIdentity;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;


@Configuration
public class AuditorAwareImpl implements AuditorAware<String> {

    private  final UserIdentity userIdentity;

    public AuditorAwareImpl(UserIdentity userIdentity) {
        System.err.println("creating auditorawarebean");
        this.userIdentity = userIdentity;
    }

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of(userIdentity.getCurrentUserEmail());
    }
}
