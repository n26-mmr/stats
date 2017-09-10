package org.b1n.n26.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.b1n.n26.model.TransactionModel;
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

import static org.b1n.n26.controller.TransactionController.URI_TRANSACTIONS;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    private TransactionModel validTrans;

    @Before
    public void setUp() {
        Long timestamp = 123456789L;
        Double amount = 42.51d;
        this.validTrans = new TransactionModel(timestamp, amount);
    }

    @Test
    public void canAddTransaction() throws Exception {
        String json = mapper.writeValueAsString(this.validTrans);

        RequestBuilder req = post(URI_TRANSACTIONS).contentType(MediaType.APPLICATION_JSON).content(json);
        mockMvc.perform(req).andExpect(status().isCreated());
    }
}
