package lk.ijse.spring.service.impl;/*
    @author Dasun
*/

import lk.ijse.spring.dto.CarDTO;
import lk.ijse.spring.dto.RentDTO;
import lk.ijse.spring.entity.Car;
import lk.ijse.spring.entity.Customer;
import lk.ijse.spring.entity.Rent;
import lk.ijse.spring.repo.CarRepo;
import lk.ijse.spring.repo.CustomerRepo;
import lk.ijse.spring.repo.RentRepo;
import lk.ijse.spring.service.RentService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RentServiceImpl implements RentService {


    @Autowired
    RentRepo rentRepo;

    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    CarRepo carRepo;

    @Autowired
    ModelMapper mapper;

    @Override
    public void bookingCar(RentDTO dto) {
        if (!rentRepo.existsById(dto.getRentId())){
            Rent rent = mapper.map(dto, Rent.class);
            rentRepo.save(rent);

        }else {
            throw new RuntimeException("Booking was added Already");
        }

    }

    @Override
    public List<RentDTO> rentRequest() {
        List<Rent> all = rentRepo.findAll();
        return mapper.map(all,new TypeToken<List<RentDTO>>(){}.getType());
    }


}
