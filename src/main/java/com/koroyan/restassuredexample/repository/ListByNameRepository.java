package com.koroyan.restassuredexample.repository;

import com.koroyan.restassuredexample.pojos.response.GetListByNameResult;

public interface ListByNameRepository {
    GetListByNameResult getListByName(String name);
}