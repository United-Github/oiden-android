package nagoya.united.oiden;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by takato on 2017/10/22.
 */

public class ListAdapter extends ArrayAdapter<Tweet> {
    private int mResource;
    private List<Tweet> mItems;
    private LayoutInflater mInflater;

    /**
     * コンストラクタ
     * @param context コンテキスト
     * @param resource リソースID
     * @param items リストビューの要素
     */
    public ListAdapter(Context context, int resource, List<Tweet> items) {
        super(context, resource, items);

        mResource = resource;
        mItems = items;
        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;

        if (convertView != null) {
            view = convertView;
        }
        else {
            view = mInflater.inflate(mResource, null);
        }

        // リストビューに表示する要素を取得
        Tweet item = mItems.get(position);

        // サムネイル画像を設定
        ImageView icon = (ImageView)view.findViewById(R.id.icon);
        icon.setImageBitmap(item.getIcon());

        // タイトルを設定
        TextView name = (TextView)view.findViewById(R.id.name);
        name.setText(item.getName());

        TextView tweet = (TextView)view.findViewById(R.id.tweet);
        tweet.setText(item.getTweet());

        return view;
    }

}
