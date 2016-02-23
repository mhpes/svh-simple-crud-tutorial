package es.mhp.ui;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import es.mhp.services.IServicePetshop;
import es.mhp.views.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

@SuppressWarnings("serial")
@Theme("valo")
@SpringUI
public class MainUI extends UI {
    @Autowired
    private IServicePetshop servicePetshop;

    Navigator navigator;
    protected static final String MAINVIEW = "Main";
    protected static final String ADDRESSVIEW = "Addresses";
    protected static final String CATEGORYVIEW = "Categories";
    protected static final String ERRORVIEW = "Error";
    protected static final String ITEMVIEW = "Items";
    protected static final String PRODUCTVIEW = "Products";
    protected static final String SELLERVIEW = "Sellers";
    protected static final String TAGVIEW = "Tags";
    protected static final String ZIPLOCATIONVIEW = "ZipLocations";

    public MainUI() {
    }

    //EntityManagerFactory emf = Persistence.createEntityManagerFactory("PetshopUnit");

    @Override
    protected void init(VaadinRequest request) {
        createPetshopService();

        getPage().setTitle("PetShop example");

        navigator = new Navigator(this, new MainView());
        setupNavigator();

        //LAYOUT GENERAL
        HorizontalLayout generalLayout = generateGeneralLayout();
        setContent(generalLayout);
    }

    private void setupNavigator(){
        registerViews();
    }

    private void registerViews(){
        navigator.addView("", MainView.class);
        navigator.addView(ADDRESSVIEW, AddressView.class);
        navigator.addView(CATEGORYVIEW, CategoryView.class);
        navigator.addView(ITEMVIEW, ItemView.class);
        navigator.addView(MAINVIEW, MainView.class);
        navigator.addView(PRODUCTVIEW, ProductView.class);
        navigator.addView(SELLERVIEW, SellerContactInfoView.class);
        navigator.addView(TAGVIEW, TagView.class);
        navigator.addView(ZIPLOCATIONVIEW, ZipLocationView.class);
    }

    private HorizontalLayout generateGeneralLayout() {
        HorizontalLayout generalLayout = new HorizontalLayout();

        //LAYOUT MENU
        Layout menuLayout = createMenu();

        //LAYOUT VIEW
        Layout viewLayout = createView();

        generalLayout.addComponent(menuLayout);
        generalLayout.addComponent(viewLayout);

        return generalLayout;
    }

    private Layout createMenu() {
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setMargin(true);

        verticalLayout.addComponent(createCustomButton(ADDRESSVIEW));
        verticalLayout.addComponent(createCustomButton(CATEGORYVIEW));
        verticalLayout.addComponent(createCustomButton(ITEMVIEW));
        verticalLayout.addComponent(createCustomButton(MAINVIEW));
        verticalLayout.addComponent(createCustomButton(PRODUCTVIEW));
        verticalLayout.addComponent(createCustomButton(SELLERVIEW));
        verticalLayout.addComponent(createCustomButton(TAGVIEW));
        verticalLayout.addComponent(createCustomButton(ZIPLOCATIONVIEW));

        return verticalLayout;
    }

    private Component createCustomButton(String view) {
        Button button = new Button(view,
                (Button.ClickListener) event -> {
                    navigator.navigateTo(view);
                });
        return button;
    }

    private Layout createView() {

        //LAYOUT GENERAL
        VerticalLayout generalLayout = new VerticalLayout();

        //VISTA PARA MOSTRAR
        //Layout view = createViewTable();
        //generalLayout.addComponent(view);

        //FORM PARA EDITAR
        /*FormLayout form = createForm();
        generalLayout.addComponent(form);*/
        
        return generalLayout;
    }

    private void createPetshopService() {
        ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(VaadinServlet.getCurrent().getServletContext());
        servicePetshop = context.getBean(IServicePetshop.class);
    }
}

