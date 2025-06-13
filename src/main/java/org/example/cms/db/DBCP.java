package org.example.cms.db;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.apache.commons.dbcp2.BasicDataSource;
@WebListener
public class DBCP implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/cms");
        dataSource.setUsername("root");
        dataSource.setPassword("PHW#84#jeor");
        dataSource.setInitialSize(50);
        dataSource.setMaxTotal(100);

        ServletContext servletContext = sce.getServletContext();
        servletContext.setAttribute("ds", dataSource);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            BasicDataSource ds = (BasicDataSource) sce.getServletContext().getAttribute("dataSource");
            ds.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
