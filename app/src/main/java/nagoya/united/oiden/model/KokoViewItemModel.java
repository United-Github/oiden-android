package nagoya.united.oiden.model;

import android.graphics.Bitmap;
import com.google.android.gms.maps.model.LatLng;
import java.util.Date;

/**
 * Created by ragro on 2017/10/21.
 */

public class KokoViewItemModel {
        public Integer id; // ココのID
        public Integer userId; // 投稿ユーザID
        public String userName;// 投稿ユーザ名
        public Bitmap userIcon;// 投稿ユーザアイコン
        public Date created; // 投稿日
        public String content; // 内容
        public String attach; // 添付画像
        public Integer sumChildlen; // 返信の数
        public Integer sumLike; // いいねの数
        public LatLng latlng; // 位置
        public String section; // 区画名
        public Integer parentId; // 親のID
}
