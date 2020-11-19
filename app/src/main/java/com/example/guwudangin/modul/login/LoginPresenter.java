package pens.lab.app.belajaractivity.modul.login;

import pens.lab.app.belajaractivity.data.model.User;
import pens.lab.app.belajaractivity.data.source.session.SessionRepository;

/**
 * Created by fahrul on 13/03/19.
 */

public class LoginPresenter implements LoginContract.Presenter{
    private final LoginContract.View view;
    private final SessionRepository sessionRepository;                                              //new

    public LoginPresenter(LoginContract.View view, SessionRepository sessionRepository) {           //add
        this.view = view;
        this.sessionRepository = sessionRepository;                                                 //new
    }

    @Override
    public void start() {
        if(sessionRepository.getSessionData() != null){                                             //new
            view.redirectToProfile();                                                               //jika sudah login langsung masuk profile
        }
    }

    @Override
    public void performLogin(final String email, final String password){
        //proses login

        //if login success
        User loggedUser = new User(email, "TOKEN123456");                                    //new
        sessionRepository.setSessionData(loggedUser);                                               //new

        //then call redirect to profile
        view.redirectToProfile();
    }

}
