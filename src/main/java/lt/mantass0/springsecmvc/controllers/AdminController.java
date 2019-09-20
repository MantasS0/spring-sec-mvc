package lt.mantass0.springsecmvc.controllers;

import lt.mantass0.springsecmvc.entities.Role;
import lt.mantass0.springsecmvc.entities.User;
import lt.mantass0.springsecmvc.repositories.RoleRepository;
import lt.mantass0.springsecmvc.repositories.UserRepository;
import lt.mantass0.springsecmvc.utility.PaginationRange;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;


@Controller
@RequestMapping("/admin")
public class AdminController {

    final private UserRepository userRepository;
    final private RoleRepository roleRepository;
    final private PasswordEncoder passwordEncoder;

    public AdminController(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String getMainAdminWindow(@RequestParam(name = "page", required = false, defaultValue = "1") int pageNumber,
                                     @RequestParam(name = "size", required = false, defaultValue = "10") int pageSize,
                                     @SortDefault(sort = "id", direction = Sort.Direction.ASC) Sort sort,
                                     ModelMap map){
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);

        Page<User> result;


            result = userRepository.findAll(pageable);


        map.addAttribute("result", result);
        Sort.Order order = sort.iterator().next();
        map.addAttribute("sorting", order);

        PaginationRange paginationRange = new PaginationRange(pageNumber, result.getTotalPages());
        map.addAttribute("rangeFrom", paginationRange.getRangeFrom());
        map.addAttribute("rangeTo", paginationRange.getRangeTo());

        return "admin-main-window";
    }

    @GetMapping("/new-user")
    public String getNewUserForm(User user, ModelMap map){
        return "new-user-form";
    }

    @PostMapping("/create-user")
    public String createUser(@Valid User user, BindingResult result, ModelMap map){
        if (result.hasErrors()){
            return "new-user-form";
        }
        if (userRepository.getUserByUserName(user.getUserName()) != null){
            map.addAttribute("error", "Username \"" + user.getUserName() + "\" already exists");
            return "new-user-form";
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setAccountEnabled(true);

        Set<Role> roleSet = new HashSet<>();
        roleSet.add(roleRepository.getRoleByName("USER"));
        user.setRoles(roleSet);

        try {
            userRepository.save(user);
        }catch (DataIntegrityViolationException e){
            return "redirect:/new-user";
        }
        return "redirect:/";
    }
}
