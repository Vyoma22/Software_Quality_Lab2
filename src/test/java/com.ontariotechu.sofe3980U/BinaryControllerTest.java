package com.ontariotechu.sofe3980U;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
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
@WebMvcTest(BinaryController.class)
public class BinaryControllerTest {

    @Autowired
    private MockMvc mvc;
    @Test
    public void getDefault() throws Exception {
        this.mvc.perform(get("/"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("calculator"))
			.andExpect(model().attribute("operand1", ""))
			.andExpect(model().attribute("operand1Focused", false));
    }
    @Test
    public void getParameter() throws Exception {
        this.mvc.perform(get("/").param("operand1","111"))
            .andExpect(status().isOk())
            .andExpect(view().name("calculator"))
			.andExpect(model().attribute("operand1", "111"))
			.andExpect(model().attribute("operand1Focused", true));
    }
	@Test
	    public void postParameter() throws Exception {
        this.mvc.perform(post("/").param("operand1","111").param("operator","+").param("operand2","111"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
			.andExpect(model().attribute("result", "1110"))
			.andExpect(model().attribute("operand1", "111"));
    }
    // Additional test case 1: Addition with carry
    @Test
    public void postParameterAdditionWithCarry() throws Exception {
        this.mvc.perform(post("/").param("operand1", "111").param("operator", "+").param("operand2", "111"))
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attribute("result", "1110"))
                .andExpect(model().attribute("operand1", "111"));
    }
    // Additional test case 2: Addition with single bit operands
    @Test
    public void postParameterAdditionWithSingleBitOperands() throws Exception {
        // Test POST request where both operands are single-bit binary numbers
        this.mvc.perform(post("/").param("operand1", "1").param("operator", "+").param("operand2", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attribute("result", "10"))
                .andExpect(model().attribute("operand1", "1"));
    }
    // Additional test case 3: Addition with large binary numbers
    @Test
    public void postParameterAdditionWithLargeBinary() throws Exception {
        this.mvc.perform(post("/").param("operand1", "1010101010101010").param("operator", "+").param("operand2", "1111111111111111"))
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attribute("result", "11010101010101001"))
                .andExpect(model().attribute("operand1", "1010101010101010"));
    }
    // Test case 1 for AND operation
    @Test
    public void postParameterAndOperation() throws Exception {
        this.mvc.perform(post("/").param("operand1", "1101").param("operator", "&").param("operand2", "1011"))
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attribute("result", "1001"))
                .andExpect(model().attribute("operand1", "1101"));
    }
    // Test case 2 for AND operation
    @Test
    public void postParameterAndOperation2() throws Exception {
        this.mvc.perform(post("/").param("operand1", "111001").param("operator", "&").param("operand2", "1011101"))
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attribute("result", "11001"))
                .andExpect(model().attribute("operand1", "111001"));
    }
    // Test case 1 for OR operation
    @Test
    public void postParameterOrOperation() throws Exception {
        this.mvc.perform(post("/").param("operand1", "1101").param("operator", "|").param("operand2", "1011"))
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attribute("result", "1111"))
                .andExpect(model().attribute("operand1", "1101"));
    }
    // Test case 2 for OR operation
    @Test
    public void postParameterOrOperation2() throws Exception {
        this.mvc.perform(post("/").param("operand1", "111111").param("operator", "|").param("operand2", "1001001"))
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attribute("result", "1111111"))
                .andExpect(model().attribute("operand1", "111111"));
    }
    // Test case 1 for MULTIPLY operation
    @Test
    public void postParameterMultiplyOperation() throws Exception {
        this.mvc.perform(post("/").param("operand1", "101").param("operator", "*").param("operand2", "10"))
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attribute("result", "1010"))
                .andExpect(model().attribute("operand1", "101"));
    }
    // Test case 2 for MULTIPLY operation
    @Test
    public void postParameterMultiplyOperation2() throws Exception {
        this.mvc.perform(post("/").param("operand1", "11001").param("operator", "*").param("operand2", "1111"))
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attribute("result", "101110111"))
                .andExpect(model().attribute("operand1", "11001"));
    }

}