package com.app.fragments.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.fragments.R;
import com.app.fragments.ui.components.Forms;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class FormsAdpter extends RecyclerView.Adapter<FormsAdpter.FormViewHolder> {

    Context context;
    List<Forms> formsList;
    public FormsAdpter(Context context, List<Forms> formsList) {
        this.context = context;
        this.formsList = formsList;
    }

    @NonNull
    @Override
    public FormViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_view_form, parent, false);
        return new FormViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FormViewHolder holder, int position) {
        var form = formsList.get(position);
        holder.bind(form);
    }

    @Override
    public int getItemCount() {
        return formsList.size();
    }

    public static class FormViewHolder extends RecyclerView.ViewHolder {
        public FormViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        protected void bind(Forms forms) {
            TextView titleView = itemView.findViewById(R.id.title);
            titleView.setText(forms.getTitle());
            String title = titleView.getText().toString().trim();

            TextInputLayout fieldOneLayout = itemView.findViewById(R.id.textInputContainerOne);
            EditText fieldOneView = fieldOneLayout.getEditText();
            if (fieldOneView != null) {
                fieldOneView.setHint(forms.getField1());
            }

            TextInputLayout fieldTwoLayout = itemView.findViewById(R.id.textInputContainerTwo);
            EditText fieldTwoView = fieldTwoLayout.getEditText();
            if (fieldTwoView != null) {
                fieldTwoView.setHint(forms.getField2());
            }

            TextInputLayout fieldThreeLayout = itemView.findViewById(R.id.textInputContainerThree);
            EditText fieldThreeView = fieldThreeLayout.getEditText();
            if (fieldThreeView != null) {
                fieldThreeView.setHint(forms.getField3());
            }
        }
    }
}
