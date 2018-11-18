package com.example.ifritee.rdconverter;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;

public class DAnswerOpenFile extends DialogFragment implements OnClickListener {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().setTitle("Открыть файл?");
        View View_o = inflater.inflate(R.layout.dialog_answer_open_file, null);
        View_o.findViewById(R.id.btnNo).setOnClickListener(this);

        return View_o;
    }

    @Override
    public void onClick(View View_o) {
        if(View_o.getId() == R.id.btnNo) {
            dismiss();
        }


    }

    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
    }

    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
    }
}
