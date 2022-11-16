package com.example.softwareEng.presentation;

import java.util.ArrayList;
import java.util.Optional;

import com.example.softwareEng.UserDto;
import com.example.softwareEng.CreateUserDto;
import com.example.softwareEng.persistence.User;
import com.example.softwareEng.persistence.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class UserControllerUnitTest {

    private UserController userController;


    @Test
    void testCreateUser() {
        userController = new UserController(new UserRepository() {
            @Override
            public <S extends User> S save(S entity) {

                entity.setId(2048L);
                entity.setName("Max");
                entity.setSurname("Mayer");
                entity.setEmail("mm@gmail.com");

                return entity;
            }

            @Override
            public <S extends User> Iterable<S> saveAll(Iterable<S> entities) {
                return null;
            }

            @Override
            public Optional<User> findById(Long aLong) {
                return Optional.empty();
            }

            @Override
            public boolean existsById(Long aLong) {
                return false;
            }

            @Override
            public Iterable<User> findAll() {
                return null;
            }

            @Override
            public Iterable<User> findAllById(Iterable<Long> longs) {
                return null;
            }

            @Override
            public long count() {
                return 0;
            }

            @Override
            public void deleteById(Long aLong) {

            }

            @Override
            public void delete(User entity) {

            }

            @Override
            public void deleteAllById(Iterable<? extends Long> longs) {

            }

            @Override
            public void deleteAll(Iterable<? extends User> entities) {

            }

            @Override
            public void deleteAll() {

            }
        });

        final ResponseEntity<UserDto> result =  userController.create(new CreateUserDto());

        Assertions.assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(result.getBody()).isNotNull();
        Assertions.assertThat(result.getBody().getId()).isEqualTo(2048L);
        Assertions.assertThat(result.getBody().getName())
            .isEqualTo("Max");
        Assertions.assertThat(result.getBody().getSurname())
            .isEqualTo("Mayer");
        Assertions.assertThat(result.getBody().getEmail())
                .isEqualTo("mm@gmail.com");

    }



    @Test
    void testGetAllUsers() {
        userController = new UserController(new UserRepository() {
            @Override
            public <S extends User> S save(S entity) {
                return null;
            }

            @Override
            public <S extends User> Iterable<S> saveAll(Iterable<S> entities) {
                return null;
            }

            @Override
            public Optional<User> findById(Long aLong) {
                return Optional.empty();
            }

            @Override
            public boolean existsById(Long aLong) {
                return false;
            }

            @Override
            public Iterable<User> findAll() {
                User entity;
                ArrayList<User> result = new ArrayList<>();

                entity = new User();
                entity.setId(2048L);
                entity.setName("Max");
                entity.setSurname("Mayer");
                entity.setEmail("mm@gmail.com");
                result.add(entity);

                entity = new User();
                entity.setId(2049L);
                entity.setName("Lebron");
                entity.setSurname("James");
                entity.setEmail("lj@gmail.com");
                result.add(entity);

                return  result;
            }

            @Override
            public Iterable<User> findAllById(Iterable<Long> longs) {
                return null;
            }

            @Override
            public long count() {
                return 0;
            }

            @Override
            public void deleteById(Long aLong) {

            }

            @Override
            public void delete(User entity) {

            }

            @Override
            public void deleteAllById(Iterable<? extends Long> longs) {

            }

            @Override
            public void deleteAll(Iterable<? extends User> entities) {

            }

            @Override
            public void deleteAll() {

            }
        });


        final ResponseEntity<Iterable<UserDto>> result =  userController.getAll();
        ArrayList<UserDto> listing = (ArrayList<UserDto>) result.getBody();
        UserDto latest = listing.get(listing.size()-1);

        Assertions.assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(result.getBody()).isNotNull();
        Assertions.assertThat(result.getBody()).size().isNotEqualTo(0);


        Assertions.assertThat(latest.getId()).isEqualTo(2049L);
        Assertions.assertThat(latest.getName())
                .isEqualTo("Lebron");
        Assertions.assertThat(latest.getSurname())
                .isEqualTo("James");
        Assertions.assertThat(latest.getEmail())
                .isEqualTo("lj@gmail.com");
    }



}