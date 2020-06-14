package managedBean;

import java.io.Serializable;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;


@Named
@SessionScoped
public class InternationalizationBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private Locale locale;

    private static boolean english = true;

    @PostConstruct
    public void init() {
        locale = FacesContext.getCurrentInstance().getExternalContext().getRequestLocale();
    }

    public boolean getEnglish() {
        return english;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    //value change event listener
    public void localeChanged() {
        english = !english;
        if (english) {
            locale = new Locale("en");
            FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
        } else {
            locale = new Locale("fr");
            FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
        }
    }
}
