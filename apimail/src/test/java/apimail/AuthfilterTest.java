package apimail;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by fefe on 27/6/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Main.class})
@WebAppConfiguration
public class AuthfilterTest extends TestCase{

    @Mock
    private HttpServletResponse httpResponse;
    @Mock
    private HttpServletRequest httpRequest;
    @Mock
    private FilterChain filterChain;

    @Autowired
    AuthFilter authFilter;


    @Test
    public void doFilterTest() throws Exception {
        this.authFilter.doFilterInternal(httpRequest, httpResponse, filterChain);
    }
}
