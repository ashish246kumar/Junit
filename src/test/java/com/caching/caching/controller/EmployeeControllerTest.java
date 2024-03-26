package com.caching.caching.controller;



import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.hamcrest.CoreMatchers.is;
import com.caching.caching.entity.Employee;
import com.caching.caching.service.EmployeeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

	   @Autowired
	    private MockMvc mockMvc;

	    @MockBean
	    private EmployeeService employeeService;
	    
	    @Autowired
	    private ObjectMapper objectMapper;
	    Employee employee;
	    Employee employee2;
	    List<Employee> listOfEmployees = new ArrayList<>();
	    
	@BeforeEach
	void setUp() throws Exception {
         employee = Employee.builder()
                .firstName("Ramesh")
                .lastName("Fadatare")
                .email("ramesh@gmail.com")
                .build();
         employee2 = Employee.builder()
                 .firstName("Rakesh")
                 .lastName("kerk")
                 .email("rakesh@gmail.com")
                 .build();
         listOfEmployees.add(employee);
         listOfEmployees.add(employee2);

	}

	@AfterEach
	void tearDown() throws Exception {
		
	}

//	@Test
//	void testEmployeeController() {
//		
//	}

	@Test
	void testCreateEmployee() throws JsonProcessingException, Exception {
		
		given(employeeService.saveEmployee(any(Employee.class)))
        .willAnswer((invocation)-> invocation.getArgument(0));

		ResultActions response = mockMvc.perform(post("/api/employees")
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(objectMapper.writeValueAsString(employee)));

	        
	        response.andDo(print()).
	                andExpect(status().isCreated())
	                .andExpect(jsonPath("$.firstName",
	                        is(employee.getFirstName())))
	                .andExpect(jsonPath("$.lastName",
	                        is(employee.getLastName())))
	                .andExpect(jsonPath("$.email",
	                        is(employee.getEmail())));
	}

	@Test
	void testGetAllEmployees() throws Exception {
		given(employeeService.getAllEmployees()).willReturn(listOfEmployees);

        
        ResultActions response = mockMvc.perform(get("/api/employees"));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()",
                        is(listOfEmployees.size())));
	}
//
	@Test
	void testGetEmployeeById() throws Exception {
		long employeeId = 1L;
		given(employeeService.getEmployeeById(employeeId)).willReturn(Optional.of(employee));
		ResultActions response = mockMvc.perform(get("/api/employees/{id}",1L));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.firstName", is(employee.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(employee.getLastName())))
                .andExpect(jsonPath("$.email", is(employee.getEmail())));
	}
	// negative scenario - valid employee id
	@Test
	void testGetEmployeeByIdthenReturnEmpty() throws Exception {
		long employeeId = 1L;
		given(employeeService.getEmployeeById(employeeId)).willReturn(Optional.empty());
		ResultActions response = mockMvc.perform(get("/api/employees/{id}",1L));

        // then - verify the output
        response.andExpect(status().isNotFound()).andDo(print());
                
	}
//
	@Test
	void testUpdateEmployee() throws JsonProcessingException, Exception {
		
		Employee updatedEmployee = Employee.builder()
                .firstName("Ram")
                .lastName("Jadhav")
                .email("ram@gmail.com")
                .build();
		long employeeId = 1L;
		given(employeeService.getEmployeeById(employeeId)).willReturn(Optional.of(employee));
		given(employeeService.updateEmployee(any(Employee.class)))
      .willAnswer((invocation)-> invocation.getArgument(0));
		
	ResultActions response = mockMvc.perform(put("/api/employees/{id}", employeeId)
                 .contentType(MediaType.APPLICATION_JSON)
                 .content(objectMapper.writeValueAsString(updatedEmployee)));
	 
	response.andExpect(status().isOk())
     .andDo(print());
		
	}
//
//	@Test
//	void testDeleteEmployee() {
//		
//	}

}
