package com.example.farid.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by farid on 11/08/15.
 */
public class ImageDialog extends DialogFragment {





    // we try here to create a table of all drawable resources

    public ArrayList<Integer> mSelectedItem;

    public Dialog onCreateDialog (Bundle savedInstanceState)
    {

        mSelectedItem = new ArrayList();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder
                .setTitle(R.string.show_images)


                .setMultiChoiceItems(R.array.Photo_choice, null, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if (isChecked)
                            //answerDialog.getChosen_values(ImageDialog.this).putInt(,imageResIds[which]);
                            mSelectedItem.add(which);
                        else if (mSelectedItem.contains(which))
                            //answerDialog.getChosen_values(ImageDialog.this).remove(choices_string[which].toString());
                            mSelectedItem.remove(Integer.valueOf(which));

                    }
                })

                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        answerDialog.sentArray(ImageDialog.this, mSelectedItem);

                        // here we can create the toast notification
                        Context context = getActivity().getApplicationContext();
                        CharSequence text = "choix d'images";
                        // here we can loop for all values of the array

                        if (mSelectedItem.size() == 0) {
                            text = TextUtils.concat(text, "(vide)");
                        } else {
                            text = TextUtils.concat(text,"(");
                            int tmp = 0;
                            for (tmp = 0; tmp < mSelectedItem.size()-1; tmp++) {
                                text = TextUtils.concat(text,getResources().getStringArray(R.array.Photo_choice)[mSelectedItem.get(tmp)],",");
                            }
                            text = TextUtils.concat(text,getResources().getStringArray(R.array.Photo_choice)[mSelectedItem.get(tmp)]);

                            text = TextUtils.concat(text,")");
                        }
                        int length = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context,text,length);
                        toast.show();


                        answerDialog.onDialogOk(ImageDialog.this);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // nothing to do
                        answerDialog.onDialogCancel(ImageDialog.this);
                    }
                });
      return builder.create();



    }


    //  Interface to implement in the host activity

    public interface DialogAnswerInterface
    {
        public void onDialogOk (DialogFragment dialog);
        public void onDialogCancel (DialogFragment dialog);
        public void sentArray(DialogFragment dialog,ArrayList array);
    }


    // we will use this instance of the interface

    DialogAnswerInterface answerDialog;

    // then we override the onAttach method

    @Override
    public void onAttach (Activity activity)
    {
        super.onAttach(activity);
        // verify the host activity has correctly implements this interface
        try
        {
            answerDialog = (DialogAnswerInterface) activity;
        }
        catch (ClassCastException e)
        {
            // if we are here then that's why the activity doesn't implements the interface
            throw new ClassCastException(activity.toString() + "must implement DialogInterface interface");
        }
    }
}
