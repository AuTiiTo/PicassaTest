package globant.picassatest.user_interface;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import globant.picassatest.R;
import globant.picassatest.common.MainItem;

/**
 * @author s.ruiz
 */

public class FirstFragment extends Fragment {

    private static final String ARGUMENT_IMAGE_RES_ID = "imageResId";
    private static final String ARGUMENT_NAME = "name";
    private static final String ARGUMENT_DESCRIPTION = "description";
    private static final String ARGUMENT_URL = "url";

    public FirstFragment() {
    }

    public static FirstFragment newInstance(MainItem item) {

        final Bundle args = new Bundle();
        args.putInt(ARGUMENT_IMAGE_RES_ID, item.getResourseImageId());
        args.putString(ARGUMENT_NAME, item.getName());
        args.putString(ARGUMENT_DESCRIPTION, item.getDescription());
        args.putString(ARGUMENT_URL, item.getUrl());
        final FirstFragment fragment = new FirstFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_first, container, false);
        final TextView name = (TextView) view.findViewById(R.id.desc_name);
        final ImageView image = (ImageView) view.findViewById(R.id.desc_image);
        final TextView desc = (TextView) view.findViewById(R.id.desc_description);

        final Bundle args = getArguments();
        name.setText(args.getString(ARGUMENT_NAME));
        Picasso.with(view.getContext())
                .load(args.getInt(ARGUMENT_IMAGE_RES_ID))
                .into(image);
        final String description = String.format(getString(R.string.description_format), args.getString(ARGUMENT_DESCRIPTION), args.getString
                (ARGUMENT_URL));
        desc.setText(description);
        return view;
    }
}
