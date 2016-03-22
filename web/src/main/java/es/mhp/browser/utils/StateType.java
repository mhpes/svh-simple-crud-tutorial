package es.mhp.browser.utils;

/**
 * Created by Edu on 17/03/2016.
 */
public enum StateType {
    SELECTEDROW,
    INITIAL,
    EDIT,
    SAVE,
    NEW,
    DELETE;

    @Override
    public String toString() {
        switch (this) {
            case SELECTEDROW:
                return "Selected Row";
            case INITIAL:
                return "Initial";
            case EDIT:
                return "Edit";
            case SAVE:
                return "Save";
            case NEW:
                return "New";
            case DELETE:
                return "Delete";
            default:
                throw new IllegalArgumentException();
        }
    }
}
