package com.ifsc.cigerds.Classes;

import android.widget.CheckBox;
import android.widget.EditText;

public class AdaptadorCheckBox {

    private CheckBox checkBox;
    private EditText editText;

    public AdaptadorCheckBox(CheckBox checkBox, EditText editText) {
        this.checkBox = checkBox;
        this.editText = editText;
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public EditText getEditText() {
        return editText;
    }
}

