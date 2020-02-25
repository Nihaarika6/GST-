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

public class VendorRestTest {

	@Test
	public void testVendorList() throws AssertionError, URISyntaxException {
	Vendor[] res = given().when().accept(ContentType.JSON)
		.get(CommonUtil.getURI("/api/vendor")).getBody().as(Vendor[].class);
	assertEquals(6, res.length);
	assertEquals(1, res[0].getVendorId());
	assertEquals("ABC", res[0].getVendorName());
	assertEquals("200", res[0].getVendorPassword());
	}

    @Test
	public void testVendorDetails() throws AssertionError, URISyntaxException {
	Vendor v = given().accept(ContentType.JSON).when().get(CommonUtil.getURI("/api/vendor/abc")).
				  getBody().as(Vendor.class);
	assertEquals(1, v.getVendorId());
	assertEquals("ABC", v.getVendorName());
	assertEquals("200", v.getVendorPassword());
	}
	@Test
	public void testVendorByName200() throws AssertionError, URISyntaxException {
		given().accept(ContentType.JSON).when()
		.get(CommonUtil.getURI("/api/vendor/abc")).then().assertThat().statusCode(200);
	}
	

	@Test
	public void testVendorByName404() throws AssertionError, URISyntaxException {
		given().accept(ContentType.JSON).when()
		.get(CommonUtil.getURI("/api/vendor/jek")).then().assertThat().statusCode(204);
	}
	
}