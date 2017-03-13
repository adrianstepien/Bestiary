package com.adrian.service;


import com.adrian.domain.objects.Beast;

import java.util.List;

public interface BeastService {
    List<Beast> getAllBeasts();
    Beast getById(int beastId);
    Beast getByName(String name);
    void leaveComment(String comment);
    List<Beast> getGroupByCategory(String category);
    List<Beast> getBeastsFromDb();
    void addBeast(Beast beast);
    void update(Beast beast);
    void delete(int beastId);
}
