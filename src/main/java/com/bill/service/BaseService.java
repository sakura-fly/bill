package com.bill.service;


import com.bill.model.response.*;

public interface BaseService<T> {
    ResponseModel add(T t);

    ResponseOneModel<T> find(T t);

    ResponseListModel<T> list(T t);

    void resSuccess(ResponseModel r);

    void reserr(ResponseModel r);

}
