package powermock;

import static org.powermock.api.mockito.PowerMockito.when;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.powermock.api.support.membermodification.MemberModifier.suppress;
import static org.powermock.api.support.membermodification.MemberMatcher.method;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ Computer.class,Customer.class,Demo.class })
public class MockUtil {
    private static final String myvalue = "value";
    
    @Rule
    private ClearMockRule        rule    = new ClearMockRule();
    @Mock
    private Computer            c1;
    @InjectMocks
    Demo                        d1      = new Demo();
    
    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    Demo                        d2;
    @Before
    public void prepare() {
        when(c1.getName()).thenReturn("thinkpad", "macbook");
    }
    public static ArgumentMatcher<String> StringEqaulMatcher(final String value) {
        return new ArgumentMatcher<String>() {
            @Override
            public boolean matches(Object argument) {
                if (argument.equals(value)) {
                    return true;
                }
                return false;
            }
        };
    }
    
    // Invalid use of argument matchers!
    @Test
    public void matchTest() {
        when(c1.getName(Mockito.argThat(StringEqaulMatcher("mao")), Mockito.eq("abc")))
                .thenReturn("thinkpad");
        System.out.println(c1.getName("mao", "abc"));
    }
    @Test
    public void prtTest() {
        suppress(method(Computer.class, "prtParValue", String.class));
        Thinkpad p = new Thinkpad();
        p.prtChild2();
    }
    @Test
    public void shouldSetTheNameWhenNewCustomerIsCreated() {
      /** Suppressing the constructor of LegacyCustomer class
          which takes one Argument of String type */
        PowerMockito.suppress(PowerMockito.constructor(LegacyCustomer.class, String.class));
        Customer c = new Customer("abc","123");
    }
    
 
}
