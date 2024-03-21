package com.villatter.rest;

import com.villatter.dto.utility.ValidationDTO;
import com.villatter.utility.MapperHelper;
import com.villatter.utility.RowHandler;
import org.springframework.data.domain.Page;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class AbstractRestController {

    public static List<Object> getGridDTO(Page<Object> pageCollection, RowHandler handler){
        Stream<Object> gridStream = pageCollection.toList().stream();
        return gridStream.map(row -> {
            return handler.getRow(row);
        }).collect(Collectors.toList());
    }

    List<ValidationDTO> getErrors(List<ObjectError> errors){
        var dto = new ArrayList<ValidationDTO>();
        for (var error : errors){
            var fieldName = MapperHelper.getStringField(error.getArguments()[0], "defaultMessage");
            fieldName = (fieldName.equals("")) ? "object" : fieldName;
            var message = error.getDefaultMessage();
            dto.add(new ValidationDTO(fieldName, message));
        }
        return dto;
    }
}
