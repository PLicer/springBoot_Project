package databaseSpring.databaseSpringBoot.controller;
import databaseSpring.databaseSpringBoot.Dto.productdto;
import databaseSpring.databaseSpringBoot.entity.product;
import databaseSpring.databaseSpringBoot.exception.userNotFoundException;
import databaseSpring.databaseSpringBoot.service.DatabaseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@Tag(name = "DataBase Controller" ,description = "")
@RestController
@RequestMapping("/api/products")

public class databasecontroller {

    @Autowired
    private DatabaseService dser;





    @PostMapping("/addproduct")
    @Operation(summary = "AddProduct", description = "Add your Product in DB")
    public productdto addproduct(@Valid @RequestBody productdto p)
    {


        return dser.saveProduct(p);

    }
    @PostMapping("/addproducts")
    @Operation(summary = "AddProducts", description = "Add more than one Product in DB at one opearation")
    public List<productdto> addproducts(@Valid @RequestBody List<productdto> list, BindingResult bindingResult) throws MethodArgumentNotValidException {
        if (bindingResult.hasErrors()) {
            throw new MethodArgumentNotValidException(null, bindingResult);
        }

        dser.saveProducts(list);
        return list;
    }
    @GetMapping("/getproductid/{id}")
    @Operation(summary = "Get Product By id", description = "Get your Product by entering the id corresponding to that product")
    public productdto getproduct(@PathVariable int id)throws userNotFoundException
    {
        return dser.getProductById(id);
    }

    @GetMapping("/getproducts")
    @Operation(summary = "Get all Products", description = "Get All products present in Database")
    public List<productdto> getproducts()throws userNotFoundException
    {
        return dser.getProducts();
    }

    @GetMapping("/getproduct/{name}")
    @Operation(summary = "Get Product by name", description = "Get your Product by entering the name of product")
    public List<productdto> getproductbyname(@PathVariable String name)throws userNotFoundException
    {
        return dser.getProductByName(name);
    }

    @DeleteMapping("/deleteproduct/{id}")
    @Operation(summary = "Delete your product", description = "Delete your product by providing its corresponding Id")
    public String deleteproduct(@PathVariable int id) throws userNotFoundException {
        return dser.deleteProduct(id);
    }


    @PutMapping("/update")
    @Operation(summary = "Update your product", description = "Update your existing product values")
    public productdto updateproduct(@RequestBody productdto p)throws userNotFoundException
    {

        return dser.updateProduct(p);

    }





}
