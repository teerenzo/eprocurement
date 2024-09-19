package eprocument.test.application;

import ifmis.framework.common.trace.RequestContext;
import ifmis.framework.common.trace.RequestContextHolder;
import ifmis.framework.core.trace.TraceAuditContextService;
import ifmis.framework.test.AbstractPersistenceTestCase;
import ifmis.framework.test.TestDataSetup;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Locale;
//
@ExtendWith(TestDataSetup.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(classes = EprocumentTestApplication.class)
public abstract class AbstractEProcurementTest extends AbstractPersistenceTestCase  {

    @Autowired
    private TraceAuditContextService traceService;

    @BeforeEach
    public void initialize() {
        RequestContext context = RequestContextHolder.getContext();
        context.setLocale(new Locale("en_us"));
    }

    @AfterEach
    public void after() {
        traceService.publish();
    }

}
