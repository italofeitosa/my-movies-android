package br.com.wellingtoncosta.mymovies.validation.validators;

import android.support.design.widget.TextInputLayout;

/**
 * @author Wellington Costa on 01/05/17.
 */
abstract class Validator {

    TextInputLayout layout;

    String pattern;

    String errorMessage;


    Validator(TextInputLayout layout, String pattern, String errorMessage) {
        this.layout = layout;
        this.pattern = pattern;
        this.errorMessage = errorMessage;
    }

    Validator(TextInputLayout layout, String errorMessage) {
        this.layout = layout;
        this.pattern = "";
        this.errorMessage = errorMessage;
    }

    abstract boolean validate();

    /*boolean validate() {
        boolean isValid = true;
        EditText editText = layout.getEditText();

        if (editText != null) {
            String value = editText.getText().toString();

            if (!pattern.isEmpty()) {
                boolean found = Pattern.matches(pattern, value);
                if (!found) {
                    setErrorLayout();
                    isValid = false;
                } else {
                    clearErrorLayout();
                }
            } else if (value.isEmpty()) {
                setErrorLayout();
                isValid = false;
            } else {
                clearErrorLayout();
            }
        }

        return isValid;
    }*/

    private void setErrorLayout() {
        layout.setErrorEnabled(true);
        layout.setError(errorMessage);
    }

    private void clearErrorLayout() {
        layout.setErrorEnabled(false);
        layout.setError(null);
    }
}