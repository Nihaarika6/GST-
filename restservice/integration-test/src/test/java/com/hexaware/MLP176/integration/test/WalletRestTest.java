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

public class WalletRestTest {

    @Test
<<<<<<< HEAD
    
=======
>>>>>>> 9bcbcea88f7d2f0d288e635b19d430234fb6c376
    public void testWallet() throws AssertionError, URISyntaxException {
        Wallet[] res = given().when().accept(ContentType.JSON)
        .get(CommonUtil.getURI("/api/wallet/101"))
        .getBody().as(Wallet[].class);
        assertEquals(5, res.length);
    }
    @Test
    public void testWalletById200() throws AssertionError, URISyntaxException {
        given().accept(ContentType.JSON).when()
        .get(CommonUtil.getURI("/api/wallet/101")).then().assertThat().statusCode(200);
    }

    @Test
    public void testWalletById404() throws AssertionError, URISyntaxException {
     given().accept(ContentType.JSON).when()
     .get(CommonUtil.getURI("/api/Wallet/1")).then().assertThat().statusCode(404);
    }
    
}
