package databaseSpring.databaseSpringBoot.controller;
import databaseSpring.databaseSpringBoot.Dto.productdto;
import databaseSpring.databaseSpringBoot.entity.product;
import databaseSpring.databaseSpringBoot.exception.userNotFoundException;
import databaseSpring.databaseSpringBoot.service.DatabaseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class databasecontroller {

    @Autowired
    private DatabaseService dser;
 
    @PostMapping("/addproduct")
    public productdto addproduct(@Valid @RequestBody productdto p)
    {


        return dser.saveProduct(p);

    }
    @PostMapping("/addproducts")
    public List<productdto> addproducts(@Valid @RequestBody List<productdto> list, BindingResult bindingResult) throws MethodArgumentNotValidException {
        if (bindingResult.hasErrors()) {
            throw new MethodArgumentNotValidException(null, bindingResult);
        }

        dser.saveProducts(list);
        return list;
    }
    @GetMapping("/getproductid/{id}")
    public productdto getproduct(@PathVariable int id)throws userNotFoundException
    {
        return dser.getProductById(id);
    }

    @GetMapping("/getproducts")
    public List<productdto> getproducts()throws userNotFoundException
    {
        return dser.getProducts();
    }

    @GetMapping("/getproduct/{name}")
    public List<productdto> getproductbyname(@PathVariable String name)throws userNotFoundException
    {
        return dser.getProductByName(name);
    }

    @DeleteMapping("/deleteproduct/{id}")
    public String deleteproduct(@PathVariable int id) throws userNotFoundException {
        return dser.deleteProduct(id);
    }

    @PutMapping("/update")
    public productdto updateproduct(@RequestBody productdto p)throws userNotFoundException
    {

        return dser.updateProduct(p);

    }





}
