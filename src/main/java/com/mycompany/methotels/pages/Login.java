/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.methotels.pages;

import com.mycompany.methotels.data.Rola;
import com.mycompany.methotels.entities.Korisnik;
import com.mycompany.methotels.persistences.KorisnikDao;
import com.mycompany.methotels.services.FacebookService;
import com.mycompany.methotels.services.FacebookServiceInformation;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import java.io.IOException;
import net.smartam.leeloo.common.exception.OAuthProblemException;
import net.smartam.leeloo.common.exception.OAuthSystemException;
import org.apache.tapestry5.annotations.ActivationRequestParameter;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.corelib.components.BeanEditForm;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
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

    @Inject
    private FacebookService facebookService;
    @SessionState
    @Property
    private FacebookServiceInformation facebookServiceInformation;
    @SessionState
    @Property
    private FacebookServiceInformation information;
    @SessionState
    @Property
    private com.restfb.types.User userfb;
    @Property
    @ActivationRequestParameter
    private String code;

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

    public String getFacebookAuthentificationLink() throws OAuthSystemException {
        return facebookService.getFacebookAuthentificationLink();
    }

    @CommitAfter
    public boolean getLoggedInFb() {
        if (facebookServiceInformation.getAccessToken() != null) {
            Korisnik fbuser = new Korisnik(userfb.getId(), " ", "fbIme", "fbPrezime", Rola.Korisnik,
                    userfb.getId());
            
            Korisnik exist = null;
            System.out.println("proverava");
            if (korisnikDao.korisnikPostoji(userfb.getId())) {
                exist = korisnikDao.getKorisnikByUsername(userfb.getId());
            }
            if (exist == null) {
                korisnikDao.registrujKorisnika(fbuser);
                ulogovanKorisnik = fbuser;
                System.out.println("registruje");
            } else {
                ulogovanKorisnik = exist;
                System.out.println("postoji");
            }
        }
        return facebookServiceInformation.getAccessToken() != null;
    }

    @SetupRender
    public void setup() throws IOException, OAuthSystemException,
            OAuthProblemException {
        if (code != null) {
            facebookService.getUserAccessToken(code,
                    information.getAccessToken());
        }
        code = null;
        FacebookClient facebookClient = new DefaultFacebookClient(information.getAccessToken());
        if (information.isLoggedIn()) {
            userfb = facebookClient.fetchObject("me", com.restfb.types.User.class);
        }
    }

}
