package com.projectsem4.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projectsem4.backend.dto.director.DirectorDto;
import com.projectsem4.backend.dto.director.DirectorDtoRes;
import com.projectsem4.backend.dto.director.DirectorMapper;
import com.projectsem4.backend.entity.Category;
import com.projectsem4.backend.entity.Director;
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
@RequestMapping(path = "api/v1/director/")
public class DirectorController {
    private static final Path CURRENT_FOLDER = Paths.get(System.getProperty("user.dir"));

    @Autowired
    private DirectorMapper directorMapper;

    @Autowired
    private DirectorService directorService;


    @PostMapping("add")
    public ResponseEntity<?> addDirector(@Valid @RequestParam MultipartFile avatar , String jsonObject) throws IOException {
        DirectorDto directorDto = null;
        directorDto = new ObjectMapper().readValue(jsonObject , DirectorDto.class);
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
            Director director = directorMapper.INSTANCE.directorDtoToDirector(directorDto);
            directorService.saveDirector(director , avatar);
            directorDto.setAvt(imagePath.resolve(avatar.getOriginalFilename()).toString());
            return new ResponseEntity<>(RESTResponse.success(directorDto
                    ,"Create Director successful!"),HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(new RESTResponse.Error()
                    .badRequestWithMessage("Wrong file extension")
                    .build(),HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> updateDirector(@Valid @PathVariable int id , @RequestParam MultipartFile avatar , String jsonObject) throws IOException
    {
        try {
            DirectorDto directorDto = null;
            directorDto = new ObjectMapper().readValue(jsonObject, DirectorDto.class);
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

                Director director = directorMapper.INSTANCE.directorDtoToDirector(directorDto);
                directorService.updateDirector(id, director, avatar);
                directorDto.setAvt(imagePath.resolve(avatar.getOriginalFilename()).toString());
                return new ResponseEntity<>(RESTResponse.success(directorDto
                        , "Update director successful!"), HttpStatus.OK);
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


    @GetMapping("admin/list")
    public ResponseEntity<?> getAllDirectorsByDeleteState(@RequestParam(name="isDeleted") boolean deleted ,
                                                          @RequestParam(value = "page" , defaultValue = "0") int page,
                                                          @RequestParam(value = "size" , defaultValue = "3") int size){
        Pageable paging = PageRequest.of(page , size);
        PaginationResponse paginationResponse = new PaginationResponse();
        Page<Director> pageTuts;
        pageTuts = directorService.getDirectorsByDeleteState(deleted , paging);
        paginationResponse.currentPage = pageTuts.getNumber();
        paginationResponse.totalItems = (int) pageTuts.getTotalElements();
        paginationResponse.totalPages = pageTuts.getTotalPages();
        paginationResponse.datas = pageTuts.getContent();
        return new ResponseEntity<>(RESTResponse.success(paginationResponse ,"Get list Director successful!")
                , HttpStatus.OK);
    }

    @GetMapping("admin/list/getName")
    public ResponseEntity<?> getAllDirectorsByName(       @RequestParam(name="name" , required = false) String name,
                                                          @RequestParam(value = "page" , defaultValue = "0") int page,
                                                          @RequestParam(value = "size" , defaultValue = "3") int size) {
        if (name == null) {
            Pageable paging = PageRequest.of(page , size);
            PaginationResponse paginationResponse = new PaginationResponse();
            Page<Director> pageTuts;
            pageTuts = directorService.getDirectorsByDeleteState(false , paging);
            paginationResponse.currentPage = pageTuts.getNumber();
            paginationResponse.totalItems = (int) pageTuts.getTotalElements();
            paginationResponse.totalPages = pageTuts.getTotalPages();
            paginationResponse.datas = pageTuts.getContent();
            return new ResponseEntity<>(RESTResponse.success(paginationResponse ,"Get list Director successful!")
                    , HttpStatus.OK);
        } else {
            Pageable paging = PageRequest.of(page, size);
            PaginationResponse paginationResponse = new PaginationResponse();
            Page<Director> pageTuts;
            pageTuts = directorService.getDirectorsByName(name, paging);
            paginationResponse.currentPage = pageTuts.getNumber();
            paginationResponse.totalItems = (int) pageTuts.getTotalElements();
            paginationResponse.totalPages = pageTuts.getTotalPages();
            paginationResponse.datas = pageTuts.getContent();
            return new ResponseEntity<>(RESTResponse.success(paginationResponse, "Get list Director successful!")
                    , HttpStatus.OK);
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> softDeleteDirector(@PathVariable(value="id")int id){
        try{
            directorService.deleteDirectorById(id);
            return new ResponseEntity<>(new RESTResponse.SuccessNoData()
                    .setMessage("Delete successful !")
                    .build(), HttpStatus.OK);
        }catch(EmptyResultDataAccessException e){}
        return new ResponseEntity<>(new RESTResponse.Error()
                .checkErrorWithMessage("Wrong id or id doesn't exist!")
                .build(), HttpStatus.NOT_FOUND);
    }

    @GetMapping("admin/list/getAll")
    public ResponseEntity<?> getAllByDeleteState(@RequestParam(name="isDeleted") boolean deleted){
        return new ResponseEntity<>(RESTResponse.success(directorMapper.INSTANCE
                .lsDirectorToDirectorDtoRes(directorService
                        .getAllDirectorsByDeleteState(deleted)),"Get list Director successful!")
                , HttpStatus.OK);
    }

    @PutMapping("statechange/{id}")
    public ResponseEntity<?> setDeleteStateCategory(@PathVariable(value="id") int id, @RequestParam(name="isDeleted") boolean deleted){
        try{
            Director director = directorService.getDirectorById(id);
            director.setDeleted(deleted);
            directorService.saveDirectorForStateChange(director);
            return new ResponseEntity<>(new RESTResponse.SuccessNoData()
                    .setMessage("Change Delete State successful !")
                    .build(), HttpStatus.OK);
        }catch(EntityNotFoundException e){}
        return new ResponseEntity<>(new RESTResponse.Error()
                .checkErrorWithMessage("Wrong id or id doesn't exist!")
                .build(), HttpStatus.NOT_FOUND);
    }

    @GetMapping("directorCount")
    public ResponseEntity<?> getDirectorCount(){
        return new ResponseEntity<>(RESTResponse.success(directorService.directorn(), "Get number Director successful!")
                , HttpStatus.OK);
    }

}
