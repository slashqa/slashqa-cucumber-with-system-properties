package com.slashqa.context;
import com.slashqa.utils.SessionUtils;

public class Context {
    private SessionUtils mySession;

    public Context(SessionUtils theSession){
        mySession = theSession;
    }

    public SessionUtils getSession(){
        return mySession;
    }
}