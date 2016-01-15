package Annotation;

import java.lang.reflect.Method;
import Annotation.ExceptionCatch;
import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;
import org.junit.Assert;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CatchExceptionRule implements MethodRule {
    @Override
    public Statement apply(Statement base, FrameworkMethod method, Object target) {
        return new InvokeStatement(base, target, method.getMethod());
    }
}

class InvokeStatement extends Statement {
    private final Statement methodState;
    private final Object    mockTarget;
    private final Method    method;
    
    public InvokeStatement(Statement base, Object target, Method method) {
        methodState = base;
        this.mockTarget = target;
        this.method = method;
    }
    
    @Override
    public void evaluate() throws Throwable {
        boolean hasExpectException = hasExpectedException(method);
        try {
            methodState.evaluate();
            // if true ,should return an exception
            if (hasExpectException) {
                fail("Should return an exception, but no.");
            }
        } catch (Exception e) {
            String actualErrorMessage = e.getMessage();
            if (hasExpectException) {
                ExceptionCatch exceptException = method
                        .getDeclaredAnnotation(ExceptionCatch.class);
                Class<? extends Exception> expectException = exceptException
                        .value();
                String exceptClassName = expectException.getSimpleName();
                String actualExceptionClassName = e.getClass().getSimpleName();
                String expectMessage = exceptException.message();
                assertEquals(String.format("unexpected %s happened",
                        actualExceptionClassName), exceptClassName,
                        actualExceptionClassName);
                assertTrue(
                        String.format(
                                "Actual exception: '%s' does not contains except message: %s ",
                                actualErrorMessage, expectMessage),
                        actualErrorMessage.contains(expectMessage));
            }
            else {
                e.printStackTrace();
                fail(String.format("unexpected exception %s happened",
                        e.getMessage()));
            }
        }
    }
    
    private boolean hasExpectedException(Method method) {
        return method.isAnnotationPresent(ExceptionCatch.class);
    }
    
}