package com.villatter.dto.grid;

import com.villatter.dto.penduduk.PendudukRowDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GridPageDTO<T> {
    private List<T> grid;
    private Integer page;
    private Integer totalPages;
}
