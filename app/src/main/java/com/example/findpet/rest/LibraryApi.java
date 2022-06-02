package com.example.findpet.rest;

import com.example.findpet.Map.NewPoint;

public interface LibraryApi {
    void fillPoint();

    void addPoint(NewPoint point);
    void updatePoint(int id, NewPoint point);
    void deletePoint(int id);
}
