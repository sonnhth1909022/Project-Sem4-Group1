package com.projectsem4.backend.controller;

import com.projectsem4.backend.Payload.Request.SignupRequest;
import com.projectsem4.backend.Payload.Response.MessageResponse;
import com.projectsem4.backend.dto.category.CategoryDtoRes;
import com.projectsem4.backend.dto.movie.MovieMapper;
import com.projectsem4.backend.dto.movie.MovieTypeDto;
import com.projectsem4.backend.dto.user.UserDto;
import com.projectsem4.backend.entity.*;
import com.projectsem4.backend.repository.AccountRepo;
import com.projectsem4.backend.repository.MovieRepo;
import com.projectsem4.backend.repository.RoleRepository;
import com.projectsem4.backend.repository.UserRepository;
import com.projectsem4.backend.service.AccountService;
import com.projectsem4.backend.service.TransactionHistoryService;
import com.projectsem4.backend.service.UserService;
import com.projectsem4.backend.ulti.PaginationResponse;
import com.projectsem4.backend.ulti.RESTResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "api/v1/user/")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MovieRepo movieRepo;

    @Autowired
    private MovieMapper movieMapper;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    AccountRepo accountRepo;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionHistoryService transactionHistory;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllUsers(){
        return new ResponseEntity<>(RESTResponse.success((userService.listUser()),"Get list user successful!")
                , HttpStatus.OK);
    }

