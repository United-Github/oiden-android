package nagoya.united.oiden;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import nagoya.united.oiden.TimeLineFragment.OnListFragmentInteractionListener;
import nagoya.united.oiden.model.KokoViewItemModel;

import java.util.List;
import java.util.Date;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

/**
 * {@link RecyclerView.Adapter} that can display a {@link KokoViewItemModel} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyTimeLineRecyclerViewAdapter extends RecyclerView.Adapter<MyTimeLineRecyclerViewAdapter.ViewHolder> {

    private final List<KokoViewItemModel> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyTimeLineRecyclerViewAdapter(List<KokoViewItemModel> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_timeline, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        //holder.mIdView.setText(mValues.get(position).id);
        holder.mUserNameView.setText(mValues.get(position).userName);
        holder.mSumLikeView.setText(Integer.toString(mValues.get(position).sumLike));
        holder.mContentView.setText(mValues.get(position).content);
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Tokyo"), Locale.JAPAN);
        calendar.setTime(mValues.get(position).created);
        String tempDate = calendar.get(Calendar.YEAR)+"/"+calendar.get(Calendar.MONTH)+"/"+calendar.get(Calendar.DATE);
        holder.mCreateDate.setText(tempDate);
        holder.mUserIcon.setImageBitmap(mValues.get(position).userIcon);


        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        //public final TextView mIdView;
        public final TextView mUserNameView;
        public final TextView mSumLikeView;
        public final TextView mContentView;
        public final TextView mCreateDate;
        public final ImageView mUserIcon;
        public KokoViewItemModel mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            //mIdView = (TextView) view.findViewById(R.id.listId);
            mUserNameView = (TextView) view.findViewById(R.id.listId);
            mSumLikeView = (TextView) view.findViewById(R.id.likeValue);
            mContentView = (TextView) view.findViewById(R.id.listContent);
            mCreateDate = (TextView) view.findViewById(R.id.createDate);
            mUserIcon = (ImageView) view.findViewById(R.id.listIcon);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
