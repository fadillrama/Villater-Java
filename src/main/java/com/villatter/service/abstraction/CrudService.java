package com.villatter.service.abstraction;

import org.springframework.data.domain.Page;

public interface CrudService {
    public <T> Page<Object> getGrid(Integer page, T filter);
    public Object getUpdate(Object id);
    public Object save(Object dto);
    public void delete(Object id);
}
