package com.demo.demo.conf;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.file.dsl.Files;
import org.springframework.integration.file.transformer.FileToStringTransformer;
import org.springframework.messaging.MessageChannel;

import java.io.File;

@Configuration
@EnableIntegration
public class FileWatcherConfig {

    @Bean
    public MessageChannel fileOutputChannel() {
        return new DirectChannel();
    }
    @Bean
    public IntegrationFlow fileIntegrationFlow() {
        File file = new File("export_worklog.csv");
        return IntegrationFlows.from(Files.inboundAdapter(file))
                .transform(fileMessageTransformer())
                .channel(inputChannel())
                .get();
    }

    @Bean
    public MessageChannel inputChannel() {
        return new DirectChannel();
    }

    @Bean
    public FileToStringTransformer fileMessageTransformer() {
        return new FileToStringTransformer();
    }

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job job;

    @ServiceActivator(inputChannel = "inputChannel")
    public void launchJob(String fileContent) throws Exception {
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("fileContent", fileContent)
                .toJobParameters();

        jobLauncher.run(job, jobParameters);
    }
}
