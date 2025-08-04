package sazondelbueno.web.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sazondelbueno.web.Dto.AccountResponse;
import sazondelbueno.web.Model.Account;
import sazondelbueno.web.Service.AccountService;
import sazondelbueno.web.Service.AccountServiceImpl;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/account")

public class AccountController {

    @Autowired
    AccountService service;

    @PostMapping()
    public ResponseEntity<Account> saveAccount(@RequestBody Account c) {
        Account account = service.saveAccount(c);
        return new ResponseEntity<>(account, HttpStatus.CREATED);
    }


    @GetMapping()
    public ResponseEntity<List<Account>> getAccounts() {
        List<Account> listAccounts = service.listAccounts();
        return new ResponseEntity<>(listAccounts, HttpStatus.OK);
    }

    @PostMapping("/exist")
    public ResponseEntity<Optional<Account>> existAccount(@RequestBody Account account) {
        Optional<Account> c = service.existAccount(account.getUsername());
        return new ResponseEntity<>(c, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Account>> getById(@PathVariable("id") Long id) {
        Optional<Account> c = service.getById(id);
        return new ResponseEntity<>(c, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("id") Long id) {
        boolean exit = service.deleteById(id);
        return new ResponseEntity<>(exit, HttpStatus.OK);
    }

    @GetMapping("/restore/{id}")
    public ResponseEntity<Boolean> restoreById(@PathVariable("id") Long id) {
        System.out.println("llegue al controller");
        boolean exit = service.restore(id);
        return new ResponseEntity<>(exit, HttpStatus.OK);
    }



    @PatchMapping("/{id}")
    public ResponseEntity<AccountResponse> updateAccount(@RequestBody Account account, @PathVariable Long id) {

        AccountResponse accountResponse= service.updateById(id,account);

        if (accountResponse.getStatus()){
            return new ResponseEntity<>(accountResponse, HttpStatus.OK);

        }else {
            return new ResponseEntity<>(accountResponse,HttpStatus.INTERNAL_SERVER_ERROR);
        }



    }

}