package es.mhp.browser.impl.address;

import es.mhp.browser.impl.AbstractNotificationBrowser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Edu on 20/03/2016.
 */
//@Component
public class AddressNotificationBrowser extends AbstractNotificationBrowser{

    //public AddressNotificationBrowser(){}

    public AddressNotificationBrowser(String caption) {
        super(caption);
    }

    public AddressNotificationBrowser(String caption, Type type) {
        super(caption, type);
    }

    public AddressNotificationBrowser(String caption, String description) {
        super(caption, description);
    }

    public AddressNotificationBrowser(String caption, String description, Type type) {
        super(caption, description, type);
    }

    public AddressNotificationBrowser(String caption, String description, Type type, boolean htmlContentAllowed) {
        super(caption, description, type, htmlContentAllowed);
    }

    @Override
    public void showHumanizedNotification(String textToShow) {
        this.show(textToShow, Type.HUMANIZED_MESSAGE);
    }
}
