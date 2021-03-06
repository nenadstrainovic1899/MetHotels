package com.mycompany.methotels.components;

import com.mycompany.methotels.entities.Korisnik;
import com.mycompany.methotels.pages.Index;
import org.apache.tapestry5.*;
import org.apache.tapestry5.annotations.*;
import org.apache.tapestry5.ioc.annotations.*;
import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.SymbolConstants;

/**
 * Layout component for pages of application test-project.
 */
@Import(module = "bootstrap/collapse", stylesheet = {"context:css/app.css", "context:css/responsive.css"}, library = {"context:js/bootstrap.min.js"})
public class Layout {

    @Inject
    private ComponentResources resources;

    /**
     * The page title, for the <title> element and the <h1> element.
     */
    @Property
    @Parameter(required = true, defaultPrefix = BindingConstants.LITERAL)
    private String title;

    @Property
    private String pageName;

    @Property
    @Inject
    @Symbol(SymbolConstants.APPLICATION_VERSION)
    private String appVersion;

    @SessionState
    private Korisnik ulogovanKorisnik;

    public boolean getLoggedIn() {
        return ulogovanKorisnik.getUsername() != null;
    }

    public Object onActionFromLogout() {
        ulogovanKorisnik = null;
        return Index.class;
    }

    public String getClassForPageName() {
        return resources.getPageName().equalsIgnoreCase(pageName)
                ? "active"
                : null;
    }

    public String[] getPageNames() {
        return new String[]{"Index", "DodajTipSobe", "DodajSobe", "DodajKorisnika", "RezervacijaSobe", "PretragaTipovaSoba"};
    }

}
