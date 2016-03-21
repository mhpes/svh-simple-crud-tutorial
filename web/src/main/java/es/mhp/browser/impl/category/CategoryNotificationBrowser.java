package es.mhp.browser.impl.category;

import es.mhp.browser.impl.AbstractNotificationBrowser;

/**
 * Created by Edu on 20/03/2016.
 */
//@Component
public class CategoryNotificationBrowser extends AbstractNotificationBrowser{

    //public CategoryNotificationBrowser(){}

    public CategoryNotificationBrowser(String caption) {
        super(caption);
    }

    public CategoryNotificationBrowser(String caption, Type type) {
        super(caption, type);
    }

    public CategoryNotificationBrowser(String caption, String description) {
        super(caption, description);
    }

    public CategoryNotificationBrowser(String caption, String description, Type type) {
        super(caption, description, type);
    }

    public CategoryNotificationBrowser(String caption, String description, Type type, boolean htmlContentAllowed) {
        super(caption, description, type, htmlContentAllowed);
    }

    @Override
    public void showHumanizedNotification(String textToShow) {
        this.show(textToShow, Type.HUMANIZED_MESSAGE);
    }
}
