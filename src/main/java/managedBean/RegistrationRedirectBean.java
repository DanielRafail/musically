package managedBean;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;

@Named(value = "redirectbean")
@SessionScoped
public class RegistrationRedirectBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String location;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void valueChangeMethod(ValueChangeEvent e) {
        location = e.getNewValue().toString();
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(location + ".xhtml");
        } catch (IOException ex) {
            Logger.getLogger(RegistrationRedirectBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
