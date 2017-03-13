package com.adrian.service.impl;

import com.adrian.domain.objects.Beast;
import com.adrian.domain.repository.BeastRepository;
import com.adrian.service.BeastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeastServiceImpl implements BeastService{
    @Autowired
    private BeastRepository beastRepository;

    @Override
    public List<Beast> getAllBeasts(){
        return beastRepository.getAllBeasts();
    }

    @Override
    public Beast getById(int beastId){
        return beastRepository.getById(beastId);
    }

    @Override
    public Beast getByName(String name){
        return beastRepository.getByName(name);
    }

    @Override
    public void leaveComment(String comment){
        beastRepository.leaveComment(comment);
    }

    @Override
    public List<Beast> getGroupByCategory(String category){
        return beastRepository.getGroupByCategory(category);
    }

    @Override
    public List<Beast> getBeastsFromDb(){
        return beastRepository.getBeastsFromDb();
    }

    @Override
    public void addBeast(Beast beast){
        beastRepository.addBeast(beast);
    }

    @Override
    public void delete(int beastId){
        beastRepository.delete(beastId);
    }

    @Override
    public void update(Beast beast){
        beastRepository.update(beast);
    }
}
