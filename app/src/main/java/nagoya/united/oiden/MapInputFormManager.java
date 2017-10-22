package nagoya.united.oiden;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by ragro on 2017/10/21.
 */

public class MapInputFormManager {
    private ImageButton selectImageButton;
    private ImageButton selectSendMessage;
    private View rootView;
    private AppCompatActivity activity;
    public static final int SELECT_IMAGE_REQUEST_CODE = 300;

    public MapInputFormManager(AppCompatActivity activity) {
        this.activity = activity;
        rootView = activity.getWindow().getDecorView();

        this.bindView();
    }

    private void bindView(){
        selectImageButton = rootView.findViewById(R.id.select_image);
        selectSendMessage = rootView.findViewById(R.id.send_message);
        selectImageButton.setOnClickListener(view -> {
            Intent intentGallery;
            intentGallery = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intentGallery.addCategory(Intent.CATEGORY_OPENABLE);
            intentGallery.setType("image/jpeg");
            activity.startActivityForResult(intentGallery, 200);
        });
    }

    public void onSelectImage(Intent data) {
    }


}
