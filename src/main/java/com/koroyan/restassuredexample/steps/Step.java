package com.koroyan.restassuredexample.steps;

import com.koroyan.restassuredexample.enums.EndPoint;
import com.koroyan.restassuredexample.enums.SOAPAction;
import com.koroyan.restassuredexample.pojos.request.Envelope;
import com.koroyan.restassuredexample.pojos.request.GetListByName;
import com.koroyan.restassuredexample.pojos.response.FindPersonResult;
import com.koroyan.restassuredexample.pojos.response.GetListByNameResult;
import com.koroyan.restassuredexample.services.RequestService;
import com.koroyan.restassuredexample.utils.StringRequests;
import com.koroyan.restassuredexample.utils.XmlUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.commons.io.IOUtils;


import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static io.restassured.RestAssured.given;

public class Step {

    public Step() {
        disableWarning();
    }


    private void disableWarning() {
        System.err.close();
        System.setErr(System.out);
    }

    public int addInteger(int x, int y) {
        Envelope addIntegerRequestModel = RequestService.getAddIntegerRequestModel(x, y);
        return Integer.parseInt(
                given()
                        .contentType("text/xml;charset=UTF-8").and()
                        .header("SOAPAction", SOAPAction.ADD_INTEGER.toString())
                        .body(addIntegerRequestModel)
                        .when().log().all()
                        .post(EndPoint.BASE_URL.toString())
                        .then()
                        .log().ifError()
                        .assertThat()
                        .statusCode(200)
                        //.body("Envelope.Body.AddIntegerResponse.AddIntegerResult", equalTo("2"))
                        .extract().path("Envelope.Body.AddIntegerResponse.AddIntegerResult"));
    }

    public int addIntegerXml(int x, int y) throws IOException {
        String path = "src/main/resources/request.xml";
        XmlUtils.setValue(
                "tem:Arg1", x,
                "tem:Arg2", y);

        FileInputStream fileInputStream = new FileInputStream(path);
        return Integer.parseInt(given()
                .contentType("text/xml;charset=UTF-8").and()
                .header("SOAPAction", SOAPAction.ADD_INTEGER.toString())
                .body(IOUtils.toString(fileInputStream, StandardCharsets.UTF_8))
                .when().log().all()
                .post(EndPoint.BASE_URL.toString())
                .then()
                .log().ifError()
                .assertThat()
                .statusCode(200)
                //.body("Envelope.Body.AddIntegerResponse.AddIntegerResult", equalTo("2"))
                .extract().path("Envelope.Body.AddIntegerResponse.AddIntegerResult"));
    }

    public int addIntegerString(int x, int y){
        String addIntegerRequest = StringRequests.getAddIntegerRequest(x, y);
        return Integer.parseInt(given()
                .contentType("text/xml;charset=UTF-8").and()
                .header("SOAPAction", SOAPAction.ADD_INTEGER.toString())
                .body(addIntegerRequest)
                .when().log().all()
                .post(EndPoint.BASE_URL.toString())
                .then()
                .log().ifError()
                .assertThat()
                .statusCode(200)
                //.body("Envelope.Body.AddIntegerResponse.AddIntegerResult", equalTo("2"))
                .extract().path("Envelope.Body.AddIntegerResponse.AddIntegerResult"));
    }


    public FindPersonResult findPerson(String id) {
        Envelope findPersonRequestModel = RequestService.getFindPersonRequestModel(id);
        RestAssured.baseURI = EndPoint.BASE_URL.toString();
        return given()
                .contentType("text/xml;charset=UTF-8").and()
                .header("SOAPAction", SOAPAction.FIND_PERSON.toString())
                .body(findPersonRequestModel)
                .when().log().all()
                .post(EndPoint.BASE_URL.toString())
                .then()
                .log().ifError()
                .assertThat()
                .statusCode(200)
                .extract()
                .body().xmlPath().getObject("Envelope.Body.FindPersonResponse.FindPersonResult",
                        FindPersonResult.class);
    }

    public GetListByNameResult getListByName(String name) {
        Envelope getListByNameModel = RequestService.getListByNameRequestModel(name);
        RestAssured.baseURI = EndPoint.BASE_URL.toString();
        return given()
                .contentType(ContentType.XML).and()
                .header("SOAPAction", SOAPAction.GetListByName.toString())
                .body(getListByNameModel)
                .when().log().all()
                .post(EndPoint.BASE_URL.toString())
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .extract()
                .body().xmlPath().getObject("Envelope.Body.GetListByNameResponse.GetListByNameResult",
                        GetListByNameResult.class);
    }

}
