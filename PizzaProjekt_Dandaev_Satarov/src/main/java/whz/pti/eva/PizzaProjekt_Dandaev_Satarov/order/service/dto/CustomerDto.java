package whz.pti.eva.PizzaProjekt_Dandaev_Satarov.order.service.dto;

import whz.pti.eva.PizzaProjekt_Dandaev_Satarov.securiy.service.dto.UserDto;

public class CustomerDto {
    private String id;
    private String firstName;
    private String lastName;
    private String loginName;
    private String passwordHash;
    private UserDto userDto;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }
}
