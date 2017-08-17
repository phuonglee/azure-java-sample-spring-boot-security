package fuonglee.azure.sample.adal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class MainRestController {

    @Autowired
    private UserService userService;

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping(value = "/user")
    public Principal user(Principal principal) {
        return principal;
    }

    @GetMapping(value = "/user/list", produces = "application/json")
    public String userList(Principal principal) {
        return userService.listUsers();
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/check/user")
    public String userRoleChk() {
        return "I have users permission";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/check/admin")
    public String adminRoleChk() {
        return "I have admin permission";
    }
}
