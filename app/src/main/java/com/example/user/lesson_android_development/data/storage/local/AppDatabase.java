package com.example.user.lesson_android_development.data.storage.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.user.lesson_android_development.data.CartItem;
import com.example.user.lesson_android_development.data.MostSoldItem;
import com.example.user.lesson_android_development.data.ProductDescription;
import com.example.user.lesson_android_development.data.ProductImage;
import com.example.user.lesson_android_development.data.Product;
import com.example.user.lesson_android_development.data.ProductTag;
import com.example.user.lesson_android_development.data.Tag;
import com.example.user.lesson_android_development.data.storage.local.cartitem.CartItemDao;
import com.example.user.lesson_android_development.data.storage.local.mostsolditem.MostSoldItemDao;
import com.example.user.lesson_android_development.data.storage.local.productImage.ProductImageDao;
import com.example.user.lesson_android_development.data.storage.local.productdescription.ProductDescriptionDao;
import com.example.user.lesson_android_development.data.storage.local.product.ProductDao;
import com.example.user.lesson_android_development.data.storage.local.tag.TagDao;

import java.io.File;

@Database(entities = {
        Product.class,
        ProductImage.class,
        ProductDescription.class,
        Tag.class,
        ProductTag.class,
        CartItem.class,
        MostSoldItem.class
}, version = 7, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract ProductDao getProductsDao();

    public abstract ProductImageDao getProductImageDao();

    public abstract ProductDescriptionDao getProductDescriptionDao();

    public abstract TagDao getTagDao();

    public abstract CartItemDao getCartItemDao();

    public abstract MostSoldItemDao getMostSoldItemDao();

    public static final Object sLock = new Object();

    public static AppDatabase getInstance(Context context) {
        synchronized (sLock) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "app.db").build();
                pragmaCheckpoint(context);
            }
            return INSTANCE;
        }
    }

    /**
     * cod for db editor
     */
    private static void pragmaCheckpoint(Context context) {
        String dbpath = (context).getDatabasePath("spartan.db").getPath();
        if (ifDBExists(dbpath)) {
            SQLiteDatabase db = SQLiteDatabase.openDatabase(dbpath, null, Context.MODE_PRIVATE);
            Cursor csr = db.rawQuery("PRAGMA wal_checkpoint", null);
            while (csr.moveToNext()) {
                StringBuilder sb = new StringBuilder();
                for (int c = 0; c < csr.getColumnCount(); c++) {
                    sb.append("\n\tColumnName = ").append(csr.getColumnName(c)).append(" Value=").append(csr.getString(c));
                }
                android.util.Log.d("INFO", sb.toString());
            }
            db.close();
        }
    }

    private static boolean ifDBExists(String dbpath) {
        File db = new File(dbpath);
        if (db.exists()) return true;
        File dir = new File(db.getParent());
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return false;
    }
}
