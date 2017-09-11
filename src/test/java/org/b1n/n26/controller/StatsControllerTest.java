package org.b1n.n26.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.b1n.n26.model.StatsModel;
import org.b1n.n26.model.TransactionModel;
import org.b1n.n26.service.StorageService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.b1n.n26.controller.StatsController.URI_STATS;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class StatsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private StorageService storage;

    @Before
    public void setUp() {
        storage.reset();
    }

    @Test
    public void canGetStats() throws Exception {
        String res = mapper.writeValueAsString(new StatsModel(0d, 0d, 0d, 0d, 0L));
        mockMvc.perform(get(URI_STATS))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(res));
    }

    private void addTrans(Double amount) {
        storage.add(new TransactionModel(amount, System.currentTimeMillis()));
    }

    @Test
    public void canAggregate() throws Exception {
        Double[] values = {1.0, 2.0, 3.0};
        Double sum = 6d;
        Double avg = 2d;
        Double max = 3d;
        Double min = 1d;
        Long count = 3L;
        for (Double v : values) {
            addTrans(v);
        }
        String res = mapper.writeValueAsString(new StatsModel(sum, avg, max, min, count));
        mockMvc.perform(get(URI_STATS))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(res));
    }
}
