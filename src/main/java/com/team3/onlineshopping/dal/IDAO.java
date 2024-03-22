package com.team3.onlineshopping.dal;

import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

/**
 *
 * @author Admin
 */
public interface IDAO<T> {
    public List<T> getAll();
    
    public void add(T t);
    
    public void update(T t);
    
    public void delete(int id);
    
    public T getById(int id);
}
