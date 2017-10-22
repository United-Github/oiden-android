package nagoya.united.oiden;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.text.InputType;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.Theme;

/**
 * Created by ragro on 2017/10/14.
 */

public class OpenDetailKoko {
    public static void Show(Bitmap icon, String username, String content, Context context) {
        new MaterialDialog.Builder(context)//ここでgetApplicationContextするとボタンのコンテキストを受け取ってしまうため
                .title("つぶやき")//たぶん人名
                .content("あれこれ")//ここにタッチした投稿の内容をいれてください
                .positiveText("返信")
                .iconRes(R.mipmap.ic_launcher)//アイコン
                .theme(Theme.DARK)//テーマの設定。お好みでどうぞ
                .onPositive(new MaterialDialog.SingleButtonCallback(){
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which){//返信の文面
                        new MaterialDialog.Builder(context)//ここでgetApplicationContextするとボタンのコンテキストを受け取ってしまうため
                                .title("タイトル")//人名？
                                .content("内容")//ここに返信する投稿の内容を入れてほしい
                                .iconRes(R.mipmap.ic_launcher)//アイコン
                                .inputType(InputType.TYPE_CLASS_TEXT)
                                .input("返信", "", (dialog1, input) -> {
                                    Toast.makeText(context, input, Toast.LENGTH_SHORT).show();//ここでデータ送信
                                }).show();
                    }
                })
                .negativeText("つけたし")
                .onNegative(new MaterialDialog.SingleButtonCallback(){
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which){//付け足しの文面
                        new MaterialDialog.Builder(context)//ここでgetApplicationContextするとボタンのコンテキストを受け取ってしまうため
                                .title("タイトル")
                                .content("内容")//上と同じく付け足しする内容をいれてほしい
                                .iconRes(R.mipmap.ic_launcher)//アイコン
                                .inputType(InputType.TYPE_CLASS_TEXT)
                                .input("つけたし", "", new MaterialDialog.InputCallback() {
                                    @Override
                                    public void onInput(MaterialDialog dialog, CharSequence input) {
                                        Toast.makeText(context, input, Toast.LENGTH_SHORT).show();//ここでデータ送信
                                    }
                                }).show();
                    }
                })
                .neutralText("キャンセル")//デザイン的に付け足しと返信を横に並べたかった
                .show();
    }
}
