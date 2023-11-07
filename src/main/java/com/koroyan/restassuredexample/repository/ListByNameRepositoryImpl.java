package com.koroyan.restassuredexample.repository;

import com.koroyan.restassuredexample.pojos.response.GetListByNameResult;
public class ListByNameRepositoryImpl implements ListByNameRepository{


    @Override
    public GetListByNameResult getListByName(String name) {
        return ListByNameResultData.getPersonByName(name);
    }
}
