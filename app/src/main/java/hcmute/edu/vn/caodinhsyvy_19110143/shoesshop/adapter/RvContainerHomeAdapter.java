//package hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.adapter;
//
//import android.content.Context;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.FrameLayout;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import hcmute.edu.vn.caodinhsyvy_19110143.shoesshop.R;
//
//public class RvContainerHomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
//
//    private Context context;
//    private List<View> lstViews;
//    private List<HomeViewHolder> lstHomeViewHolders;
//    private int i;
//
//    @NonNull
//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        return this.lstHomeViewHolders.get(i++);
//        //return new HomeViewHolder(View.inflate(parent.getContext(), R.layout.home_frame_layout, null));
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
////        FrameLayout homeFrameLayout = holder.itemView.findViewById(R.id.homeFrameLayout);
////        View view = lstViews.get(position);
////        homeFrameLayout.addView(view);
//    }
//
//    @Override
//    public int getItemCount() {
//        return lstViews.size();
//    }
//
//    public RvContainerHomeAdapter(Context context, List<View> lstViews) {
//        this.context = context;
//        this.lstViews = lstViews;
//        this.lstHomeViewHolders = new ArrayList<>();
//        this.i = 0;
//        for (View view : lstViews) {
//            HomeViewHolder homeViewHolder = new HomeViewHolder(View.inflate(this.context, R.layout.home_frame_layout, null));
//            lstHomeViewHolders.add(homeViewHolder);
//            FrameLayout homeFrameLayout = homeViewHolder.itemView.findViewById(R.id.homeFrameLayout);
//            homeFrameLayout.addView(view);
//        }
//    }
//
//    public class HomeViewHolder extends RecyclerView.ViewHolder {
//
//        private View itemView;
//
//        public HomeViewHolder(@NonNull View itemView) {
//            super(itemView);
//            this.itemView = itemView;
//            itemView.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT));
//        }
//
//        public void setView(View view) {
//            itemView = view;
//        }
//
//        public View getView() {
//            return this.itemView;
//        }
//    }
//}
