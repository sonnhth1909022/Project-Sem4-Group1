package com.projectsem4.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projectsem4.backend.dto.cast.CastDto;
import com.projectsem4.backend.dto.cast.CastMapper;
import com.projectsem4.backend.dto.director.DirectorDto;
import com.projectsem4.backend.dto.director.DirectorMapper;
import com.projectsem4.backend.entity.Cast;
import com.projectsem4.backend.entity.Director;
import com.projectsem4.backend.service.CastService;
import com.projectsem4.backend.service.DirectorService;
import com.projectsem4.backend.ulti.PaginationResponse;
import com.projectsem4.backend.ulti.RESTResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "api/v1/cast/")
public class CastController {
    private static final Path CURRENT_FOLDER = Paths.get(System.getProperty("user.dir"));

    @Autowired
    private CastMapper castMapper;

    @Autowired
    private CastService castService;

    @PostMapping("add")
    public ResponseEntity<?> addCast(@Valid @RequestParam MultipartFile avatar , String jsonObject) throws IOException {
        CastDto castDto = null;
        castDto = new ObjectMapper().readValue(jsonObject , CastDto.class);

        Path staticPath = Paths.get("static");
        Path imagePath = Paths.get("images");
        String filename = avatar.getOriginalFilename();
        int index = filename.lastIndexOf('.');
        String extension = filename.substring(index + 1);
        if(extension.equalsIgnoreCase("jpg")) {
            if (!Files.exists(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath))) {
                Files.createDirectories(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath));
            }
            Path file = CURRENT_FOLDER.resolve(staticPath).resolve(imagePath).resolve(avatar.getOriginalFilename());
            try (OutputStream os = Files.newOutputStream(file)) {
                os.write(avatar.getBytes());
            }

            Cast cast = castMapper.INSTANCE.castDtoToCast(castDto);
            castService.saveCast(cast, avatar);
            castDto.setAvt(imagePath.resolve(avatar.getOriginalFilename()).toString());
            return new ResponseEntity<>(RESTResponse.success(castDto
                    , "Create Cast successful!"), HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(new RESTResponse.Error()
                    .badRequestWithMessage("Wrong file extension")
                    .build(),HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> updateCast(@Valid @PathVariable int id , @RequestParam MultipartFile avatar , String jsonObject) throws IOException
    {
        try {
            CastDto castDto = null;
            castDto = new ObjectMapper().readValue(jsonObject , CastDto.class);
            Path staticPath = Paths.get("static");
            Path imagePath = Paths.get("images");
            String filename = avatar.getOriginalFilename();
            int index = filename.lastIndexOf('.');
            String extension = filename.substring(index + 1);
            if(extension.equalsIgnoreCase("jpg")) {
                if (!Files.exists(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath))) {
                    Files.createDirectories(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath));
                }
                Path file = CURRENT_FOLDER.resolve(staticPath).resolve(imagePath).resolve(avatar.getOriginalFilename());
                try (OutputStream os = Files.newOutputStream(file)) {
                    os.write(avatar.getBytes());
                }

                Cast cast = castMapper.INSTANCE.castDtoToCast(castDto);
                castService.updateCast(id, cast, avatar);
                castDto.setAvt(imagePath.resolve(avatar.getOriginalFilename()).toString());
                return new ResponseEntity<>(RESTResponse.success(castDto
                        , "Update cast successful!"), HttpStatus.OK);
            }
            else
            {
                return new ResponseEntity<>(new RESTResponse.Error()
                        .badRequestWithMessage("Wrong file extension")
                        .build(),HttpStatus.BAD_REQUEST);
            }
        }catch(EntityNotFoundException e){ }
        return new ResponseEntity<>(new RESTResponse.Error()
                .checkErrorWithMessage("Wrong id or id doesn't exist!")
                .build(), HttpStatus.NOT_FOUND);

    }

