package com.batch.writer;

import org.springframework.batch.infrastructure.item.data.RepositoryItemWriter;
import org.springframework.batch.infrastructure.item.data.builder.RepositoryItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.batch.model.User;
import com.batch.repository.IUserRepository;

/**
 * Writer Configuration
 * 
 * This class writes processed User objects into the database
 * using Spring Data JPA repository.
 */
@Configuration
public class UserWriter {

    /**
     * Creates a RepositoryItemWriter bean
     * 
     * Flow:
     * User object → save() → Database
     */
    @Bean
    public RepositoryItemWriter<User> writer(IUserRepository userRepo) {
        return new RepositoryItemWriterBuilder<User>()
                
                // Inject repository to perform DB operations
                .repository(userRepo)

                // Method to call for each item
                .methodName("save")

                .build();
    }
}