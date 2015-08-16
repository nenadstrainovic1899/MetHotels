/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.methotels.pages;

import com.mycompany.methotels.entities.Korisnik;
import com.mycompany.methotels.persistences.KorisnikDao;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.BeanEditForm;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author NenadS
 */
public class Login {

    @Property
    private Korisnik loginKorisnik;

    @Inject
    private KorisnikDao korisnikDao;

    @SessionState
    private Korisnik ulogovanKorisnik;

    @Component
    private BeanEditForm form;

    Object onActivate() {
        if (ulogovanKorisnik.getUsername() != null) {
            return Index.class;
        }
        return null;
    }

    public String getMD5Hash(String yourString) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(yourString.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (Exception e) {
            return "";
        }
    }

    Object onSuccess() {
        String password = getMD5Hash(loginKorisnik.getPassword());
        Korisnik u = korisnikDao.proveriKorisnika(loginKorisnik.getUsername(), password);
        if (u != null) {
            ulogovanKorisnik = u;
            return Index.class;
        } else {
            form.recordError("Uneli ste pogresne parametre");
            return null;
        }
    }

}
