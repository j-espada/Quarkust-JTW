package org.jwt.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class UserDto {

    @NotNull(message = "username must not be null")
    @Size(min = 5, max = 100, message = "size between 5 and 100")
    private String username;

    @NotNull(message = "password must not be null")
    @Size(min = 10, max = 200, message = "size between 10 and 200")
    private String password;
}
