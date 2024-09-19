package eprocument.event.user;

import ifmis.framework.common.audit.Audit;
import ifmis.framework.persistence.service.AbstractEventProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Audit
@Service
public class AuthEventProcessor extends AbstractEventProcessor {
}
