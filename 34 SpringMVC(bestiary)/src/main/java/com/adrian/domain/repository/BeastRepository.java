package com.adrian.domain.repository;


import com.adrian.domain.objects.Beast;

import java.util.List;

public interface BeastRepository {
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
