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

public class OrderRestTest {

    @Test
    public void testOrders() throws AssertionError, URISyntaxException {
    Orders[] res = given().when().accept(ContentType.JSON)
        .get(CommonUtil.getURI("/api/Orders")).getBody().as(Orders[].class);
    assertEquals(54, res.length);
    assertEquals(1,res[0].getOrderId());
    }

    @Test
    public void testPendingCustomer200() throws AssertionError, URISyntaxException {
        given().accept(ContentType.JSON).when()
        .get(CommonUtil.getURI("/api/Orders/pendingcus/101")).then().assertThat().statusCode(200);
    }
    @Test
    public void testPendingVendor200() throws AssertionError, URISyntaxException {
        given().accept(ContentType.JSON).when()
        .get(CommonUtil.getURI("/api/Orders/pendingven/1")).then().assertThat().statusCode(200);
    }
    @Test
    public void showCustomer200() throws AssertionError, URISyntaxException {
        given().accept(ContentType.JSON).when()
        .get(CommonUtil.getURI("/api/Orders/102")).then().assertThat().statusCode(200);
    }
    @Test
    public void showVendor200() throws AssertionError, URISyntaxException {
        given().accept(ContentType.JSON).when()
        .get(CommonUtil.getURI("/api/Orders/Vendor/3")).then().assertThat().statusCode(200);
    }
}
	
