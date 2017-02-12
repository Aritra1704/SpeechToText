package com.arpaul.speechtotext.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arpaul.speechtotext.R;
import com.arpaul.speechtotext.dataobject.PhraseDO;
import com.arpaul.utilitieslib.ColorUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ARPaul on 12-02-2017.
 */

public class PhrasesAdapter extends RecyclerView.Adapter<PhrasesAdapter.ViewHolder> {

    private Context context;
    private List<PhraseDO> arrPhraseDO = new ArrayList<>();

    public PhrasesAdapter(Context context, List<PhraseDO> arrPhraseDO) {
        this.context = context;
        this.arrPhraseDO = arrPhraseDO;
    }

    public void refresh(List<PhraseDO> arrPhraseDO) {
        this.arrPhraseDO = arrPhraseDO;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_phrase, parent, false);//farmlist_adapter_cell
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final PhraseDO objPhraseDO = arrPhraseDO.get(position);

        holder.tvPhrase.setText(objPhraseDO.Phrase);
        holder.tvPhraseIteration.setText(objPhraseDO.PhraseIteration + "");

        if(objPhraseDO.PhraseIteration > 0)//Highlight if iteration found
            holder.mView.setBackgroundColor(ColorUtils.getColor(context, R.color.colorYellow));
    }

    @Override
    public int getItemCount() {
        if(arrPhraseDO != null)
            return arrPhraseDO.size();

        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;

        public final TextView tvPhrase;
        public final TextView tvPhraseIteration;

        public ViewHolder(View view) {
            super(view);
            mView = view;

            tvPhrase            = (TextView) view.findViewById(R.id.tvPhrase);
            tvPhraseIteration   = (TextView) view.findViewById(R.id.tvPhraseIteration);
        }

        @Override
        public String toString() {
            return "";
        }
    }
}