    @GetMapping("admin/cast/getName")
    public ResponseEntity<?> getAllCastsByName(@RequestParam(name = "name" , required = false) String name,
                                                      @RequestParam(value = "page" , defaultValue = "0") int page,
                                                      @RequestParam(value = "size" , defaultValue = "3") int size) {
        if (name == null) {
            Pageable paging = PageRequest.of(page , size);
            PaginationResponse paginationResponse = new PaginationResponse();
            Page<Cast> pageTuts;
            pageTuts = castService.getCastsByDeleteState(false , paging);
            paginationResponse.currentPage = pageTuts.getNumber();
            paginationResponse.totalItems = (int) pageTuts.getTotalElements();
            paginationResponse.totalPages = pageTuts.getTotalPages();
            paginationResponse.datas = pageTuts.getContent();
            return new ResponseEntity<>(RESTResponse.success(paginationResponse ,"Get list cast successful!")
                    , HttpStatus.OK);
        } else {
            Pageable paging = PageRequest.of(page, size);
            PaginationResponse paginationResponse = new PaginationResponse();
            Page<Cast> pageTuts;
            pageTuts = castService.getCastsByName(name, paging);
            paginationResponse.currentPage = pageTuts.getNumber();
            paginationResponse.totalItems = (int) pageTuts.getTotalElements();
            paginationResponse.totalPages = pageTuts.getTotalPages();
            paginationResponse.datas = pageTuts.getContent();
            return new ResponseEntity<>(RESTResponse.success(paginationResponse, "Get list cast successful!")
                    , HttpStatus.OK);
        }
    }
    @GetMapping("admin/list")
    public ResponseEntity<?> getAllCastsByDeleteState(@RequestParam(name="isDeleted") boolean deleted,
                                                      @RequestParam(value = "page" , defaultValue = "0") int page,
                                                      @RequestParam(value = "size" , defaultValue = "3") int size){
        Pageable paging = PageRequest.of(page , size);
        PaginationResponse paginationResponse = new PaginationResponse();
        Page<Cast> pageTuts;
        pageTuts = castService.getCastsByDeleteState(deleted , paging);
        paginationResponse.currentPage = pageTuts.getNumber();
        paginationResponse.totalItems = (int) pageTuts.getTotalElements();
        paginationResponse.totalPages = pageTuts.getTotalPages();
        paginationResponse.datas = pageTuts.getContent();
        return new ResponseEntity<>(RESTResponse.success(paginationResponse ,"Get list cast successful!")
                , HttpStatus.OK);
    }

    @GetMapping("admin/list/getAll")
    public ResponseEntity<?> getAllByDeleteState(@RequestParam(name="isDeleted") boolean deleted){
        return new ResponseEntity<>(RESTResponse.success(castMapper.INSTANCE
                .lsCastToCastDtoRes(castService
                        .getAllCastsByDeleteState(deleted)),"Get list cast successful!")
                , HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> softDeleteCast(@PathVariable(value="id")int id){
        try{
            castService.deleteCastById(id);
            return new ResponseEntity<>(new RESTResponse.SuccessNoData()
                    .setMessage("Delete successful !")
                    .build(), HttpStatus.OK);
        }catch(EmptyResultDataAccessException e){}
        return new ResponseEntity<>(new RESTResponse.Error()
                .checkErrorWithMessage("Wrong id or id doesn't exist!")
                .build(), HttpStatus.NOT_FOUND);
    }

    @PutMapping("statechange/{id}")
    public ResponseEntity<?> setDeleteStateCast(@PathVariable(value="id") int id, @RequestParam(name="isDeleted") boolean deleted){
        try{
            Cast cast = castService.getCastById(id);
            cast.setDeleted(deleted);
            castService.saveCastForStateChange(cast);
            return new ResponseEntity<>(new RESTResponse.SuccessNoData()
                    .setMessage("Change Delete State successful !")
                    .build(), HttpStatus.OK);
        }catch(EntityNotFoundException e){}
        return new ResponseEntity<>(new RESTResponse.Error()
                .checkErrorWithMessage("Wrong id or id doesn't exist!")
                .build(), HttpStatus.NOT_FOUND);
    }

    @GetMapping("castCount")
    public ResponseEntity<?> getCastCount(){
        return new ResponseEntity<>(RESTResponse.success(castService.castn(), "Get number cast successful!")
                , HttpStatus.OK);
    }

}
