package com.cahue.config;

import com.cahue.persistence.DataSource;
import com.google.appengine.api.utils.SystemProperty;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

/**
 * Date: 17.12.14
 *
 * @author francesco
 */
public class TestDataSource implements DataSource {

    private EntityManagerFactory mysqlFactory;

    public TestDataSource() {
        mysqlFactory = Persistence.createEntityManagerFactory("test-mysql");
    }

    @Override
    public EntityManager createDatastoreEntityManager() {
        throw new UnsupportedOperationException("Not implemented for tests.");
    }

    @Override
    public EntityManager createRelationalEntityManager() {
        return mysqlFactory.createEntityManager();
    }

}