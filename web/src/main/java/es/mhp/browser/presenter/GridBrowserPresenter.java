package es.mhp.browser.presenter;

import com.vaadin.ui.Grid;
import com.vaadin.ui.components.grid.ItemClickListener;
import es.mhp.browser.utils.StateType;
import es.mhp.views.AbstractView;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by Edu on 23/03/2016.
 */
@Component
@Scope("session")
public class GridBrowserPresenter {

    public GridBrowserPresenter() {
    }

    public void addDoubleClickListenerToGrid(Grid grid, AbstractView view) {
        grid.addItemClickListener((ItemClickListener) event -> {
            if (event.getMouseEventDetails().isDoubleClick()) {
                grid.select(event.getItem());
                view.updateView(StateType.EDIT);
            } else {
                view.updateView(StateType.SELECTEDROW);
            }
        });
    }
}
