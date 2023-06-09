package com.springbot.recyclemapbot.DTO;

import com.springbot.recyclemapbot.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
     private Long chatId;

     private String firstName;

     private String lastName;

     private String username;

     public User UserDTOtoUser(){
         User user = new User();
         user.setChatId(this.chatId);
         user.setFirstName(this.firstName);
         user.setLastName(this.lastName);
         user.setUserName(this.username);
         return user;
     }
}
