package ssg.com.sellercommerce.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ssg.com.sellercommerce.service.SettlementService;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Slf4j
@Configuration
@RequiredArgsConstructor
@EnableBatchProcessing
public class SettlementJobConfig {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    private final SettlementService settlementService;

    @Bean
    public Job settlementJob() {
        return jobBuilderFactory.get("settlementJob")
                .start(settlementStep1())
                .build();
    }

    /**
     * Batch Job 을 구성하는 step 으로, 현재 시각과 하루 전 까지
     * 생성된 Click 광고과금을 정산합니다.
     */
    @Bean
    public Step settlementStep1() {
        return stepBuilderFactory.get("settlementStep1")
                .tasklet(((contribution, chunkContext) -> {
                    settlementService.processSettlements(LocalDateTime.now().minusDays(1), LocalDateTime.now());
                    return RepeatStatus.FINISHED;
                }))
                .build();
    }
}
