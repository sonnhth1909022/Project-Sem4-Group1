package com.projectsem4.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projectsem4.backend.dto.cast.CastDto;
import com.projectsem4.backend.dto.category.CategoryDto;
import com.projectsem4.backend.dto.category.CategoryDtoRes;
import com.projectsem4.backend.dto.category.CategoryMapper;
import com.projectsem4.backend.entity.Cast;
import com.projectsem4.backend.entity.Category;
import com.projectsem4.backend.repository.CategoryRepo;
import com.projectsem4.backend.service.CategoryService;
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
@RequestMapping(path = "api/v1/category/")
public class CategoryController {

    private static final Path CURRENT_FOLDER = Paths.get(System.getProperty("user.dir"));

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private CategoryService categoryService;


    //Lấy list dựa theo trạng thái: đã xóa hay chưa xóa ? (dùng cho admin)
    @GetMapping("admin/list")
    public ResponseEntity<?> getAllCategoriesByDeleteState(@RequestParam(name="isDeleted") boolean deleted,
                                                           @RequestParam(value = "page" , defaultValue = "0") int page,
                                                           @RequestParam(value = "size" , defaultValue = "3") int size){

        Pageable paging = PageRequest.of(page , size);
        PaginationResponse paginationResponse = new PaginationResponse();
        Page<Category> pageTuts;
        pageTuts = categoryService.getCategoriesByDeleteState(deleted , paging);
        paginationResponse.currentPage = pageTuts.getNumber();
        paginationResponse.totalItems = (int) pageTuts.getTotalElements();
        paginationResponse.totalPages = pageTuts.getTotalPages();
        paginationResponse.datas = pageTuts.getContent();
        return new ResponseEntity<>(RESTResponse.success(paginationResponse,"Get list Category successful!")
                , HttpStatus.OK);
    }

    @GetMapping("admin/list/getName")
    public ResponseEntity<?> getAllCategoriesByDeleteState(@RequestParam(name="name" , required = false) String name,
                                                           @RequestParam(value = "page" , defaultValue = "0") int page,
                                                           @RequestParam(value = "size" , defaultValue = "3") int size) {
        if (name == null) {
            Pageable paging = PageRequest.of(page , size);
            PaginationResponse paginationResponse = new PaginationResponse();
            Page<Category> pageTuts;
            pageTuts = categoryService.getCategoriesByDeleteState(false , paging);
            paginationResponse.currentPage = pageTuts.getNumber();
            paginationResponse.totalItems = (int) pageTuts.getTotalElements();
            paginationResponse.totalPages = pageTuts.getTotalPages();
            paginationResponse.datas = pageTuts.getContent();
            return new ResponseEntity<>(RESTResponse.success(paginationResponse,"Get list Category successful!")
                    , HttpStatus.OK);
        } else {
            Pageable paging = PageRequest.of(page, size);
            PaginationResponse paginationResponse = new PaginationResponse();
            Page<Category> pageTuts;
            pageTuts = categoryService.getCategoriesByName(name, paging);
            paginationResponse.currentPage = pageTuts.getNumber();
            paginationResponse.totalItems = (int) pageTuts.getTotalElements();
            paginationResponse.totalPages = pageTuts.getTotalPages();
            paginationResponse.datas = pageTuts.getContent();
            return new ResponseEntity<>(RESTResponse.success(paginationResponse, "Get list Category successful!")
                    , HttpStatus.OK);
        }
    }
    @GetMapping("admin/list/getAll")
    public ResponseEntity<?> getAllByDeleteState(@RequestParam(name="isDeleted") boolean deleted)
    {
        return new ResponseEntity<>(RESTResponse.success(categoryMapper.INSTANCE
                .lsCategoryToCategoryDtoRes(categoryService
                        .getAllCategoriesByDeleteState(deleted)),"Get list Category successful!")
                , HttpStatus.OK);
    }

