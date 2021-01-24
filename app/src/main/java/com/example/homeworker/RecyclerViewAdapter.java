package com.example.homeworker;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private ArrayList<String> nImageNames;
    private ArrayList<String> nImages;
    private Context nContext;
    private static List<String> mEditTextValues = new ArrayList<>();

    public ArrayList<String> getnImageNames() {
        return nImageNames;
    }

    public RecyclerViewAdapter(ArrayList<String> kImageNames, ArrayList<String> kImages, Context context) {
        this.nImageNames = kImageNames;
        this.nImages = kImages;
        this.nContext = context;
        for (int i = 1; i <= nImageNames.size(); i++) {
            mEditTextValues.add("");
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.each_container, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(nContext)
                .asBitmap()
                .load(nImages.get(position))
                .into(holder.img);

        holder.img_name.setText(nImageNames.get(position));
        holder.mEditText.setTag(position);
        holder.mEditText.setText(mEditTextValues.get(position));

    }

    @Override
    public int getItemCount() {
        return nImageNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CircleImageView img;
        private ConstraintLayout constraintLayout;
        private TextView img_name, population;
        private Button button;
        private EditText mEditText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            button = itemView.findViewById(R.id.btn);
            mEditText = itemView.findViewById(R.id.etText);
            mEditText.addTextChangedListener(new TextWatcher() {
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                public void afterTextChanged(Editable editable) {
                }

                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (mEditText.getTag() != null) {
                        mEditTextValues.set((int) mEditText.getTag(), charSequence.toString());
                        population.setText(charSequence.toString());
                    }
                }
            });
            button.setOnClickListener(v -> population.setText(mEditText.getText().toString()));

            img = itemView.findViewById(R.id.image);
            constraintLayout = itemView.findViewById(R.id.parent_layout);
            img_name = itemView.findViewById(R.id.image_name);
            population = itemView.findViewById(R.id.result);
            constraintLayout.setOnClickListener(v -> Toast.makeText(nContext, "vedite population", Toast.LENGTH_SHORT).show());
        }
    }
}