package com.alifilali.trackercovid19;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import static com.alifilali.trackercovid19.GameView.screenRatioX;
import static com.alifilali.trackercovid19.GameView.screenRatioY;
public class Flight {
    int toShoot = 0;
    boolean isGoingUp = false;
    int x, y, width, height, wingCounter = 0, shootCounter = 1;
    Bitmap flight1, flight2, shoot1, shoot2, shoot3, shoot4, shoot5, dead;
    private GameView gameView;

    Flight (GameView gameView, int screenY, Resources res) {

        this.gameView = gameView;

        flight1 = BitmapFactory.decodeResource(res, R.drawable.fly1);
        flight2 = BitmapFactory.decodeResource(res, R.drawable.fly2);


        y = screenY / 2;
        x = (int) (64 * screenRatioX);

    }

    Bitmap getFlight () {


        if (wingCounter == 0) {
            wingCounter++;
            return flight1;
        }
        wingCounter--;

        return flight2;
    }

}
