package nagoya.united.oiden.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 裕亮 on 2017/10/24.
 */

public class KokoContent {
    public static final List<KokoViewItemModel> KOKOITEMS = new ArrayList<KokoViewItemModel>();
    private static final int COUNT = 25;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createKokoItem(i));
        }
    }

    private static void addItem(KokoViewItemModel item) {
        KOKOITEMS.add(item);
    }

    private static KokoViewItemModel createKokoItem(int position) {
        KokoViewItemModel kokoViewItemModel = new KokoViewItemModel();

        return kokoViewItemModel;
    }
}
