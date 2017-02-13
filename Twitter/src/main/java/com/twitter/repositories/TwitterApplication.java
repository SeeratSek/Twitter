package com.twitter.repositories;

import com.github.javafaker.Faker;
import com.twitter.models.Pie;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SpringBootApplication
public class TwitterApplication {
    private final Faker faker = new Faker();

	public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/twitterbase";
        String username = "root";
        String password = "Hammer_2";

        System.out.println("Connecting database...");

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Database connected!");
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
		SpringApplication.run(TwitterApplication.class, args);
	}

    @Bean
    public CommandLineRunner initializeDb(PieRepository repository){
        return (args) -> {
            repository.deleteAll();
            //Insert some random pies
            for(int i = 0; i < 1; i++) {
                repository.save(new Pie(faker.lorem().word(), faker.lorem().sentence()));
            }
        };
    }

}
