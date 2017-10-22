package nagoya.united.oiden;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;

import java.util.ArrayList;

public class ReplyActivity extends AppCompatActivity {

    private TextView tex;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply);

        ListView listView = (ListView)findViewById(R.id.listView);
        ArrayList<Tweet> listItems = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);  // 今回はサンプルなのでデフォルトのAndroid Iconを利用
            Tweet tweet = new Tweet();
            tweet.setIcon(bmp);
            tweet.setName("watasi");
            tweet.setTweet("gaaa");
            listItems.add(tweet);
        }

        // 出力結果をリストビューに表示
        ListAdapter adapter = new nagoya.united.oiden.ListAdapter(this, R.layout.tweetrow, listItems);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(onItemClickListener);
    }

    private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            // タップしたアイテムの取得
            ListView listView = (ListView)parent;
            Tweet tweet = (Tweet)listView.getItemAtPosition(position);  // SampleListItemにキャスト
            new MaterialDialog.Builder(ReplyActivity.this)
                    .title(tweet.getName())
                    .content(tweet.getTweet())
                    .inputType(InputType.TYPE_CLASS_TEXT)
                    .input("ヒント", "初期値", new MaterialDialog.InputCallback() {
                        @Override
                        public void onInput(MaterialDialog dialog, CharSequence input) {
                            Toast.makeText(ReplyActivity.this, input, Toast.LENGTH_SHORT).show();
                        }
                    }).show();
        }
    };
}