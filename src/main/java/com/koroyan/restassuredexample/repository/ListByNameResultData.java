package com.koroyan.restassuredexample.repository;

import com.koroyan.restassuredexample.pojos.response.GetListByNameResult;
import com.koroyan.restassuredexample.pojos.response.PersonIdentification;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListByNameResultData {
    private static List<PersonIdentification> getListByNameResultData;
    private static List<PersonIdentification> newGetListByNameResultData;

    private static void initializeDatabase() {
        getListByNameResultData = new ArrayList<>(Arrays.asList(
                new PersonIdentification(89, "Xavier,Bob D.", "708-96-6126", "1966-08-11"),
                new PersonIdentification(10, "Xavier,Joe I.", "640-94-6892", "2013-12-12"),
                new PersonIdentification(41, "Xavier,Jose V.", "611-16-6306", "1929-01-07"),
                new PersonIdentification(116, "Xavier,Orson Q.", "261-54-9130", "1966-06-08")));
    }

    public static GetListByNameResult getPersonByName(String name) {
        initializeDatabase();
        newGetListByNameResultData = new ArrayList<>();
        for (PersonIdentification person: getListByNameResultData) {
            if (person.getName().contains(name)) {
                newGetListByNameResultData.add(person);
            }
        }
        return new GetListByNameResult(newGetListByNameResultData);
    }
}