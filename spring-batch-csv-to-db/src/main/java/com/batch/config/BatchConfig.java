package com.batch.config;

import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.parameters.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.Step;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.infrastructure.item.data.RepositoryItemWriter;
import org.springframework.batch.infrastructure.item.file.FlatFileItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.batch.listener.JobListener;
import com.batch.model.User;

/**
 * Main Batch Configuration
 * 
 * This class defines:
 * - Step (processing logic)
 * - Job (execution flow)
 */
@Configuration
public class BatchConfig {

    /**
     * Step:
     * Defines how data flows from Reader → Writer
     */
    @Bean
    public Step step1(JobRepository jobRepository,
                      PlatformTransactionManager transactionManager,
                      FlatFileItemReader<User> reader,
                      RepositoryItemWriter<User> writer) {

        return new StepBuilder("step1", jobRepository)

                // Process data in chunks (batch size = 3)
                .<User, User>chunk(3, transactionManager)

                // Read data from CSV
                .reader(reader)

                // Write data to DB
                .writer(writer)

                .build();
    }

    /**
     * Job:
     * Defines overall batch execution
     */
    @Bean
    public Job job1(JobRepository jobRepository,
                    Step step1,
                    JobListener listener) {

        return new JobBuilder("job1", jobRepository)

                // Allows multiple runs of the same job
                .incrementer(new RunIdIncrementer())

                // Attach listener (for logging start/end)
                .listener(listener)

                // Start with step1
                .start(step1)

                .build();
    }
}