package com.fbaron.controller;

import com.fbaron.config.DatabaseConfig;
import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.flywaydb.core.Flyway;

/**
 * @author Ferney Estupinan Baron
 */
@WebListener
public class FlywayMigrationListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        HikariDataSource dataSource = DatabaseConfig.getDataSource();

        Flyway flyway = Flyway.configure()
                .dataSource(dataSource)
                .locations("classpath:db/migration")
                .load();

        try {
            flyway.migrate();
        } catch (Exception e) {
            System.err.println("There was an error with Flyway migration: " + e.getMessage());
        }

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            AbandonedConnectionCleanupThread.checkedShutdown();
            DatabaseConfig.shutdown();
        } catch (Exception e) {
            System.err.println("There was an error with shutdown: " + e.getMessage());
        }
    }

}
