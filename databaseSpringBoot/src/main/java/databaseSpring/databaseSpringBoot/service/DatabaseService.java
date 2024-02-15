package databaseSpring.databaseSpringBoot.service;


import databaseSpring.databaseSpringBoot.Dto.productdto;
import databaseSpring.databaseSpringBoot.controller.errorinlist;
import databaseSpring.databaseSpringBoot.entity.product;
import databaseSpring.databaseSpringBoot.exception.userNotFoundException;
import databaseSpring.databaseSpringBoot.repository.databaserepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DatabaseService {
    @Autowired
    private databaserepository repo;

    public productdto saveProduct(productdto p)
    {

        product p1=new product();
        p1.setId(p.getId());
        p1.setName(p.getName());
        p1.setQuantity(p.getQuantity());
        p1.setPrice(p.getPrice());
        p1.setMobile(p.getMobile());
        repo.save(p1);
        p.setId(p1.getId());

         return p;


    }

    public List<product> saveProducts(List<productdto> products)
    {
        List<product>list1=new ArrayList<>();

        for(int i=0;i<products.size();i++)
        {
            product p=new product();
            p.setName(products.get(i).getName());
            p.setQuantity(products.get(i).getQuantity());
            p.setPrice(products.get(i).getPrice());
            p.setMobile(products.get(i).getMobile());
            list1.add(p);
        }

        return repo.saveAll(list1);
    }

    public List<productdto>getProducts()throws userNotFoundException
    {
        List<product>list=new ArrayList<>();

        list.addAll(repo.findAll());
        if(list.size()!=0) {
            List<productdto> list1 = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                productdto p = new productdto();
                p.setId(list.get(i).getId());
                p.setName(list.get(i).getName());
                p.setQuantity(list.get(i).getQuantity());
                p.setPrice(list.get(i).getPrice());
                p.setMobile(list.get(i).getMobile());
                list1.add(p);
            }
            return list1;
        }
        else
        {
            throw new userNotFoundException("No product Exist in the Database");
        }

    }

    public productdto getProductById(int id) throws userNotFoundException {
        productdto p1=new productdto();

        product p= repo.findById(id).orElse(null);

        if(p!=null) {
            p1.setId(p.getId());
            p1.setName(p.getName());
            p1.setPrice(p.getPrice());
            p1.setQuantity(p.getQuantity());
            p1.setMobile(p.getMobile());
            return p1;
        }

        else {
            throw new userNotFoundException("user not find with id "+id);
        }
    }

    public List<productdto>getProductByName(String name)throws userNotFoundException
    {
        List<product>list=new ArrayList<>();
        list.addAll(repo.findByName(name));
        if(list.size()!=0) {
            List<productdto> list1 = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                productdto p = new productdto();
                p.setId(list.get(i).getId());
                p.setName(list.get(i).getName());
                p.setQuantity(list.get(i).getQuantity());
                p.setPrice(list.get(i).getPrice());
                p.setMobile(list.get(i).getMobile());
                list1.add(p);
            }
            return list1;
        }
        else {
            throw new userNotFoundException("Sorry no product of this name is exist....");
        }

    }

    public String deleteProduct(int id)throws userNotFoundException
    {
        product p= repo.findById(id).orElse(null);
        if(p!=null) {
            repo.deleteById(id);
            return "Prouct of id ==>" + id + "is deleted!!";
        }
        else {
            throw new userNotFoundException("We can't delete as product not find with id "+id);
        }
    }

    public productdto updateProduct(productdto p)throws userNotFoundException
    {
        product existing=repo.findById(p.getId()).orElse(null);
        if(existing!=null) {
            existing.setName(p.getName());
            existing.setPrice(p.getPrice());
            existing.setQuantity(p.getQuantity());
            repo.save(existing);
            productdto p1 = new productdto();
            p1.setId(p.getId());
            p1.setName(p.getName());
            p1.setPrice(p.getPrice());
            p1.setQuantity(p.getQuantity());
            p1.setMobile(p.getMobile());
            return p1;
        }
        else{
            throw new userNotFoundException("we can't update as user not find with id "+p.getId());
        }


    }


}
