package com.batch.reader;

import org.springframework.batch.infrastructure.item.file.FlatFileItemReader;
import org.springframework.batch.infrastructure.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.batch.model.User;

/**
 * Reader Configuration
 * 
 * This class is responsible for reading data from a CSV file
 * and converting each row into a User object.
 */
@Configuration
public class UserReader {

    /**
     * Creates a FlatFileItemReader bean
     * 
     * Flow:
     * CSV file → Line → Map to User object
     */
    @Bean
    public FlatFileItemReader<User> reader() {
        return new FlatFileItemReaderBuilder<User>()
                .name("file-reader") // Unique reader name

                // CSV file location inside resources folder
                .resource(new ClassPathResource("User_Info.csv"))

                // Skip header row (VERY IMPORTANT)
                .linesToSkip(1)

                // Read CSV as delimited (comma-separated)
                .delimited()

                // Map CSV columns → User fields
                .names("id", "name", "email")

                // Target object type
                .targetType(User.class)

                .build();
    }
}