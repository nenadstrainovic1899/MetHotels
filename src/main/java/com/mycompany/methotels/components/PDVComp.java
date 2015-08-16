/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.methotels.components;

import java.text.DecimalFormat;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;

/**
 *
 * @author NenadS
 */
public class PDVComp {

    @Parameter(required = true)
    @Property
    private Double cena;

    public String getCenaWithPDV() {
        DecimalFormat df = new DecimalFormat("#.###");
        return df.format((cena * 0.2) + cena);
    }
}