//    @GetMapping("/admin/list/getAll")
//    public ResponseEntity<?> getAllUsersByDeleteState(@RequestParam(name="isDeleted") boolean deleted){
//        return new ResponseEntity<>(RESTResponse.success((userService.getAllUsersByDeleteState(deleted)),"Get list user successful!")
//                , HttpStatus.OK);
//    }

    @GetMapping("/getMovie/{id}")
    public ResponseEntity<?> getListMovieType(@PathVariable(value="id")int id) {
        MovieTypeDto movieTypeDto = new MovieTypeDto();
        User user = userRepository.findById(id).get();
        List<Movie> list = new ArrayList<>();
        List<Movie> list1 = new ArrayList<>();
        List<Movie> movieList = movieRepo.findAll();
        if (user.getAccountType().getName().equals(EAccount.ACCOUNT_NORMAL)) {
            for (int i = 0; i < movieList.size(); i++) {
                Movie movie = movieList.get(i);
                if (movie.getMovieType().getName().equals(EMovie.MOVIE_NORMAL)) {
                    list.add(movie);
                }
            }

            movieTypeDto.setListNormal(movieMapper.INSTANCE
                    .lsMovieToMovieDtoRes(list));
            return new ResponseEntity<>(RESTResponse.success(movieTypeDto
                    , "Get list movie by type successfully"), HttpStatus.OK);
        } else if (user.getAccountType().getName().equals(EAccount.ACCOUNT_VIP)) {
            for (int i = 0; i < movieList.size(); i++) {
                Movie movie = movieList.get(i);
                if (movie.getMovieType().getName().equals(EMovie.MOVIE_PREMIUM)) {
                    list1.add(movie);
                }
                if (movie.getMovieType().getName().equals(EMovie.MOVIE_NORMAL)) {
                    list.add(movie);
                }
            }
            movieTypeDto.setListNormal(movieMapper.INSTANCE
                    .lsMovieToMovieDtoRes(list));
            movieTypeDto.setListPremium(movieMapper.INSTANCE
                    .lsMovieToMovieDtoRes(list1));
            return new ResponseEntity<>(RESTResponse.success(movieTypeDto
                    , "Get list movie by type successfully"), HttpStatus.OK);
        } else {
            return null;
        }
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<?> updateUser(@PathVariable(value = "id") int id , @RequestBody UserDto userDto)
    {

        User user1 = userRepository.findById(id).get();
        if(userRepository.existsByUsername(userDto.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error : Username is already taken!"));
        }

        if (userRepository.existsByEmail(userDto.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error : Email is already in use"));
        }
        user1.setUsername(userDto.getUsername());
        user1.setPassword(encoder.encode(userDto.getPassword()));
        user1.setPhone(userDto.getPhone());
        user1.setEmail(userDto.getEmail());
        String accountType = userDto.getAccountType();

        if(accountType == null)
        {
            AccountType accountType1 = accountRepo.findByName(EAccount.ACCOUNT_NORMAL)
                    .orElseThrow(() -> new RuntimeException(("Error: Account is not found")));
            user1.setAccountType(accountType1);
        }
        else
        {
            switch (accountType) {
                case "admin" :
                    AccountType accountType1 = accountRepo.findByName(EAccount.ACCOUNT_ADMIN)
                            .orElseThrow(() -> new RuntimeException(("Error: Account is not found")));
                    user1.setAccountType(accountType1);
                    break;
                case "normal" :
                    AccountType accountMonth = accountRepo.findByName(EAccount.ACCOUNT_NORMAL)
                            .orElseThrow(() -> new RuntimeException(("Error: Account is not found")));
                    user1.setAccountType(accountMonth);
                    break;
                case "vip" :
                    AccountType accountVIP = accountRepo.findByName(EAccount.ACCOUNT_VIP)
                            .orElseThrow(() -> new RuntimeException(("Error: Account is not found")));
                    user1.setAccountType(accountVIP);
                    break;
            }
        }


        Set<String> strRoles = userDto.getRole();
        Set<Role> roles = new HashSet<>();
        if(strRoles == null) {
            Role modRole = roleRepository.findByName(ERole.ROLE_CUSTOMER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(modRole);
        }
        else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin" :
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
                        roles.add(adminRole);
                        break;
                    case "customer" :
                        Role modRole = roleRepository.findByName(ERole.ROLE_CUSTOMER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
                        roles.add(modRole);
                        break;
                }
            });
        }

        user1.setRoles(roles);

        userRepository.save(user1);
        return ResponseEntity.ok(new MessageResponse("Update user successfully"));
    }

    @GetMapping("/user/get/{id}")
    public ResponseEntity<?> getUser(@PathVariable(value="id")int id)
    {
        try{
            User user = userService.getUserById(id);
            return new ResponseEntity<>(RESTResponse.success(user
                    ,"Found an user with this id !"),HttpStatus.OK);
        }catch(EntityNotFoundException e){}
        return new ResponseEntity<>(new RESTResponse.Error()
                .checkErrorWithMessage("Wrong id or id doesn't exist!")
                .build(), HttpStatus.NOT_FOUND);
    }

    @PutMapping("/updateAccount/{id}")
    public ResponseEntity<?> updateAccount(@PathVariable(value="id")int id , @RequestParam(name = "accountType") String accountType)
    {
        User user = userRepository.findById(id).get();
        if(accountType == null)
        {
            AccountType accountType1 = accountRepo.findByName(EAccount.ACCOUNT_NORMAL)
                    .orElseThrow(() -> new RuntimeException(("Error: Account is not found")));
            user.setAccountType(accountType1);
        }
        else
        {
            switch (accountType) {
                case "ACCOUNT_ADMIN" :
                    AccountType accountType1 = accountRepo.findByName(EAccount.ACCOUNT_ADMIN)
                            .orElseThrow(() -> new RuntimeException(("Error: Account is not found")));
                    user.setAccountType(accountType1);
                    break;
                case "ACCOUNT_NORMAL" :
                    AccountType accountMonth = accountRepo.findByName(EAccount.ACCOUNT_NORMAL)
                            .orElseThrow(() -> new RuntimeException(("Error: Account is not found")));
                    user.setAccountType(accountMonth);
                    break;
                case "ACCOUNT_VIP" :
                    AccountType accountYear = accountRepo.findByName(EAccount.ACCOUNT_VIP)
                            .orElseThrow(() -> new RuntimeException(("Error: Account is not found")));
                    user.setAccountType(accountYear);
                    break;
            }
        }

        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("Update accountType successfully"));
    }

    @GetMapping("/accountType")
    public ResponseEntity<?> getAllAccountTypes(){
        return new ResponseEntity<>(RESTResponse.success(accountService.getAllAccountTypes() , "Get list Account Type successful!")
                ,HttpStatus.OK);
    }

    @GetMapping("/name")
    public ResponseEntity<?> getMovieByName( @RequestParam(value = "name" , required = false) String name)
    {
        if(name == null)
        {
            return new ResponseEntity<>(RESTResponse.success((userService.listUser()),"Get list user successful!")
                    , HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(RESTResponse.success(userService.getUserByUsername(name),"Get list user successful!")
                    , HttpStatus.OK);
        }
    }

    @GetMapping("/userCount")
    public ResponseEntity<?> getUserCount()
    {
        return new ResponseEntity<>(RESTResponse.success(userService.getUserCount(),"Get list user successful!")
            , HttpStatus.OK);
    }


    @GetMapping("admin/user/transaction")
    public ResponseEntity<?> getAllTransactionsByDeleteState(@RequestParam(name="isDeleted") boolean deleted ,
                                                          @RequestParam(value = "page" , defaultValue = "0") int page,
                                                          @RequestParam(value = "size" , defaultValue = "3") int size){
        Pageable paging = PageRequest.of(page , size);
        PaginationResponse paginationResponse = new PaginationResponse();
        Page<TransactionHistory> pageTuts;
        pageTuts = transactionHistory.getListTransactionHistoryPage(deleted , paging);
        paginationResponse.currentPage = pageTuts.getNumber();
        paginationResponse.totalItems = (int) pageTuts.getTotalElements();
        paginationResponse.totalPages = pageTuts.getTotalPages();
        paginationResponse.datas = pageTuts.getContent();
        return new ResponseEntity<>(RESTResponse.success(paginationResponse ,"Get list Transactions successful!")
                , HttpStatus.OK);
    }

    @GetMapping("admin/user/transaction02")
    public ResponseEntity<?> getAllTransactions(){
        return new ResponseEntity<>(RESTResponse.success(transactionHistory.getListTransaction(false) ,"Get list Transactions successful!")
                , HttpStatus.OK);
    }

    @DeleteMapping("admin/delete/{id}")
    public ResponseEntity<?> softDeleteTransaction(@PathVariable(value="id")int id){
        try{
            transactionHistory.deleteTransactionById(id);
            return new ResponseEntity<>(new RESTResponse.SuccessNoData()
                    .setMessage("Delete successful !")
                    .build(), HttpStatus.OK);
        }catch(EmptyResultDataAccessException e){}
        return new ResponseEntity<>(new RESTResponse.Error()
                .checkErrorWithMessage("Wrong id or id doesn't exist!")
                .build(), HttpStatus.NOT_FOUND);
    }

    @PutMapping("statechange/admin/{id}")
    public ResponseEntity<?> setDeleteStateTransaction(@PathVariable(value="id") int id, @RequestParam(name="isDeleted") boolean deleted){
        try{
            TransactionHistory transactionHistory1 = transactionHistory.getTransactionById(id);
            transactionHistory1.setDeleted(deleted);
            transactionHistory.saveTransactionHistory(transactionHistory1);
            return new ResponseEntity<>(new RESTResponse.SuccessNoData()
                    .setMessage("Change Delete State successful !")
                    .build(), HttpStatus.OK);
        }catch(EntityNotFoundException e){}
        return new ResponseEntity<>(new RESTResponse.Error()
                .checkErrorWithMessage("Wrong id or id doesn't exist!")
                .build(), HttpStatus.NOT_FOUND);
    }

    @GetMapping("admin/user/sum")
    public ResponseEntity<?> getTransactions(){
        return new ResponseEntity<>(RESTResponse.success(transactionHistory.totalSum() ,"Get total sum successful!")
                , HttpStatus.OK);
    }

    @GetMapping("admin/user/totalnotdeleted")
    public ResponseEntity<?> getNumber(){
        return new ResponseEntity<>(RESTResponse.success(transactionHistory.totalTransactionNotDeleted() ,"Get total sum successful!")
                , HttpStatus.OK);
    }

    @GetMapping("admin/transaction/getName")
    public ResponseEntity<?> getAllUsersByName(@RequestParam(name = "name" , required = false) String name,
                                               @RequestParam(value = "page" , defaultValue = "0") int page,
                                               @RequestParam(value = "size" , defaultValue = "3") int size) {
        if (name == null) {
            Pageable paging = PageRequest.of(page , size);
            PaginationResponse paginationResponse = new PaginationResponse();
            Page<TransactionHistory> pageTuts;
            pageTuts = transactionHistory.getListTransactionHistoryPage(false , paging);
            paginationResponse.currentPage = pageTuts.getNumber();
            paginationResponse.totalItems = (int) pageTuts.getTotalElements();
            paginationResponse.totalPages = pageTuts.getTotalPages();
            paginationResponse.datas = pageTuts.getContent();
            return new ResponseEntity<>(RESTResponse.success(paginationResponse ,"Get list transaction successful!")
                    , HttpStatus.OK);
        } else {
            Pageable paging = PageRequest.of(page, size);
            PaginationResponse paginationResponse = new PaginationResponse();
            Page<TransactionHistory> pageTuts;
            pageTuts = transactionHistory.getTransactionByUsername(name , paging);
            if(pageTuts == null)
            {
                return new ResponseEntity<>(RESTResponse.success(paginationResponse , "Get list transaction successful!")
                    , HttpStatus.OK);
            }
            else {
                paginationResponse.currentPage = pageTuts.getNumber();
                paginationResponse.totalItems = (int) pageTuts.getTotalElements();
                paginationResponse.totalPages = pageTuts.getTotalPages();
                paginationResponse.datas = pageTuts.getContent();
                return new ResponseEntity<>(RESTResponse.success(paginationResponse, "Get list transaction successful!")
                        , HttpStatus.OK);
            }
        }
    }


    @GetMapping("/userVipCount")
    public ResponseEntity<?> getUserVipCount()
    {
        return new ResponseEntity<>(RESTResponse.success(userService.getUserVipCount(),"Get list user successful!")
                , HttpStatus.OK);
    }

    @GetMapping("/getAll/normal")
    public ResponseEntity<?> getAllUserNormal(){
        return new ResponseEntity<>(RESTResponse.success((userService.getUserNormal()),"Get list user successful!")
                , HttpStatus.OK);
    }

    @GetMapping("/getAll/vip")
    public ResponseEntity<?> getAllUsersVip(){
        return new ResponseEntity<>(RESTResponse.success((userService.getUserVip()),"Get list user successful!")
                , HttpStatus.OK);
    }

}
