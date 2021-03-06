package dating.controllers;

import dating.domain.Result;
import dating.models.AppUser;
import dating.security.AppUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/user")
public class AppUserController {

    // Field
    AppUserService appUserService;

    // Constructor
    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    // Method: Create a new user.
    @PostMapping("/register")
    public ResponseEntity<Object> create(@RequestBody AppUser user) {

        Result<AppUser> result = appUserService.create(user.getUsername(), user.getPassword(), user.getEmail(), user.getFirstName(),
                user.getLastName());

        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }

        return ErrorResponse.build(result);
    }
}
