package ru.itis.javalab.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;
import ru.itis.javalab.repositories.*;
import ru.itis.javalab.services.*;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:db.properties")
@ComponentScan(basePackages = "ru.itis.javalab")
public class ApplicationConfig {

    @Autowired
    private Environment environment;

    @Bean
    public DataSource dataSource() {

        return new HikariDataSource(hikariConfig());
    }

    @Bean
    public TeamsRepository teamsRepository() {

        return new TeamsRepositoryImpl(dataSource());
    }

    @Bean
    public TeamsService teamsService() {

        return new TeamsServiceImpl(teamsRepository());
    }

    @Bean
    public PlayersRepository playerRepository() {

        return new PlayersRepositoryImpl(dataSource());
    }

    @Bean
    public PlayerService playerService() {

        return new PlayerServiceImpl(playerRepository());
    }

    @Bean
    public LeaguesRepository leaguesRepository() {

        return new LeaguesRepositoryImpl(dataSource());
    }

    @Bean
    public LeagueService leagueService() {

        return new LeagueServiceImpl(leaguesRepository());
    }

    @Bean
    public HikariConfig hikariConfig() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(environment.getProperty("db.url"));
        hikariConfig.setMaximumPoolSize(Integer.parseInt(environment.getProperty("db.hikari.max-pool-size")));
        hikariConfig.setUsername(environment.getProperty("db.username"));
        hikariConfig.setPassword(environment.getProperty("db.password"));
        hikariConfig.setDriverClassName(environment.getProperty("db.driver.classname"));
        return hikariConfig;
    }

    @Bean
    public FreeMarkerViewResolver freemarkerViewResolver() {

        FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
        resolver.setPrefix("");
        resolver.setSuffix(".ftlh");
        resolver.setContentType("text/html;charset=UTF-8");

        return resolver;
    }

    @Bean
    public FreeMarkerConfigurer freemarkerConfig() {

        FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
        configurer.setTemplateLoaderPath("/templates/");

        return configurer;
    }
}