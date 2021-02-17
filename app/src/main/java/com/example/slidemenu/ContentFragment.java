package com.example.slidemenu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class ContentFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // attachToRoot 如果为 true app 会崩溃
        // The specified child already has a parent. You must call removeView() on the child's parent first.
        View view = inflater.inflate(R.layout.fragment_content, container, false);
        TextView textView = view.findViewById(R.id.show_result);
        assert getArguments() != null;
        // args.putString("text", datas.get(position)); 设置
        String text = getArguments().getString("text");
        textView.setText(text);
        return view;
    }

    // // TODO: Rename parameter arguments, choose names that match
    // // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    // private static final String ARG_PARAM1 = "param1";
    // private static final String ARG_PARAM2 = "param2";
    //
    // // TODO: Rename and change types of parameters
    // private String mParam1;
    // private String mParam2;
    //
    // public ContentFragment() {
    //     // Required empty public constructor
    // }

    // /**
    //  * Use this factory method to create a new instance of
    //  * this fragment using the provided parameters.
    //  *
    //  * @param param1 Parameter 1.
    //  * @param param2 Parameter 2.
    //  * @return A new instance of fragment ContentFragment.
    //  */
    // // TODO: Rename and change types and number of parameters
    // public static ContentFragment newInstance(String param1, String param2) {
    //     ContentFragment fragment = new ContentFragment();
    //     Bundle args = new Bundle();
    //     args.putString(ARG_PARAM1, param1);
    //     args.putString(ARG_PARAM2, param2);
    //     fragment.setArguments(args);
    //     return fragment;
    // }

    // @Override
    // public void onCreate(Bundle savedInstanceState) {
    //     super.onCreate(savedInstanceState);
    //     if (getArguments() != null) {
    //         mParam1 = getArguments().getString(ARG_PARAM1);
    //         mParam2 = getArguments().getString(ARG_PARAM2);
    //     }
    // }
}