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

    // Additional test case 1: Addition with carry
    @Test
    public void addWithCarry() throws Exception {
        this.mvc.perform(get("/add").param("operand1", "11111111").param("operand2", "11111"))
                .andExpect(status().isOk())
                .andExpect(content().string("100011110"));
    }
    @Test
    public void addWithCarry2() throws Exception {
        this.mvc.perform(get("/add_json").param("operand1", "11111111").param("operand2", "11111"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(11111111))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(11111))
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(100011110L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("add"));
    }
    // Additional test case 2: Addition with single bit operands
    @Test
    public void addWithSingleBitOperands() throws Exception {
        this.mvc.perform(get("/add").param("operand1", "1").param("operand2", "1"))
                .andExpect(status().isOk())
                .andExpect(content().string("10"));
    }
    @Test
    public void addWithSingleBitOperands2() throws Exception {
        this.mvc.perform(get("/add_json").param("operand1", "1").param("operand2", "1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(10))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("add"));
    }
    //
    // Additional test case 3: Large binary numbers addition
    @Test
    public void addLargeBinary() throws Exception {
        this.mvc.perform(get("/add").param("operand1", "1010101010101010").param("operand2", "1111111111111111"))
                .andExpect(status().isOk())
                .andExpect(content().string("11010101010101001"));
    }
    @Test
    public void addLargeBinary2() throws Exception {
        this.mvc.perform(get("/add_json").param("operand1", "1010101010101010").param("operand2", "1111111111111111"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(1010101010101010L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(1111111111111111L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(11010101010101001L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("add"));
    }

    // Test #1 for AND operation
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
    // Test #2 for AND operation
    @Test
    public void andSecondTest() throws Exception {
        this.mvc.perform(get("/and").param("operand1", "101010101").param("operand2", "111000"))
                .andExpect(status().isOk())
                .andExpect(content().string("10000"));
    }

    @Test
    public void andSecondTest2() throws Exception {
        this.mvc.perform(get("/and_json").param("operand1", "101010101").param("operand2", "111000"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(101010101))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(111000))
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(10000))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("and"));
    }
    // Test #1 for OR operation
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
    // Test #2 for OR operation
    @Test
    public void orSecondTest() throws Exception {
        this.mvc.perform(get("/or").param("operand1", "111110").param("operand2", "000000"))
                .andExpect(status().isOk())
                .andExpect(content().string("111110"));
    }

    @Test
    public void orSecondTest2() throws Exception {
        this.mvc.perform(get("/or_json").param("operand1", "111110").param("operand2", "000000"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(111110))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(0))
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(111110))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("or"));
    }
    // Test #1 for multiplication operation
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
    // Test #2 for multiplication operation
    @Test
    public void multiplySecondTest() throws Exception {
        this.mvc.perform(get("/multiply").param("operand1", "1").param("operand2", "1001"))
                .andExpect(status().isOk())
                .andExpect(content().string("1001"));
    }

    @Test
    public void multiplySecondTest2() throws Exception {
        this.mvc.perform(get("/multiply_json").param("operand1", "1").param("operand2", "1001"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(1001))
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(1001))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("multiply"));
    }
}
