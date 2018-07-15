package com.zzq.demo.controller;

import com.zzq.demo.aspect.HttpAspect;
import com.zzq.demo.domain.Girl;
import com.zzq.demo.repository.GirlRepository;
import com.zzq.demo.service.GirlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class GirlController {

    private final static Logger logger = LoggerFactory.getLogger(GirlController.class);
    @Autowired
    private GirlRepository girlRepository;

    @Autowired
    private GirlService girlService;
    /*
    * 查询所有女生列表
    * */
    @GetMapping(value = "/girls")
    public List<Girl> girlList(){
        logger.info("girlList()");
        return girlRepository.findAll();
    }
/*
* 创建
* */
    @PostMapping(value = "/girls")
    public Girl girlAdd(@Valid Girl girl ,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            System.out.println(bindingResult.getFieldError().getDefaultMessage());
            return null;
        }


        return girlRepository.save(girl);
    }
    //查询
    @GetMapping(value = "/girls/{id}")
    public Optional<Girl> girlFindOne(@PathVariable("id") Integer id){
        return girlRepository.findById(id);

    }
    @GetMapping(value = "/girls/age/{age}")
    public List<Girl> girlFindByAge(@PathVariable("age") Integer age){
        return girlRepository.findByAge(age);
    }


    //跟新
   @PutMapping(value = "/girls/{id}")
    public Girl girlUpdate(@PathVariable("id") Integer id,
                           @RequestParam("cupSize") String cupSize,
                           @RequestParam("age") Integer age){
        Girl girl = new Girl();
        girl.setId(id);
        girl.setAge(age);
        girl.setCupSize(cupSize);
        return girlRepository.save(girl);
   }

    //删除
    @DeleteMapping(value = "/girls/{id}")
    public void girlDelete(@PathVariable("id") Integer id){
        girlRepository.deleteById(id);
    }
    @PostMapping(value = "/girls/two")
    public void getTwo(){
        girlService.insertTwo();
    }
}