    // Thêm item (nếu ko có trường unique trong entity thì bỏ qua đoạn code check name)
    // Ở đây vì trong entity class Category có trường name với annotation là unique, nên sẽ có thêm đoạn check name,
    // nếu name trùng với name đã có trong database (được tạo trước đó), thì sẽ ko cho insert vào database.
    @PostMapping("add")
    public ResponseEntity<?> createCategory(@Valid @RequestParam MultipartFile avt , String jsonObject) throws IOException {
        CategoryDto categoryDto = null;
        categoryDto = new ObjectMapper().readValue(jsonObject , CategoryDto.class);

        boolean checkName = categoryRepo.existsByName(categoryDto.getName());
        if (checkName){
            return new ResponseEntity<>(new RESTResponse.Error()
                    .badRequestWithMessage("Name already exist!")
                    .build(),HttpStatus.BAD_REQUEST);
        }

        Path staticPath = Paths.get("static");
        Path imagePath = Paths.get("images");
        String filename = avt.getOriginalFilename();
        int index = filename.lastIndexOf('.');
        String extension = filename.substring(index + 1);
        if(extension.equalsIgnoreCase("jpg") || extension.equalsIgnoreCase("png")) {
            if (!Files.exists(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath))) {
                Files.createDirectories(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath));
            }
            Path file = CURRENT_FOLDER.resolve(staticPath).resolve(imagePath).resolve(avt.getOriginalFilename());
            try (OutputStream os = Files.newOutputStream(file)) {
                os.write(avt.getBytes());
            }
            Category category = categoryMapper.INSTANCE.categoryDtoToCategory(categoryDto);
            categoryService.saveCategory(category , avt);
            categoryDto.setAvt(imagePath.resolve(avt.getOriginalFilename()).toString());
            return new ResponseEntity<>(RESTResponse.success(categoryDto
                    ,"Create Category successful!"),HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(new RESTResponse.Error()
                    .badRequestWithMessage("Wrong file extension")
                    .build(),HttpStatus.BAD_REQUEST);
        }
    }


    // Sửa item theo id
    // Ở đây cũng có đoạn check name, nhưng hàm hơi khác 1 chút, lấy từ service ra, hàm này kiểm tra thêm trường hợp nếu cùng 1 id,
    // chỉ sửa các trường khác mà ko sửa trường name (vì name là unique), thì khi update sẽ ko thực hiện check name nữa (vì name vẫn như cũ).
    @PutMapping("update/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable(value = "id") int id, @Valid @RequestParam MultipartFile avt , String jsonObject) throws  IOException{
        try{
            CategoryDto categoryDto = null;
            categoryDto = new ObjectMapper().readValue(jsonObject , CategoryDto.class);


            Category category = categoryService.getCategoryById(id);
            boolean checkExistNameUpdate = categoryService.checkExistNameUpdate(categoryDto.getName(),category);
            if (checkExistNameUpdate){
                return new ResponseEntity<>(new RESTResponse.Error()
                        .badRequestWithMessage("Name already exist!")
                        .build(),HttpStatus.BAD_REQUEST);
            }
            Path staticPath = Paths.get("static");
            Path imagePath = Paths.get("images");
            String filename = avt.getOriginalFilename();
            int index = filename.lastIndexOf('.');
            String extension = filename.substring(index + 1);
            if(extension.equalsIgnoreCase("jpg") || extension.equalsIgnoreCase("png")) {
                if (!Files.exists(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath))) {
                    Files.createDirectories(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath));
                }
                Path file = CURRENT_FOLDER.resolve(staticPath).resolve(imagePath).resolve(avt.getOriginalFilename());
                try (OutputStream os = Files.newOutputStream(file)) {
                    os.write(avt.getBytes());
                }
                Category category1 = categoryMapper.INSTANCE.categoryDtoToCategory(categoryDto);
                categoryService.UpdateCategory(id , category1 , avt);
                categoryDto.setAvt(imagePath.resolve(avt.getOriginalFilename()).toString());
                return new ResponseEntity<>(RESTResponse.success(categoryDto
                        , "Update category successful!"), HttpStatus.OK);
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


    //Xóa mềm (tức là ghi đè lệnh xóa trong sql bằng lệnh update)
    //Công việc: dùng hàm delete trong JPA như bình thường, nhưng lúc này query delete của sql đã được đổi thành update,
    // trường thay đổi là trường deleted (trong database).
    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> softDeleteCategory(@PathVariable(value="id")int id){
        try{
            categoryService.deleteCategoryById(id);
            return new ResponseEntity<>(new RESTResponse.SuccessNoData()
                    .setMessage("Delete successful !")
                    .build(), HttpStatus.OK);
        }catch(EmptyResultDataAccessException e){}
        return new ResponseEntity<>(new RESTResponse.Error()
                .checkErrorWithMessage("Wrong id or id doesn't exist!")
                .build(), HttpStatus.NOT_FOUND);
    }


    //Khôi phục item đã lỡ xóa nhầm
    //Công việc: Lấy ra category theo id truyền vào, sau đó set trường deleted theo param deleted người dùng truyền
    @PutMapping("statechange/{id}")
    public ResponseEntity<?> setDeleteStateCategory(@PathVariable(value="id") int id, @RequestParam(name="isDeleted") boolean deleted){
        try{
            Category category = categoryService.getCategoryById(id);
            category.setDeleted(deleted);
            categoryRepo.save(category);
            return new ResponseEntity<>(new RESTResponse.SuccessNoData()
                    .setMessage("Change Delete State successful !")
                    .build(), HttpStatus.OK);
        }catch(EntityNotFoundException e){}
        return new ResponseEntity<>(new RESTResponse.Error()
                .checkErrorWithMessage("Wrong id or id doesn't exist!")
                .build(), HttpStatus.NOT_FOUND);
    }

    @GetMapping("categoryCount")
    public ResponseEntity<?> getCategoryCount(){
        return new ResponseEntity<>(RESTResponse.success(categoryService.categoryn(), "Get number category successful!")
                , HttpStatus.OK);
    }
}
