/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.methotels.pages;

import com.mycompany.methotels.data.Soba;
import java.util.ArrayList;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;

/**
 *
 * @author NenadS
 */
public class DodajSobe {
    @Property
    @Persist
    private ArrayList<Soba> listaSoba;
    
    @Property
    private Soba soba;
    
    void onActivate(){
        if (listaSoba == null) {
            listaSoba = new ArrayList<Soba>();
        }
    }
    
    Object onSuccess(){
        listaSoba.add(soba);
        return this;
    }
}
