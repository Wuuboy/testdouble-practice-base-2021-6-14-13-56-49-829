package com.tw.comprehensive;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TradingServiceTest {

    @Test
    void should_call_logNewTrade_when_createTrade() {
        //given
        AuditService auditService = spy(AuditService.class);
        TradeRepository tradeRepository = spy(TradeRepository.class);
        TradingService tradingService = new TradingService(tradeRepository, auditService);

        //when
        tradingService.createTrade(new Trade("test", "test"));

        //then
        verify(auditService, times(1)).logNewTrade(any());
    }

    @Test
    void should_return_value_equals_to_value_findById_when_findTrade() {
        //given
        AuditService auditService = mock(AuditService.class);
        TradeRepository tradeRepository = mock(TradeRepository.class);
        TradingService tradingService = new TradingService(tradeRepository, auditService);

        Trade expectedTrade = new Trade("test", "test");
        when(tradeRepository.findById(anyLong())).thenReturn(expectedTrade);

        //when
        Trade actualTrade = tradingService.findTrade(anyLong());

        //then
        assertEquals(expectedTrade, actualTrade);
    }


}