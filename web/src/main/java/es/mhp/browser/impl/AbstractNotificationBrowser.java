package es.mhp.browser.impl;

import com.vaadin.ui.Notification;
import es.mhp.browser.IBrowserNotification;

/**
 * Created by Edu on 20/03/2016.
 */
public abstract class AbstractNotificationBrowser extends Notification implements IBrowserNotification {

    public AbstractNotificationBrowser(String caption) {
        super(caption);
    }

    public AbstractNotificationBrowser(String caption, Type type) {
        super(caption, type);
    }

    public AbstractNotificationBrowser(String caption, String description) {
        super(caption, description);
    }

    public AbstractNotificationBrowser(String caption, String description, Type type) {
        super(caption, description, type);
    }

    public AbstractNotificationBrowser(String caption, String description, Type type, boolean htmlContentAllowed) {
        super(caption, description, type, htmlContentAllowed);
    }
}
