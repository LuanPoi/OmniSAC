package dev.luanpoi.omnisacbackend.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class SpringSecurityAuditorAware implements AuditorAware<String> {

    public static final String SYSTEM_USERNAME = "system";

    public SpringSecurityAuditorAware() {
    }

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of(SYSTEM_USERNAME);
    }
}
