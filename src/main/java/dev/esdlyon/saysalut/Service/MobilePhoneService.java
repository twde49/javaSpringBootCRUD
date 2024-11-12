package dev.esdlyon.saysalut.Service;

import dev.esdlyon.saysalut.Entity.MobilePhone;
import dev.esdlyon.saysalut.Repository.MobilePhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MobilePhoneService {

    @Autowired
    private MobilePhoneRepository mobilePhoneRepository;

    public List<MobilePhone> getAllMobilePhones() {
        return mobilePhoneRepository.findAll();
    }

    public Optional<MobilePhone> getMobilePhoneById(Long id) {
        return mobilePhoneRepository.findById(id);
    }

    public MobilePhone createMobilePhone(MobilePhone mobilePhone) {
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
