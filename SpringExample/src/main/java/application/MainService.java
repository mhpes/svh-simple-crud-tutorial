package application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import services.IUserService;

/**
 * Created by Edu on 19/02/2016.
 */


@Component
class MainService {
    @Autowired
    IUserService iUserService;

    public IUserService getiUserService() {
        return iUserService;
    }

    public void setiUserService(IUserService iUserService) {
        this.iUserService = iUserService;
    }
}
