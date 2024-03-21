package com.villatter.rest;

import com.villatter.documentation.PendudukGridResponse;
import com.villatter.dto.grid.GridPageDTO;
import com.villatter.dto.penduduk.*;
import com.villatter.service.PendudukService;
import com.villatter.service.abstraction.CrudService;
import com.villatter.service.abstraction.PendudukServiceInterface;
import com.villatter.utility.MapperHelper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/penduduk")
public class PendudukRestController extends AbstractRestController{

    @Qualifier("pendudukServiceImplementation")
    @Autowired
    private CrudService service;

    @Autowired
    private PendudukServiceInterface pendudukServiceInterface;

    @Operation(
            summary = "Mendapatkan Collection data penduduk .",
            description = "Menampilkan 10 data di setiap request"
    )
    @ApiResponse(
            responseCode = "200",
            content = { @Content(
                    mediaType = "application/json",
                    schema = @Schema( implementation = PendudukGridResponse.class )
            )}
    )
    @GetMapping
    public ResponseEntity<Object> getRowsPenduduk(@RequestParam(defaultValue = "1") Integer page,
                        @RequestParam(defaultValue = "") String nama){
        var pageCollection = service.getGrid(page, new PendudukFilterDTO(nama));
        var grid = getGridDTO(pageCollection, row -> new PendudukRowDTO(row));
        var gridPage = new GridPageDTO(grid, page, pageCollection.getTotalPages());
        return ResponseEntity.status(HttpStatus.OK).body(gridPage);
    }

    @Operation(
            summary = "Mendapatkan data penduduk yang dipilih berdasarkan NIK .",
            description = "Data ini dimaksud kan untuk muncul di form"
    )
    @ApiResponse(
            responseCode = "200",
            content = { @Content(
                    mediaType = "application/json",
                    schema = @Schema( implementation = UpdatePendudukDTO.class )
            )}
    )
    @GetMapping("/{nik}")
    public ResponseEntity<Object> countPenduduk(@PathVariable(required = true) String nik){
        var total = pendudukServiceInterface.countPendudukById(nik);
        if(total > 0){
            var entity = service.getUpdate(nik);
            var dto = new UpdatePendudukDTO(entity);
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        }
        return ResponseEntity.status(HttpStatus.OK).body(total);
    }


    @Operation(
            summary = "Digunakan untuk menambah atau pun mengupdate data penduduk .",
            description = "Penduduk tidak memiliki auto increment, NIK number yang dikenali akan menjadi update, yang tidak dikenali akan menjadi insert dan digenerate otomatis."
    )
    @ApiResponse(
            responseCode = "200",
            content = { @Content(
                    mediaType = "application/json",
                    schema = @Schema( implementation = PutPendudukDTO.class )
            )}
    )
    @PutMapping
    public ResponseEntity<Object> putPenduduk(@Valid @RequestBody PutPendudukDTO dto, BindingResult bindingResult) throws Exception{
        if(!bindingResult.hasErrors()){
            var feedback = service.save(dto);
            var response = MapperHelper.mappingObject(feedback, PutPendudukDTO.class);
            return ResponseEntity.status(200).body(response);
        }
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(getErrors(bindingResult.getAllErrors()));
    }
}