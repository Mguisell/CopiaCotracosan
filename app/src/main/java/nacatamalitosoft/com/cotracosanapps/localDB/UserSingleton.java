package nacatamalitosoft.com.cotracosanapps.localDB;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

import static nacatamalitosoft.com.cotracosanapps.localDB.UserContract.UserEntry.*;

public final class UserSingleton {
    private static User currentUser;

    public static synchronized User getCurrentUser(Context context){
        Cursor cursor = null;
        if(currentUser == null)
        {
            try{
                UserDbHelper helper = new UserDbHelper(context);
                cursor = helper.getLocalLoggedUser();
                if(cursor.moveToNext()){
                    currentUser = new User(
                            cursor.getString(cursor.getColumnIndex(ID)),
                            cursor.getString(cursor.getColumnIndex(SOCIOID)),
                            cursor.getString(cursor.getColumnIndex(USER)),
                            cursor.getString(cursor.getColumnIndex(EMAIL)),
                            cursor.getString(cursor.getColumnIndex(ISLOGGED)),
                            cursor.getString(cursor.getColumnIndex(AVATAR)),
                            cursor.getString(cursor.getColumnIndex(ROL))
                            );
                }else{
                    currentUser = null;
                }
            }catch (Exception ex){
                System.out.println(ex.getMessage());
            }finally {
                if(cursor != null)
                    cursor.close();
            }
        }
        return currentUser;
    }

    public static void GuardarNuevoUsuario(Context context, User userResult) {
        UserDbHelper db = new UserDbHelper(context);
        db.AddNewUserLogged(userResult);
    }

    public static void CerrarSesion(Context context) {
        try{
            UserDbHelper db = new UserDbHelper(context);
            getCurrentUser(context);
            db.CerrarSesion(currentUser.getId());
            currentUser = null;
        }catch(Exception e){
            currentUser = null;
        }finally {

        }
    }

    public static void ActualizarImagen(String temp, Context context) {
        currentUser.setAvatar("data:image/jpeg," +temp);
        UserDbHelper db = new UserDbHelper(context);
        db.ActualizarImagen(currentUser.getId(), currentUser.toContentValues());
    }
}