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

        boolean wrapInScrollView = true;
       MaterialDialog dialog=new MaterialDialog.Builder(context)//ここでgetApplicationContextするとボタンのコンテキストを受け取ってしまうため
                .title("つぶやき")//たぶん人名
                .negativeText("キャンセル")
                .customView(R.layout.custom_view,wrapInScrollView)
                .show();
        View view=dialog.getCustomView();
        ImageButton mes;
        ImageButton like;
        ImageButton pos;
        Context mcontext=context;
        mes=(ImageButton)view.findViewById(R.id.mes);
        mes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(mcontext,ReplyActivity.class);
//                startActivity(intent);
                shift=true;
            }
        });
        like=(ImageButton)view.findViewById(R.id.like);
        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        pos=(ImageButton)view.findViewById(R.id.pos);
        pos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return shift;
    }
}
