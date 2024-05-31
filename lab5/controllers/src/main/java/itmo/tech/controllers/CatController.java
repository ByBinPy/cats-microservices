package itmo.tech.controllers;
import itmo.tech.dto.CatDto;
import itmo.tech.messaging.rabbitmq.cat.RabbitCatProducer;
import itmo.tech.messaging.rpc.CatRpcClient;
import itmo.tech.security.SecurityOwner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cat")
public class CatController {
    private final CatRpcClient catRpcClient;
    private final RabbitCatProducer catProducer;
    @Autowired
    public CatController(CatRpcClient catRpcClient, RabbitCatProducer catProducer) {
        this.catRpcClient = catRpcClient;
        this.catProducer = catProducer;
    }

    @PreAuthorize("hasAuthority('READ')")
    @GetMapping("/find")
    public ResponseEntity<?> getCatById(@RequestParam Integer catId, @AuthenticationPrincipal SecurityOwner securityOwner)  throws Exception {
        return new ResponseEntity<>(catRpcClient.findCatById(catId, securityOwner.getId()), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('READ')")
    @GetMapping("/filter/color")
    public ResponseEntity<?> getCatsByColor(@RequestParam String color, @AuthenticationPrincipal SecurityOwner securityOwner)  throws Exception {

        return new ResponseEntity<>(catRpcClient.findCatsByColor(color, securityOwner.getId()), HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('WRITE')")
    @PostMapping("/save")
    public ResponseEntity<?> saveCat(@RequestBody CatDto catDto) {
        catProducer.sendSaveMessage(catDto);
        return new ResponseEntity<>("Save message has been sent successful", HttpStatus.ACCEPTED);
    }

    @PreAuthorize("hasAuthority('WRITE')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCat(@PathVariable("id") Integer catId) {
        catProducer.sendDeleteMessage(catId);
        return new ResponseEntity<>("Delete message has been sent successful",HttpStatus.ACCEPTED);
    }

    @PreAuthorize("hasAuthority('WRITE')")
    @PutMapping("/update")
    public ResponseEntity<?> updateCat(@RequestBody CatDto catDto) {
        catProducer.sendUpdateMessage(catDto);
        return new ResponseEntity<>("Update message has been sent successful", HttpStatus.ACCEPTED);
    }
}
