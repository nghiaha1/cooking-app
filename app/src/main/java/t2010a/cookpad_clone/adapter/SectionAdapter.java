package t2010a.cookpad_clone.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import t2010a.cookpad_clone.R;
import t2010a.cookpad_clone.model.home_client.Section;

public class SectionAdapter extends RecyclerView.Adapter {
    private Activity activity;
    private List<Section> sectionList;

    public SectionAdapter(Activity activity, List<Section> sectionList) {
        this.activity = activity;
        this.sectionList = sectionList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = activity.getLayoutInflater().inflate(R.layout.item_section, parent, false);
        SectionViewHolder holder = new SectionViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        SectionViewHolder viewHolder = (SectionViewHolder) holder;
        Section model = sectionList.get(position);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false);

        PostAdapter adapter = new PostAdapter(activity, model.getPostList());
        adapter.setSection(model.getTitle());

        viewHolder.rv_section.setLayoutManager(layoutManager);
        viewHolder.rv_section.setHasFixedSize(true);
        viewHolder.rv_section.setAdapter(adapter);

        viewHolder.tv_section_title.setText(model.getTitle());
        viewHolder.tv_section_note.setText(model.getNote());
    }

    @Override
    public int getItemCount() {
        return sectionList.size();
    }

    public class SectionViewHolder extends RecyclerView.ViewHolder {
        TextView tv_section_title, tv_section_note;
        RecyclerView rv_section;
        public SectionViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_section_title = itemView.findViewById(R.id.tv_section_title);
            tv_section_note = itemView.findViewById(R.id.tv_section_note);
            rv_section = itemView.findViewById(R.id.rv_section);
        }
    }
}
