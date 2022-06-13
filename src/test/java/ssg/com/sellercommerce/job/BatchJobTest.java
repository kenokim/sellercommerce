package ssg.com.sellercommerce.job;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.batch.core.*;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.JobRepositoryTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import ssg.com.sellercommerce.domain.Commercial;
import ssg.com.sellercommerce.domain.CommercialBilling;
import ssg.com.sellercommerce.domain.Company;
import ssg.com.sellercommerce.domain.Item;
import ssg.com.sellercommerce.repository.*;
import ssg.com.sellercommerce.service.CompanyService;
import ssg.com.sellercommerce.service.SettlementService;

import javax.persistence.AssociationOverride;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBatchTest
@SpringBootTest
@EnableAutoConfiguration
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class})
class BatchJobTest {
    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Autowired
    private JobRepositoryTestUtils jobRepositoryTestUtils;

    @Autowired private SettlementService settlementService;

    @Autowired private SettlementJobConfig settlementJobConfig;
    @Autowired private ItemRepository itemRepository;
    @Autowired private CompanyRepository companyRepository;
    @Autowired private CommercialRepository commercialRepository;

    @Autowired private SettlementRepository settlementRepository;

    @Autowired private CommercialBillingRepository commercialBillingRepository;

    @MockBean private CompanyService companyService;
    private Commercial commercial;

    private Item item;
    private Company company;

    @BeforeEach
    @Transactional
    public void setup() {
        item = Item.createItem("신세계몰", "수박", 10000, 1);
        itemRepository.save(item);

        company = Company.create("신세계몰", 1000000000L, 102345678L, "hello");
        companyRepository.save(company);

        commercial = Commercial.create(company, item, 1000);
        commercialRepository.save(commercial);

    }
    @After
    public void cleanUp() {
        jobRepositoryTestUtils.removeJobExecutions();
    }

    private JobParameters defaultJobParameters() {
        JobParametersBuilder paramsBuilder = new JobParametersBuilder();
        paramsBuilder.addParameter("time", new JobParameter(System.currentTimeMillis()));
        return paramsBuilder.toJobParameters();
    }

    @Test
    void batchJobSuccess() throws Exception {
        JobExecution jobExecution = jobLauncherTestUtils.launchStep("settlementStep1", defaultJobParameters());
        Collection actualStepExecutions = jobExecution.getStepExecutions();
        ExitStatus actualExitStatus = jobExecution.getExitStatus();
        assertThat(settlementService.findAll().size()).isEqualTo(1);
    }
}