package com.ups.riseappstesttask.views.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ups.riseappstesttask.R;
import com.ups.riseappstesttask.model.entities.Contact;

import java.util.List;

/**
 * Created by Yuriy Diachenko on 11.09.2016.
 */
public class ContactsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private IOnItemClickListener onItemClickListener;
    private List<Contact> contacts;
    private int images[] = {R.drawable.ic_face_black_48dp, R.drawable.ic_tag_faces_black_48dp,
            R.drawable.ic_accessibility_black_48dp, R.drawable.ic_favorite_black_48dp,
            R.drawable.ic_pets_black_48dp, R.drawable.ic_grade_black_48dp};

    public ContactsAdapter(List<Contact> contacts) {
        this.contacts = contacts;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_item, parent, false);
        return new ContactViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Contact item = contacts.get(position);

        ((ContactViewHolder) holder).tvPosition.setText(String.valueOf(item.getId()));
        ((ContactViewHolder) holder).tvName.setText(new StringBuffer(item.getFirstName())
                .append(" ")
                .append(item.getSecondName())
                .append(" ")
                .append(item.getSurname()));
        ((ContactViewHolder) holder).ivImage.setImageResource(getImageByIndex(item.getImage()));
    }

    private int getImageByIndex(int index) {
        return images[index];
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public void setOnItemClickListener(IOnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView ivImage;
        public TextView tvPosition;
        public TextView tvName;

        public ContactViewHolder(View itemView) {
            super(itemView);
            ivImage = (ImageView) itemView.findViewById(R.id.ivContactImage);
            tvPosition = (TextView) itemView.findViewById(R.id.tvIndex);
            tvName = (TextView) itemView.findViewById(R.id.tvName);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            if (onItemClickListener != null && adapterPosition >= 0) {
                onItemClickListener.onClick(v, adapterPosition, contacts.get(adapterPosition));
            }
        }
    }

    public interface IOnItemClickListener {
        public void onClick(View view, int position, Contact model);
    }
}
