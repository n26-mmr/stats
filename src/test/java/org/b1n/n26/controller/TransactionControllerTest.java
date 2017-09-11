package org.b1n.n26.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.b1n.n26.model.TransactionModel;
import org.b1n.n26.service.StorageService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;

import static org.b1n.n26.controller.TransactionController.URI_TRANSACTIONS;
import static org.b1n.n26.model.TransactionModel.MAX_TIME_TO_CONSIDER_IN_MILLIS;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private StorageService storage;

    @Autowired
    private ObjectMapper mapper;

    @Before
    public void setUp() {
        storage.reset();
    }

    private ResultActions runReq(Double amount) throws Exception {
        return runReq(amount, System.currentTimeMillis());
    }

    private ResultActions runReq(Double amount, Long timestamp) throws Exception {
        TransactionModel trans = new TransactionModel(amount, timestamp);
        String data = mapper.writeValueAsString(trans);
        RequestBuilder req = post(URI_TRANSACTIONS)
            .contentType(MediaType.APPLICATION_JSON)
            .content(data);
        return mockMvc.perform(req).andDo(print());
    }

    @Test
    public void canAddTransaction() throws Exception {
        runReq(1.23)
            .andExpect(status().isCreated());
    }

    @Test
    public void oldTransactionGets204() throws Exception {
        long timeInThePast = System.currentTimeMillis() - (MAX_TIME_TO_CONSIDER_IN_MILLIS * 2);
        runReq(1.23, timeInThePast)
            .andExpect(status().isNoContent());
    }
}
