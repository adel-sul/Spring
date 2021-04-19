package com.cybertek.aspects;

import com.cybertek.controller.ProductController;
import com.cybertek.entity.Product;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Aspect
@Configuration
public class LoggingAspect {

    Logger logger = LoggerFactory.getLogger(ProductController.class);

    /* Pointcut : It is a predicate expression that determines the Join points,
       hence allowing us to control the advice execution.
       @Pointcut(POINTCUT_EXPRESSION)   */
    @Pointcut("execution(* com.cybertek.controller.ProductController.*(..))")
    public void pointcut(){}    // pointcut signature

    // Advice - what action is taken and when it should be applied
    // Before advice : run before the method
    @Before("pointcut()")
    public void log(){
        logger.info("------ TEST LOG BEFORE ------");
    }

    // Combining Pointcut & Advice
    @Before("execution(* com.cybertek.controller.ProductController.*(..))")
    public void beforeAdvice(){
        logger.info("-----------");
    }

    // EXECUTION - for matching method execution join points.
    @Pointcut("execution(* com.cybertek.controller.ProductController.up*(..))")
    private void anyUpdateOperation(){}

    @Pointcut("execution(* com.cybertek.repository.ProductRepository.findById(Integer))")
    private void anyProductRepositoryFindById(){}

    @Before("anyProductRepositoryFindById()")
    public void beforeProductRepoAdvice(JoinPoint joinPoint){
        logger.info("Before(findById) -> Method {} - Arguments : {} - Target : {}", joinPoint, joinPoint.getArgs(), joinPoint.getTarget());
    }

    @Before("anyUpdateOperation()")
    public void beforeControllerAdvice(JoinPoint joinPoint){
        logger.info("Before -> Method {} - Arguments : {} - Target : {}", joinPoint, joinPoint.getArgs(), joinPoint.getTarget());
    }

    // WITHIN - for matching methods of classes within certain types e.gclasses within a package
    @Pointcut("within(com.cybertek.controller..*)")
    private void anyControllerOperation(){}

    @Pointcut("@within(org.springframework.stereotype.Service)")
    private void anyServiceAnnotatedOperation(){}

    @Before("anyServiceAnnotatedOperation() || anyControllerOperation() ")
    public void beforeControllerAdvice2(JoinPoint joinPoint){
        logger.info("Before -> Method : {} - Arguments : {} - Target : {}", joinPoint, joinPoint.getArgs(), joinPoint.getTarget());
    }

    // ANNOTATION - for matching to join points where the subject(method) of the Joinpoint has the given annotation
    @Pointcut("@annotation(org.springframework.web.bind.annotation.DeleteMapping)")
    private void anyDeleteProductOperation(){}

    @Before("anyDeleteProductOperation()")
    public void beforeControllerAdvice3(JoinPoint joinPoint){
        logger.info("Before -> Method : {} - Arguments : {} - Target : {}", joinPoint, joinPoint.getArgs(), joinPoint.getTarget());
    }

    // AFTER RETURNING - Gets invoked after a matched method finishes its execution and returns a value.
    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    private void anyGetProductOperation(){}

    @AfterReturning(pointcut = "anyGetProductOperation()", returning = "results")
    public void afterReturningControllerAdvice(JoinPoint joinPoint, Product results){
        logger.info("After Returning(Mono Result) -> Method : {} - results :{}", joinPoint.getSignature().toShortString(), results);
    }

    @AfterReturning(pointcut = "anyGetProductOperation()", returning = "results")
    public void afterReturningControllerAdvice2(JoinPoint joinPoint, List<Product> results){
        logger.info("After Returning(List Result) -> Method : {} - results :{}",joinPoint.getSignature().toShortString(),results);
    }

    // AFTER THROWING - Gets invoked after a matched method finishes/aborts its execution by throwing an Exception.
    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    private void anyGetPutProductOperation(){}

    @AfterThrowing(pointcut = "anyGetPutProductOperation()",throwing = "exception")
    public void afterThrowingControllerAdvice(JoinPoint joinPoint,RuntimeException exception){
        logger.info("After Throwing(Send Email to L2 Team) -> Method: {} - Exception : {}", joinPoint.getSignature().toShortString(), exception.getMessage());
    }

    // AFTER (finally) - Executed either the method exits successfully or terminates its execution by throwing an Exception
    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    private void anyGetPutProductOperation2(){}

    @After("anyGetPutProductOperation2()")
    public void afterControllerAdvice(JoinPoint joinPoint){
        logger.info("After finally -> Method : {} - results :{}", joinPoint.getSignature().toShortString());
    }

    @After("anyGetPutProductOperation2()")
    public void afterControllerAdvice2(JoinPoint joinPoint){
        logger.info("After finally -> Method : {} - results :{}", joinPoint.getSignature().toShortString());
    }






}
