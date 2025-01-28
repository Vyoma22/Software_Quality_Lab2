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
    // Additional test case 1: Addition with one operand empty
    @Test
    public void postParameterAdditionWithEmptyOperand() throws Exception {
        this.mvc.perform(post("/").param("operand1", "111").param("operator", "+").param("operand2", ""))
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attribute("result", "111")) // Operand2 treated as 0
                .andExpect(model().attribute("operand1", "111"));
    }
    // Additional test case 2: Addition with single bit operands
    @Test
    public void postParameterAdditionWithSingleBitOperands() throws Exception {
        // Test POST request where both operands are single-bit binary numbers
        this.mvc.perform(post("/").param("operand1", "1").param("operator", "+").param("operand2", "1"))
                .andExpect(status().isOk()) // Ensures the response is successful
                .andExpect(view().name("result")) // Confirms the view name
                .andExpect(model().attribute("result", "10")) // Verifies the correct addition result
                .andExpect(model().attribute("operand1", "1")); // Confirms operand1 value
    }
    // Additional test case 3: Addition with large binary numbers
    @Test
    public void postParameterDivisionWithLargeBinary() throws Exception {
        this.mvc.perform(post("/").param("operand1", "1010101010101010").param("operator", "+").param("operand2", "1111111111111111"))
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attribute("result", "11010101010101001")) // Expected result of binary addition
                .andExpect(model().attribute("operand1", "1010101010101010"));
    }
    @Test
    public void postParameterAndOperation() throws Exception {
        this.mvc.perform(post("/").param("operand1", "1101").param("operator", "&").param("operand2", "1011"))
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attribute("result", "1001")) // Binary AND result
                .andExpect(model().attribute("operand1", "1101"));
    }

    @Test
    public void postParameterOrOperation() throws Exception {
        this.mvc.perform(post("/").param("operand1", "1101").param("operator", "|").param("operand2", "1011"))
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attribute("result", "1111")) // Binary OR result
                .andExpect(model().attribute("operand1", "1101"));
    }

    @Test
    public void postParameterMultiplyOperation() throws Exception {
        this.mvc.perform(post("/").param("operand1", "101").param("operator", "*").param("operand2", "10"))
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attribute("result", "1010")) // Binary Multiply result
                .andExpect(model().attribute("operand1", "101"));
    }

}