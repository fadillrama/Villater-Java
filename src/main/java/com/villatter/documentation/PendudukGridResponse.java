package com.villatter.documentation;

import com.villatter.dto.penduduk.PendudukRowDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PendudukGridResponse {
    private List<PendudukRowDTO> grid;
    private Integer page;
    private Integer totalPages;
}
