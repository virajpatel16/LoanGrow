package com.example.loangrow.Activity;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.loangrow.R;

import java.util.List;

public class ViewPagerAdapter extends RecyclerView.Adapter<ViewPagerAdapter.ViewHolder> {

    private List<Integer> imageList;

    public ViewPagerAdapter(List<Integer> imageList) {
        this.imageList = imageList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_layout, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(holder.imageView.getContext())
                .load(imageList.get(position))
                .override(holder.imageView.getWidth(), holder.imageView.getHeight())
                .into(holder.imageView);

        if (position == 0) {
            holder.title.setText("");
            holder.text.setText("A compilation of free financial calculator \n GST calculateor ,VAT Calculator,RD calculator\nFD Calculator and more");
        } else if (position == 1) {
            holder.title.setText("");
            holder.text.setText("All in one financial Calculator works as great \n Planner and usefull loans");
        } else if (position == 2) {
            holder.title.setText("");
            holder.text.setText("An EMI  determine their monthly installment amount based on laon amount interest \n Calculator and tenure.");
            // Add more cases if needed
        } else if (position == 3) {
            holder.title.setText("FD Calculator");
            holder.text.setText("User= thi EMI calculator for secured or unsecured loans to\n get a quick estimate of your monthly instalment be\n well prepared before avaling a loan successfully repay your \n loan in comfortable instalments.");
            // Add more cases if needed
        } else if (position == 4) {
            holder.title.setText("Repayment Calculator");
            holder.text.setText("An EMI  determine their monthly installment amount based on laon amount interest \n Calculator and tenure.");
            // Add more cases if needed
        } else if (position == 5) {
            holder.title.setText("Repayment Calculator");
            holder.text.setText("An EMI  determine their monthly installment amount based on laon amount interest \n Calculator and tenure.");
            // Add more cases if needed
        } else if (position == 6) {
            holder.title.setText("Repayment Calculator");
            holder.text.setText("An EMI  determine their monthly installment amount based on laon amount interest \n Calculator and tenure.");
            // Add more cases if needed
        } else if (position == 7) {
            holder.title.setText("Repayment Calculator");
            holder.text.setText("An EMI  determine their monthly installment amount based on laon amount interest \n Calculator and tenure.");
            // Add more cases if needed
        } else {
            holder.title.setText("Default Text");
            holder.text.setText("Default Title");
        }
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView text, title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            text = itemView.findViewById(R.id.textdisciption);
            title = itemView.findViewById(R.id.texttitle);
        }
    }
}