package com.demo.demo.conf;

import com.demo.demo.csv.CsvOrderReader;
import com.demo.demo.dto.Order;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.TaskExecutorJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;


@Configuration
public class BatchConfiguration {
//    @Autowired
//    private JobRepository jobRepository;
    @Bean
    public JobLauncher jobLauncher() throws Exception {
        TaskExecutorJobLauncher jobLauncher = new TaskExecutorJobLauncher();
        jobLauncher.setJobRepository(jobRepository());
        jobLauncher.setTaskExecutor(new SimpleAsyncTaskExecutor());
        jobLauncher.afterPropertiesSet();
        return jobLauncher;
    }
//    @Autowired
//    private DataSource dataSource;

    @Bean
    public JobRepository jobRepository() throws Exception {
        JobRepositoryFactoryBean factory = new JobRepositoryFactoryBean();
//        factory.setDataSource(new DriverManagerDataSource());
//        factory.setTransactionManager(transactionManager());
//        factory.setIsolationLevelForCreate("ISOLATION_REPEATABLE_READ");
//        factory.setTablePrefix("BATCH_");
//        factory.setMaxVarCharLength(1000);
        return factory.getObject();
    }
    @Bean
    public PlatformTransactionManager transactionManager() {
        return new ResourcelessTransactionManager();
    }
//    @Bean
//    public Step step(){
//        return stepBuilderFactory.get("step")
//                .<Order, Order>chunk(10)
//                .reader(reader())
//                .build();
//    }
}
