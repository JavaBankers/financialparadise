package com.sda.spring;

import com.sda.spring.entity.Account;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnitParamsRunner.class)
public class MoneyTransferServiceTest {

    private MoneyTransferService testedObject;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        testedObject = new MoneyTransferService();
    }

    private Object[][] parametersForSpecificTransfers(){
        return new Object[][]{
            {new BigDecimal("1000"),new BigDecimal("500"),new BigDecimal("500"),new BigDecimal("500"),new BigDecimal("1000")},
            {new BigDecimal("50.87"),new BigDecimal("180.98"),new BigDecimal("20.69"),new BigDecimal("30.18"),new BigDecimal("201.67")},
            {new BigDecimal("0.00"),new BigDecimal("150.00"),new BigDecimal("0.00"),new BigDecimal("0.00"),new BigDecimal("150.00")},
//            {new BigDecimal(""),new BigDecimal(""),new BigDecimal(""),new BigDecimal(""),new BigDecimal("")},
//            {new BigDecimal(""),new BigDecimal(""),new BigDecimal(""),new BigDecimal(""),new BigDecimal("")},
//            {new BigDecimal(""),new BigDecimal(""),new BigDecimal(""),new BigDecimal(""),new BigDecimal("")},
        };
    }


    @Test
    @Parameters(method = "parametersForSpecificTransfers")
    public void shouldTransferMoney(BigDecimal accountOneBalance, BigDecimal accountTwoBalance, BigDecimal moneyToTranfer,
                                    BigDecimal expectedAccountOneBalance, BigDecimal expectedAccountTwoBalance) throws Exception {

        Account accountOne = new Account();
        Account accountTwo = new Account();

        accountOne.setId(1);
        accountTwo.setId(2);
        accountOne.setBalance(accountOneBalance);
        accountTwo.setBalance(accountTwoBalance);

        testedObject.transferMoney(accountOne, accountTwo, moneyToTranfer);

        assertThat(accountOne.getBalance()).isEqualTo(expectedAccountOneBalance);
        assertThat(accountTwo.getBalance()).isEqualTo(expectedAccountTwoBalance);
    }

    @Test
    public void shouldThrowInsufficientFundsExceptionWhenMoneyToTransferBiggerThanAcountOneBalance() throws Exception {

        thrown.expect(InsufficientFundsException.class);

        Account accountOne = new Account();
        accountOne.setId(1);
        accountOne.setBalance(new BigDecimal("300.00"));
        Account accountTwo = new Account();
        accountTwo.setId(2);
        accountTwo.setBalance(new BigDecimal("800.00"));

        testedObject.transferMoney(accountOne, accountTwo, new BigDecimal("305.00"));
    }
}
