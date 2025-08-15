package com.github.tsutomunakamura.spring_boot_batch_demo.tasklet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
public class TerminationTasklet implements Tasklet {

    private static final Logger log = LoggerFactory.getLogger(TerminationTasklet.class);

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        log.info("All batch processing completed successfully!");
        log.info("Performing final cleanup and terminating application...");
        
        // Optional: Add any cleanup logic here
        // For example: closing resources, sending notifications, etc.
        
        return RepeatStatus.FINISHED;
    }
}
