package com.villatter.service.abstraction;

import org.springframework.data.domain.Page;

public interface ReadService {
    public <T> Page<Object> getGrid(Integer page, T filter);
}
