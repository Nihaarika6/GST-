package com.hexaware.MLP176.integration.test;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import org.junit.Test;
import static org.junit.Assert.*;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.path.json.JsonPath;
import static com.jayway.restassured.RestAssured.given;

public class CustomerRestTest {

	@Test
	public void testCustomerList() throws AssertionError, URISyntaxException {
	Customer[] res = given().when().accept(ContentType.JSON)
		.get(CommonUtil.getURI("/api/customer")).getBody().as(Customer[].class);
	assertEquals(6, res.length);
	assertEquals(100, res[0].getCustomerId());
	assertEquals("HARI", res[0].getCustomerName());
	assertEquals("200", res[0].getCustomerPassword());
	}

    @Test
	public void testCustomerDetails() throws AssertionError, URISyntaxException {
	Customer c = given().accept(ContentType.JSON).when().get(CommonUtil.getURI("/api/customer/hari")).
				  getBody().as(Customer.class);
	assertEquals(100, c.getCustomerId());
	assertEquals("HARI", c.getCustomerName());
	assertEquals("200", c.getCustomerPassword());
	}
    @Test
    
	public void testCustomerByName200() throws AssertionError, URISyntaxException {
		given().accept(ContentType.JSON).when()
		.get(CommonUtil.getURI("/api/customer/sankari")).then().assertThat().statusCode(200);
	}
	

	@Test
	public void testCustomerByName404() throws AssertionError, URISyntaxException {
		given().accept(ContentType.JSON).when()
		.get(CommonUtil.getURI("/api/customer/niha")).then().assertThat().statusCode(204);
	}
	
}