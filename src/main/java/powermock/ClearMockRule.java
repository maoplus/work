package powermock;

import java.lang.reflect.Field;
import java.util.Set;
import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;
import org.powermock.core.MockRepository;
import org.powermock.reflect.Whitebox;

public class ClearMockRule implements MethodRule {
    
    @Override
    public Statement apply(Statement base, FrameworkMethod method, Object target) {
        
        return new ClearMockStatement(base, target);
    }
}

class ClearMockStatement extends Statement {
    private final Statement methodState;
    private final Object    mockTarget;
    
    public ClearMockStatement(Statement base, Object target) {
        methodState = base;
        this.mockTarget = target;
    }
    
    @Override
    public void evaluate() throws Throwable {
        try {
            methodState.evaluate();
        } finally {
            MockRepository.clear();
            clearMockFields(mockTarget);
        }
    }
    
    private void clearMockFields(Object target) throws Exception {
        if (null == target) {
            return;
        }
        Set<Field> allFields = Whitebox.getAllInstanceFields(target);
        for (Field field : allFields) {
            field.set(target, null);
        }
    }
}