package es.mhp.browser.presenter;

import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.Grid;
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
        grid.addItemClickListener((ItemClickEvent.ItemClickListener) event -> {
            if (event.isDoubleClick()){
                grid.select(event.getItemId());
                view.updateView(StateType.EDIT);
            } else {
                view.updateView(StateType.SELECTEDROW);
            }
        });
    }
}
