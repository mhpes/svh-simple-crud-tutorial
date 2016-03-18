package es.mhp.browser.utils;

/**
 * Created by Edu on 17/03/2016.
 */
public enum ToolButtonType {
    NEW,
    SAVE,
    DELETE,
    BACK;

    @Override
    public String toString() {
        switch(this) {
            case NEW: return "New";
            case SAVE: return "Save";
            case DELETE: return "Delete";
            case BACK: return "Back";
            default: throw new IllegalArgumentException();
        }
    }
}
