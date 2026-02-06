package com.rpdevelopment.user_service_api.controller;

import com.rpdevelopment.user_service_api.dto.UserPersonAddressDto;
import com.rpdevelopment.user_service_api.service.UserPersonAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/users")
public class UserPersonAddressController {

    @Autowired
    private UserPersonAddressService userPersonAddressService;

    //CRUD PADRÃO
    //FIND ALL
    @GetMapping
    public ResponseEntity<Page<UserPersonAddressDto>> userFindAll(Pageable pageable) {
        Page<UserPersonAddressDto> usersDto = userPersonAddressService.usersFindAll(pageable);
        return ResponseEntity.ok(usersDto);
    }

    //FIND BY ID
    @GetMapping(value = "/{id}")
    public ResponseEntity<UserPersonAddressDto> userFindById(@PathVariable Long id) {
        UserPersonAddressDto userDto = userPersonAddressService.usersFindById(id);
        return ResponseEntity.ok(userDto);
    }

    //POST
    @PostMapping
    public ResponseEntity<UserPersonAddressDto> save(@RequestBody UserPersonAddressDto userDto) {
        UserPersonAddressDto userDtoSaved = userPersonAddressService.save(userDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(userDtoSaved.getId()).toUri();
        return ResponseEntity.created(uri).body(userDtoSaved);
    }

    //PUT
    @PutMapping(value = "/{id}")
    public ResponseEntity<UserPersonAddressDto> update(@RequestBody UserPersonAddressDto userDto, @PathVariable Long id) {
        UserPersonAddressDto userDtoUpdated = userPersonAddressService.update(userDto, id);
        return ResponseEntity.ok(userDtoUpdated);
    }

    //DELETE
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<UserPersonAddressDto> delete(@PathVariable Long id) {
        userPersonAddressService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
