package com.howthere.app.service;

import com.howthere.app.object.Search;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdministratorRestService<E> {
    List<E> getList(Pageable pageable, Search search);
    void modify(E e);
    void remove(E e);
}
