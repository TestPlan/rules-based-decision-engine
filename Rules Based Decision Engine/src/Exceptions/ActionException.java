package Exceptions;

/**
 * Created by shiv on 10/19/2016.
 */
public class ActionException extends Exception {
    /*
     * An Exception Class for Action.
      * @String exception is a String of Exception
      * getException()  returns the exception
      * toString()      returns the String Format of the Exception
     */

    //Stores exception as a String
    private String exception;

    //Constructor which has empty exception
    public ActionException(){
        this.exception = "";
    }

    //Constructor which takes a exception
    public ActionException(String exception)
    {

        this.exception = exception;
    }
    //returns exception
    public String getException()

    {
        return this.exception;
    }
    //returns string of the exception
    public String toString()
    {
        return " " + "Exception: " + this.getException();
    }

}
