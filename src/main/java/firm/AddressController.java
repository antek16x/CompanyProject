package firm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AddressController {

    @Autowired
    private AddressRepository repAddress;

//    @RequestMapping("/address")
//    @ResponseBody
//    public List<Address> getAll() {
//        return repAddress.findAll();
//    }

    @RequestMapping("/address/{id}")
    @ResponseBody
    public Address getOne(@PathVariable("id") Long id) {
        return repAddress.findById(id).orElse(null);
    }

    @RequestMapping(value = "/address", method = RequestMethod.POST)
    @ResponseBody
    public Address create(@RequestBody Address address) {
        address.setId(null);
        return repAddress.save(address);
    }

    @RequestMapping(value = "/address/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public Address updateOrCreatePut(@PathVariable("id") Long id, @RequestBody Address address) {
        address.setId(id);
        return repAddress.save(address);
    }

    @RequestMapping(value = "/address/{id}", method = RequestMethod.PATCH)
    @ResponseBody
    public Address updateOrCreatePatch(@PathVariable("id") Long id, @RequestBody Address address) {
        Address old = repAddress.getOne(id);
        if (old == null) {
            return repAddress.save(address);
        }
        if (address.getStreet() != null) old.setStreet(address.getStreet());
        if (address.getPostalCode() != null) old.setPostalCode(address.getPostalCode());
        if (address.getCity() != null) old.setCity((address.getCity()));
        return repAddress.save(old);
    }

    @RequestMapping(value = "/address/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable("id") Long id) {
        repAddress.deleteById(id);
    }

    @RequestMapping("/address") //?city=Miasto
    @ResponseBody
    public List<Address> findAll(@RequestParam(name = "city", required = false) String city) {
        if(city == null) {
            return repAddress.findAll();
        } else {
            return repAddress.findAllByCity(city);
        }
    }


}
