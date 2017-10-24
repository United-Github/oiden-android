package nagoya.united.oiden;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.text.InputType;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.Theme;

/**
 * Created by ragro on 2017/10/14.
 */

public class OpenDetailKoko {
        static boolean shift=false;
    public static boolean Show(Bitmap icon, String username, String content, Context context) {

        boolean wrapInScrollView = true;//地図上の吹き出しタッチ時のポップアップ画面
       MaterialDialog dialog=new MaterialDialog.Builder(context)//ここでgetApplicationContextするとボタンのコンテキストを受け取ってしまうため
                .title("つぶやき")
                .negativeText("キャンセル")
                .customView(R.layout.custom_view,wrapInScrollView)
                .show();
        View view=dialog.getCustomView();
        ImageButton mes;
        ImageButton like;
        ImageButton pos;
        Context mcontext=context;
        mes=(ImageButton)view.findViewById(R.id.mes);//吹き出しボタン
        mes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(mcontext,ReplyActivity.class);
//                startActivity(intent);
                shift=true;//ここで画面の遷移のためのフラグをセット
            }
        });
        like=(ImageButton)view.findViewById(R.id.like);//イイねボタン
        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ここにイイね後の処理を記述
            }
        });
        pos=(ImageButton)view.findViewById(R.id.pos);//ロケーションボタン
        pos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ここに入力後を記述
            }
        });
        return shift;
    }
}
