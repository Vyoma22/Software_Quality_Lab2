package com.ontariotechu.sofe3980U;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.junit.runner.RunWith;

import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.test.context.junit4.*;

import static org.hamcrest.Matchers.containsString;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@RunWith(SpringRunner.class)
@WebMvcTest(BinaryAPIController.class)
public class BinaryAPIControllerTest {

    @Autowired
    private MockMvc mvc;

    // Test for addition
    @Test
    public void add() throws Exception {
        this.mvc.perform(get("/add").param("operand1", "111").param("operand2", "1010"))
                .andExpect(status().isOk())
                .andExpect(content().string("10001"));
    }

    @Test
    public void add2() throws Exception {
        this.mvc.perform(get("/add_json").param("operand1", "111").param("operand2", "1010"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(111))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(1010))
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(10001))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("add"));
    }

    // Additional test case 1: Addition with an empty operand
    @Test
    public void addWithEmptyOperand() throws Exception {
        this.mvc.perform(get("/add").param("operand1", "111").param("operand2", ""))
                .andExpect(status().isOk())
                .andExpect(content().string("111"));
    }

    // Additional test case 2: Addition with single bit operands
    @Test
    public void addWithSingleBitOperands() throws Exception {
        this.mvc.perform(get("/add").param("operand1", "1").param("operand2", "1"))
                .andExpect(status().isOk())
                .andExpect(content().string("10"));
    }

    // Additional test case 3: Large binary numbers addition in JSON API
    @Test
    public void addLargeBinaryJSON() throws Exception {
        this.mvc.perform(get("/add_json").param("operand1", "1010101010101010").param("operand2", "1111111111111111"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(1010101010101010L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(1111111111111111L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(11010101010101001L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("add"));
    }

    // Test for AND operation
    @Test
    public void and() throws Exception {
        this.mvc.perform(get("/and").param("operand1", "111").param("operand2", "1010"))
                .andExpect(status().isOk())
                .andExpect(content().string("10"));
    }

    @Test
    public void and2() throws Exception {
        this.mvc.perform(get("/and_json").param("operand1", "111").param("operand2", "1010"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(111))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(1010))
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(10))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("and"));
    }

    // Test for OR operation
    @Test
    public void or() throws Exception {
        this.mvc.perform(get("/or").param("operand1", "111").param("operand2", "1010"))
                .andExpect(status().isOk())
                .andExpect(content().string("1111"));
    }

    @Test
    public void or2() throws Exception {
        this.mvc.perform(get("/or_json").param("operand1", "111").param("operand2", "1010"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(111))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(1010))
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(1111))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("or"));
    }

    // Test for multiplication operation
    @Test
    public void multiply() throws Exception {
        this.mvc.perform(get("/multiply").param("operand1", "111").param("operand2", "1010"))
                .andExpect(status().isOk())
                .andExpect(content().string("1000110"));
    }

    @Test
    public void multiply2() throws Exception {
        this.mvc.perform(get("/multiply_json").param("operand1", "111").param("operand2", "1010"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(111))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(1010))
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(1000110))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("multiply"));
    }
}
