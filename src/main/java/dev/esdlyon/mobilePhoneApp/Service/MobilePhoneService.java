package dev.esdlyon.mobilePhoneApp.Service;

import dev.esdlyon.mobilePhoneApp.Entity.MobilePhone;
import dev.esdlyon.mobilePhoneApp.Entity.User;
import dev.esdlyon.mobilePhoneApp.Repository.MobilePhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MobilePhoneService {

    @Autowired
    private MobilePhoneRepository mobilePhoneRepository;
    private SecurityContextHolder authentication;

    public List<MobilePhone> getAllMobilePhones() {
        return mobilePhoneRepository.findAll();
    }

    public Optional<MobilePhone> getMobilePhoneById(Long id) {
        return mobilePhoneRepository.findById(id);
    }

    public MobilePhone createMobilePhone(MobilePhone mobilePhone) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        mobilePhone.setCreatedBy(currentUser);
        return mobilePhoneRepository.save(mobilePhone);
    }

    public MobilePhone updateMobilePhone(Long id, MobilePhone mobilePhoneDetails) {
        return mobilePhoneRepository.findById(id).map(mobilePhone -> {
            mobilePhone.setBrand(mobilePhoneDetails.getBrand());
            mobilePhone.setModel(mobilePhoneDetails.getModel());
            mobilePhone.setPrice(mobilePhoneDetails.getPrice());
            mobilePhone.setStorageCapacity(mobilePhoneDetails.getStorageCapacity());
            mobilePhone.setRam(mobilePhoneDetails.getRam());
            mobilePhone.setColor(mobilePhoneDetails.getColor());
            return mobilePhoneRepository.save(mobilePhone);
        }).orElseThrow(() -> new RuntimeException("Mobile phone not found"));
    }

    public void deleteMobilePhone(Long id) {
        mobilePhoneRepository.deleteById(id);
    }
}
