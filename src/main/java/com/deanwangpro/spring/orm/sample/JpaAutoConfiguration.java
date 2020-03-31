package com.deanwangpro.spring.orm.sample;

import org.eclipse.persistence.config.PersistenceUnitProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.orm.jpa.JpaBaseConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.persistence.ValidationMode;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by fdlessard on 17-03-02.
 */
@Configuration
@ConditionalOnClass({DataSource.class})
public class JpaAutoConfiguration extends JpaBaseConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(JpaAutoConfiguration.class);

    protected JpaAutoConfiguration(DataSource dataSource, JpaProperties properties, ObjectProvider<JtaTransactionManager> jtaTransactionManager) {
        super(dataSource, properties, jtaTransactionManager);
    }

    @Override
    protected AbstractJpaVendorAdapter createJpaVendorAdapter() {
        LOGGER.info("PersistenceConfiguration.createJpaVendorAdapter()");
        EclipseLinkJpaVendorAdapter jpaVendorAdapter = new EclipseLinkJpaVendorAdapter();
        jpaVendorAdapter.setShowSql(true);
        jpaVendorAdapter.setGenerateDdl(true);
        jpaVendorAdapter.setDatabase(Database.MYSQL);
        return jpaVendorAdapter;
    }

    @Override
    protected Map<String, Object> getVendorProperties() {
        LOGGER.info("PersistenceConfiguration.getVendorProperties()");
        HashMap<String, Object> vendorProperties = new HashMap<>();
        vendorProperties.put(PersistenceUnitProperties.WEAVING, detectWeavingMode());
        vendorProperties.put(PersistenceUnitProperties.VALIDATION_MODE, ValidationMode.NONE.toString());
        vendorProperties.put(PersistenceUnitProperties.DDL_GENERATION, "drop-and-create-tables");
        return vendorProperties;
    }

    private String detectWeavingMode() {
        LOGGER.info("PersistenceConfiguration.detectWeavingMode()");
        return InstrumentationLoadTimeWeaver.isInstrumentationAvailable() ? "true" : "static";
    }
}
