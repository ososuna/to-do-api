// package com.todo.todoapi.service;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.mockito.Mockito.when;

// import java.util.Optional;

// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.junit.jupiter.MockitoExtension;
// import org.springframework.beans.BeanUtils;

// import com.todo.todoapi.exception.TodoException;
// import com.todo.todoapi.model.User;
// import com.todo.todoapi.model.dto.UserDto;
// import com.todo.todoapi.model.enums.Role;
// import com.todo.todoapi.repository.UserRepository;

// @ExtendWith(MockitoExtension.class)
// class UserServiceUnitTest {
  
//   @InjectMocks
//   private UserService userService;

//   @Mock
//   private UserRepository userRepository;

//   public Optional<User> getUser() {
//     Optional<User> user = Optional.of(new User());
//     user.get().setId("123456789");
//     user.get().setUsername("McLovinUwU");
//     user.get().setName("Fogell");
//     user.get().setLastName("McLovin");
//     user.get().setRole(Role.USER);
//     return user;
//   }

//   @Test
//   void testGetUserSuccess() throws TodoException {
//     var user = getUser();
//     var username = "McLovinUwU";
//     var userDto = new UserDto();
//     BeanUtils.copyProperties(user.get(), userDto);

//     when(userRepository.findByUsername(username)).thenReturn(user);
//     assertEquals(userDto, userService.getUser(username));
//   }
// }