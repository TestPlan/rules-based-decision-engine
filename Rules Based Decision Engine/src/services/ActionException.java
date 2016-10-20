package services;

/**
 * Created by shiv on 10/19/2016.
 */
public class ActionException extends Exception {
    private String exception;

    public ActionException(){
        this.exception = "";
    }

    public ActionException(String exception)
    {
        this.exception = exception;
    }

    public String getException()
    {
        return this.exception;
    }

    public String toString()
    {
        return " " + "Exception: " + this.getException();
    }

}
