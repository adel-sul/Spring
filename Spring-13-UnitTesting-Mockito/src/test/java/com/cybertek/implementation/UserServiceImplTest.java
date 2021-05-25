package com.cybertek.implementation;

import com.cybertek.mapper.MapperUtil;
import com.cybertek.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    UserRepository userRepository;
    @Mock
    MapperUtil mapperUtil;

    @InjectMocks
    UserServiceImpl userService;

    @Test
    void deleteByUserName() {

        userService.deleteByUserName("adel1986@yandex.ru");
        verify(userRepository).deleteByUserName("adel1986@yandex.ru");
        verify(userRepository, times(2)).deleteByUserName("adel1986@yandex.ru");
        verify(userRepository, atLeastOnce()).deleteByUserName("adel1986@yandex.ru");
        verify(userRepository, atMost(5)).deleteByUserName("adel1986@yandex.ru");
    }
}