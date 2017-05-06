package br.com.wellingtoncosta.mymovies.exception;

/**
 * @author Wellington Costa on 01/05/17.
 */

public class ErrorResponse {

    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
