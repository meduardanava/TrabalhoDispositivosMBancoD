package com.example.aplicativopontodevenda.dao;

import java.util.ArrayList;

public interface GerenricDao<Object> {

    long insert(Object obj);
    long update(Object obj);
    long delete(Object obj);
    ArrayList<Object> getAll();
    Object getById(int id);
}
