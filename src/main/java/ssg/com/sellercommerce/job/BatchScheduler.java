package ssg.com.sellercommerce.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class BatchScheduler {
    private final JobLauncher jobLauncher;
    private final SettlementJobConfig config;

    /**
     * Batch 작업 스케쥴링을 수행하는 메소드입니다.
     */
    @Scheduled(cron = "0 0 0 * * *")
    public void runJob() {
        Map<String, JobParameter> confMap = new HashMap<>();
        confMap.put("time", new JobParameter(System.currentTimeMillis()));
        JobParameters jobParameters = new JobParameters(confMap);
        try {
            log.info("Batch job 을 실행합니다.");
            jobLauncher.run(config.settlementJob(), jobParameters);
            log.info("Batch job 을 완료하였습니다.");
        } catch (Exception e) {
            log.error("Batch 오류입니다.");
        }
    }
}
