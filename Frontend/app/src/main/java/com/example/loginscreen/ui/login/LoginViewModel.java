package com.example.loginscreen.ui.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import android.util.Patterns;

import com.example.loginscreen.data.LoginRepository;
import com.example.loginscreen.data.Result;
import com.example.loginscreen.data.model.LoggedInUser;
import com.example.loginscreen.R;


/**
 * The main Events activity page for Sea Salt
 * @author Chandler Jurenic and Aaron Goff
 * LoginViewModel sets values in order to return appropriate results to user with regards to
 * username and password information, whether it has passed or why it has failed
 */
public class LoginViewModel extends ViewModel {

    private MutableLiveData<LoginFormState> loginFormState = new MutableLiveData<>();
    private MutableLiveData<LoginResult> loginResult = new MutableLiveData<>();
    private LoginRepository loginRepository;

    /**
     * @param loginRepository
     */
    LoginViewModel(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    LiveData<LoginFormState> getLoginFormState() {
        return loginFormState;
    }

    LiveData<LoginResult> getLoginResult() {
        return loginResult;
    }

    /**
     * login sets the username and password and sends it to the backend, if it passes then it sends a message
     * to the user stating the login has failed
     * @param  username
     * @param password
     */
    public void login(String username, String password) {
        // can be launched in a separate asynchronous job
        Result<LoggedInUser> result = loginRepository.login(username, password);

        if (result instanceof Result.Success) {
            LoggedInUser data = ((Result.Success<LoggedInUser>) result).getData();
            loginResult.setValue(new LoginResult(new LoggedInUserView(data.getDisplayName())));
        } else {
            loginResult.setValue(new LoginResult(R.string.login_failed));
        }
    }

    /**
     * loginDataChanged returns values for bubbles that pop up to give user feedback on whether the inputs
     * provided have passed or failed.
     * @param username
     * @param password
     */
    public void loginDataChanged(String username, String password) {
        if (!isUserNameValid(username)) {
            loginFormState.setValue(new LoginFormState(R.string.invalid_username, null));
        } else if (!isPasswordValid(password)) {
            loginFormState.setValue(new LoginFormState(null, R.string.invalid_password));
        } else {
            loginFormState.setValue(new LoginFormState(true));
        }
    }

    /**
     * isUserNameValid is a placeholder username validation check
     * @param username
     */
    private boolean isUserNameValid(String username) {
        if (username == null) {
            return false;
        }
        if (username.contains("@")) {
            return Patterns.EMAIL_ADDRESS.matcher(username).matches();
        } else {
            return !username.trim().isEmpty();
        }
    }

    /**
     * isPasswordValid is a placeholder password validation check
     * @param password
     */
    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 2;
    }
}