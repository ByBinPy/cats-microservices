package itmo.tech.controllers;

import itmo.tech.dto.OwnerDto;
import itmo.tech.messaging.rabbitmq.owner.RabbitOwnerProducer;
import itmo.tech.messaging.rpc.OwnerRpcClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/owner")
public class OwnerController {

    private final OwnerRpcClient ownerRpcClient;
    private final RabbitOwnerProducer ownerProducer;

    @Autowired
    public OwnerController(OwnerRpcClient ownerRpcClient, RabbitOwnerProducer ownerProducer) {
        this.ownerRpcClient = ownerRpcClient;
        this.ownerProducer = ownerProducer;
    }
    @PreAuthorize("hasAnyAuthority('WRITE', 'READ')")
    @GetMapping("/find")
    public ResponseEntity<?> getOwnerById(@RequestParam Integer ownerId) throws Exception {
        return new ResponseEntity<>(ownerRpcClient.findOwnerById(ownerId), HttpStatus.OK);
    }
    @PreAuthorize("hasAnyAuthority('WRITE', 'READ')")
    @PostMapping("/save")
    public ResponseEntity<?> saveOwner(@RequestBody OwnerDto ownerDto) {
        ownerProducer.sendSaveMessage(ownerDto);
        return new ResponseEntity<>("Save message has been sent successful", HttpStatus.ACCEPTED);
    }
    @PreAuthorize("hasAnyAuthority('WRITE', 'READ')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteOwner(@PathVariable("id") Integer ownerId) {
        ownerProducer.sendDeleteMessage(ownerId);
        return new ResponseEntity<>("Delete message has been sent successful",HttpStatus.ACCEPTED);
    }
    @PreAuthorize("hasAnyAuthority('WRITE', 'READ')")
    @PutMapping("/update")
    public ResponseEntity<?> updateOwner(@RequestBody OwnerDto ownerDto){
        ownerProducer.sendUpdateMessage(ownerDto);
        return new ResponseEntity<>("Update message has been sent successful", HttpStatus.ACCEPTED);
    }
}