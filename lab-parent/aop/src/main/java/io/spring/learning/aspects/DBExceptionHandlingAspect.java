package io.spring.learning.aspects;

import io.spring.learning.exception.RewardDataAccessException;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DBExceptionHandlingAspect {

    public static final String EMAIL_FAILURE_MSG = "Failed sending email to Mr.Smith ";

    private Logger LOGGER = LoggerFactory.getLogger(getClass());

    @AfterThrowing(value = "execution(public * io.spring.learning.repository.*.*(..))",
            throwing = "ex")
    public void implExceptionHandling(RewardDataAccessException ex) {
        LOGGER.warn(EMAIL_FAILURE_MSG + ex + "\n");
    }
}
