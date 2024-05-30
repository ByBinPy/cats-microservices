package itmo.tech.controllers;

import itmo.tech.messaging.rpc.OwnerClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/owner")
public class OwnerController {

    private final OwnerClient ownerClient;

    @Autowired
    public OwnerController(OwnerClient ownerClient) {
        this.ownerClient = ownerClient;
    }
    @PreAuthorize("hasAuthority('READ')")
    @GetMapping("/find")
    public ResponseEntity<?> getCatById(@RequestParam Integer ownerId) {
        return new ResponseEntity<>(ownerClient.findOwnerById(ownerId), HttpStatus.OK);
    }
/*    @PreAuthorize("hasAnyAuthority('WRITE', 'READ')")
    @PostMapping("/save")
    public ResponseEntity<?> saveOwner(@RequestBody OwnerDto ownerDto) throws SaveExistOwner {
        return ownerClient.save(ownerDto);
    }
    @PreAuthorize("hasAnyAuthority('WRITE', 'READ')")
    @DeleteMapping("/delete/{id}")
    public void deleteOwner(@PathVariable("id") Integer ownerId) {
        ownerClient.delete(ownerId);
    }
    @PreAuthorize("hasAnyAuthority('WRITE', 'READ')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateOwner(@PathVariable("id") Integer id, @RequestBody OwnerDto ownerDto) throws UnknownOwner {
        return ownerClient.update(id, ownerDto);
    }*/
}