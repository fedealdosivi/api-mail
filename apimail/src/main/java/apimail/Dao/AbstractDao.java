package apimail.Dao;

import org.springframework.stereotype.Repository;

import java.sql.Connection;

/**
 * Created by fefe on 24/7/2017.
 */
@Repository
public abstract class AbstractDao {

        protected Connection connection;

        public AbstractDao(Connection connection){
            this.connection = connection;
        }
}
