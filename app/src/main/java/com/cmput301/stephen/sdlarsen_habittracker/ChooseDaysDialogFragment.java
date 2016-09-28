package com.cmput301.stephen.sdlarsen_habittracker;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import java.util.ArrayList;

/**
 * Created by stephen on 2016-09-26.
 *
 * Code taken from https://developer.android.com/guide/topics/ui/dialogs.html
 * on 2016-09-26.
 */

public class ChooseDaysDialogFragment extends DialogFragment {

    private ArrayList selectedDays = new ArrayList();


    public interface onSaveListener {
        void onSaveClick(DialogFragment dialog);
    }

    // Use this instance of the interface to deliver action events
    onSaveListener mListener;

    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            this.mListener = (onSaveListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement onSaveListener");
        }
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //final ArrayList selectedDays = new ArrayList();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(R.string.repeat_on)
                .setMultiChoiceItems(R.array.days_array, null,
                        new DialogInterface.OnMultiChoiceClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which,
                                                boolean isChecked){
                                if (isChecked){
                                    selectedDays.add(which);
                                } else if (selectedDays.contains(which)){
                                    selectedDays.remove(Integer.valueOf(which));
                                }
                            }
                        });

        builder.setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                mListener.onSaveClick(ChooseDaysDialogFragment.this);
                // TODO on-save
            }
        });

        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int id){
                // save nothing, exit window
            }
        });

        return builder.create();
    }

    public ArrayList<String> getChoices() {
        return selectedDays;
    }
}
