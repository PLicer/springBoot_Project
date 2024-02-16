package databaseSpring.databaseSpringBoot.Aspect;


import databaseSpring.databaseSpringBoot.Dto.productdto;
import databaseSpring.databaseSpringBoot.entity.product;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Date;

@Aspect
@Component
public class productAspect {

    @Before(value = "execution(* databaseSpring.databaseSpringBoot.controller.databasecontroller.*(..))")
    public void beforeAspect(JoinPoint joinPoint)
    {
        System.out.println("Message------------------->Request to "+joinPoint.getSignature()+"started at "+new Date());

    }

    @After(value = "execution(* databaseSpring.databaseSpringBoot.controller.databasecontroller.*(..))")
    public void afterAspect(JoinPoint joinPoint)
    {
        System.out.println("Message------------------->Request to "+joinPoint.getSignature()+"ended at "+new Date());

    }

    @Before(value = "execution(* databaseSpring.databaseSpringBoot.service.DatabaseService.*(..))")
    public void beforeAspectService(JoinPoint joinPoint)
    {
        System.out.println("Message------------------->Request to "+joinPoint.getSignature()+"started at "+new Date());

    }

    @After(value = "execution(* databaseSpring.databaseSpringBoot.service.DatabaseService.*(..))")
    public void afterAspectService(JoinPoint joinPoint)
    {
        System.out.println("Message------------------->Request to "+joinPoint.getSignature()+"ended at "+new Date());

    }

    @AfterReturning(value = "execution(* databaseSpring.databaseSpringBoot.service.DatabaseService.saveProduct(..))" ,returning="p")
    public void AfterReturnAddproduct(JoinPoint joinPoint,productdto p)
    {


            System.out.println("Product with id " + p.getId() + " is added");

    }

    @AfterThrowing(value = "execution(* databaseSpring.databaseSpringBoot.service.DatabaseService.getProductById(..))",throwing = "ex")
    public void AfterThrowAddproduct(JoinPoint joinPoint,Exception ex)
    {

            System.out.println(ex);


    }

}
