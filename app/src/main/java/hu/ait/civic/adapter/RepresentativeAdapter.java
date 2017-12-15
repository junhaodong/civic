package hu.ait.civic.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import hu.ait.civic.MainActivity;
import hu.ait.civic.R;
import hu.ait.civic.data.Representative;

public class RepresentativeAdapter extends RecyclerView.Adapter<RepresentativeAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public CardView cardView;
        public TextView tvTitle;
        public TextView tvName;
        public CircleImageView ivPhoto;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.card_view);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvName = itemView.findViewById(R.id.tvName);
            ivPhoto = itemView.findViewById(R.id.ivPhoto);
        }
    }

    private List<Representative> representativeList;
    private Context context;

    public RepresentativeAdapter(List<Representative> representativeList, Context context) {
        this.representativeList = representativeList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.row_representative, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int position) {
        final Representative representative = getRepresentative(position);

        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) context).showRepresentativeDetails(representative);
            }
        });
        viewHolder.tvTitle.setText(representative.getTitle());
        viewHolder.tvName.setText(representative.getName());

        String photoUrl = representative.getPhotoUrl();
        if (StringUtils.isNotBlank(photoUrl)) {
            Glide.with(context).load(photoUrl).into(viewHolder.ivPhoto);
        } else {
            viewHolder.ivPhoto.setImageResource(R.mipmap.ic_launcher);
        }

        // TODO: Single source this
        String party = representative.getParty();
        if (party != null && party.equals("Republican")) {
            viewHolder.ivPhoto.setBorderColor(Color.parseColor("#E91D0E"));
        } else if (party != null && party.equals("Democratic")){
            viewHolder.ivPhoto.setBorderColor(Color.parseColor("#3333FF"));
        } else {
            viewHolder.ivPhoto.setBorderColor(Color.parseColor("#a4a4a4"));
        }
    }

    @Override
    public int getItemCount() {
        return representativeList.size();
    }

    public void addRepresentatives(Representative[] representatives) {
        representativeList.addAll(Arrays.asList(representatives));
        notifyDataSetChanged();
    }

    public void clearRepresentatives() {
        representativeList.clear();
        notifyDataSetChanged();
    }

    private Representative getRepresentative(int index) {
        return representativeList.get(index);
    }
}
