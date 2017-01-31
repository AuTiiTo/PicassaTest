package globant.picassatest.user_interface;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import globant.picassatest.R;
import globant.picassatest.common.MainItem;

/**
 * @author s.ruiz
 */

public class SecondFragment extends Fragment {

    private ArrayList<MainItem> mainItems;

    private OnItemSelected mListener;

    public static SecondFragment newInstance() {
        return new SecondFragment();
    }

    public SecondFragment() {}

    class MainAdapter extends RecyclerView.Adapter<MainViewHolder> {
        private LayoutInflater mainLayoutInflater;

        public MainAdapter(Context context) {
            mainLayoutInflater = LayoutInflater.from(context);
        }

        @Override
        public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MainViewHolder(mainLayoutInflater.inflate(R.layout.item_main, parent, false));
        }

        @Override
        public void onBindViewHolder(MainViewHolder holder, int position) {
            final MainItem item = mainItems.get(position);
            holder.setData(item.getResourseImageId(), item.getName());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onItemSelected(item);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mainItems.size();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof OnItemSelected){
            mListener = (OnItemSelected) context;
        } else {
            throw new ClassCastException(context.toString() + " must implement OnItemSelected");
        }

        mainItems = new ArrayList();
        //
        final Resources resources = context.getResources();
        String[] mNames = resources.getStringArray(R.array.names);
        String[] mDesc = resources.getStringArray(R.array.descriptions);
        String[] mUrl = resources.getStringArray(R.array.urls);

        final TypedArray typedArray = resources.obtainTypedArray(R.array.images);
        final int imageCount = mNames.length;

        for(int i = 0; i < imageCount; i++) {
            MainItem item = new MainItem(typedArray.getResourceId(i, 0)
                    , mNames[i], mDesc[i], mUrl[i]);
            mainItems.add(item);

        }
        typedArray.recycle();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_second, container, false);

        final Activity activity = getActivity();
        final RecyclerView recycler = (RecyclerView) view.findViewById(R.id.recycler_view);
        recycler.setLayoutManager(new GridLayoutManager(activity, 2));
        recycler.setAdapter(new MainAdapter(activity));
        return view;
    }

    class MainViewHolder extends RecyclerView.ViewHolder {
        // Views
        private ImageView mImageView;
        private TextView mNameTextView;

        private MainViewHolder(View itemView) {
            super(itemView);

            // Get references to image and name.
            mImageView = (ImageView) itemView.findViewById(R.id.main_image);
            mNameTextView = (TextView) itemView.findViewById(R.id.main_text);
        }

        private void setData(int imageResId, String name) {
            Picasso.with(itemView.getContext())
                    .load(imageResId)
                    .resize(650,650)
                    .into(mImageView);
//            mImageView.setImageResource(imageResId);
            mNameTextView.setText(name);
        }
    }

    public interface OnItemSelected {
        void onItemSelected(MainItem item);
    }
}
