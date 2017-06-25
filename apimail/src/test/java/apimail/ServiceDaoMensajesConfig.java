package apimail;

import apimail.Dao.DaoMensajes;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

/**
 * Created by fefe on 24/6/2017.
 */
@Profile("test-serviceMenaje")
@Configuration
public class ServiceDaoMensajesConfig {

    @Bean
    @Primary
    public DaoMensajes daoMensajes()
    {
        return Mockito.mock(DaoMensajes.class);
    }
}
