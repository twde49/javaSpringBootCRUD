package dev.esdlyon.mobilePhoneApp.Controller;

import dev.esdlyon.mobilePhoneApp.Entity.MobilePhone;
import dev.esdlyon.mobilePhoneApp.Service.MobilePhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mobilephones")
public class MobilePhoneController {

    @Autowired
    private MobilePhoneService mobilePhoneService;

    @GetMapping("/index")
    public List<MobilePhone> getAllMobilePhones() {
        return mobilePhoneService.getAllMobilePhones();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MobilePhone> getMobilePhoneById(@PathVariable Long id) {
        return mobilePhoneService.getMobilePhoneById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public MobilePhone createMobilePhone(@RequestBody MobilePhone mobilePhone) {
        return mobilePhoneService.createMobilePhone(mobilePhone);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MobilePhone> updateMobilePhone(@PathVariable Long id, @RequestBody MobilePhone mobilePhoneDetails) {
        try {
            MobilePhone updatedMobilePhone = mobilePhoneService.updateMobilePhone(id, mobilePhoneDetails);
            return ResponseEntity.ok(updatedMobilePhone);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMobilePhone(@PathVariable Long id) {
        mobilePhoneService.deleteMobilePhone(id);
        return ResponseEntity.noContent().build();
    }
}
