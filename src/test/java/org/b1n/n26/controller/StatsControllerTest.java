package org.b1n.n26.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.b1n.n26.controller.StatsController.URI_STATS;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class StatsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldGetStats() throws Exception {
        mockMvc.perform(get(URI_STATS)).andDo(print());
    }
}
