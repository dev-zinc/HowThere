package com.howthere.app.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RestService<E> {
    Page<E> getList(Pageable pageable, String keyword);
    void modify(E e);
    void remove(E e);
}
