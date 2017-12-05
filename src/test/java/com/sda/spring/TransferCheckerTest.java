package com.sda.spring;

import com.sda.spring.entity.TransferHistory;
import com.sda.spring.repository.SuspiciousPersonRepository;
import com.sda.spring.service.TransferHistoryService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class TransferCheckerTest {

    private TransferChecker transferChecker;

    @Mock
    private SuspiciousPersonRepository mockedSuspiciousPersonRepository;

    @Mock
    private TransferHistoryService mockedTransferHistoryService;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        transferChecker = new TransferChecker(mockedSuspiciousPersonRepository, mockedTransferHistoryService);
    }

    @Test
    public void shouldReturnBiggestTransaction() throws Exception {

        when(mockedTransferHistoryService.getTransferHistoryForSpecificAccount("123")).thenReturn(getTransferHistoriesList());

        BigDecimal result = transferChecker.getBiggestTransferFromLast12Moths("123");

        assertThat(result).isEqualTo(new BigDecimal("855.22"));
        verify(mockedTransferHistoryService, times(1)).getTransferHistoryForSpecificAccount("123");
    }

    private List<TransferHistory> getTransferHistoriesList() {
        List<TransferHistory> histories = new LinkedList<>();
        histories.add(new TransferHistory("123", "456", "money",
                LocalDateTime.of(2017, 11, 28, 22, 33), new BigDecimal("355.00")));
        histories.add(new TransferHistory("123", "456", "bucks",
                LocalDateTime.of(2017, 10, 28, 22, 33), new BigDecimal("855.22")));
        histories.add(new TransferHistory("123", "456", "rent",
                LocalDateTime.of(2017, 11, 28, 22, 33), new BigDecimal("145.37")));
        return histories;
    }
}
