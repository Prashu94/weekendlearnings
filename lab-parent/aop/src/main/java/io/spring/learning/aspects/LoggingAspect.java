package io.spring.learning.aspects;

import com.jamonapi.MonitorFactory;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    public final static String BEFORE = "'BEFORE'";
    public final static String AROUND = "'AROUND'";

    private Logger LOGGER = LoggerFactory.getLogger(getClass());
    private MonitorFactory monitorFactory;



}
