package mkharsa.com.weatherformation;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by mkharsa on 11/06/16.
 */
public class ResultActivity extends Activity {



    WeatherInformation weatherInformation;
ImageView imgCode;
    TextView lblCity;
    TextView lblTemp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_activity);
        weatherInformation=new WeatherInformation();

        imgCode=(ImageView)findViewById(R.id.img_code_header);
        imgCode.setImageResource(getIconFromCode(weatherInformation.getCurrentCode()));

        lblCity=(TextView)findViewById(R.id.lbl_city);
        lblCity.setText(weatherInformation.getCurrentCountry() + ", " + weatherInformation.getCurrentCity());

        lblTemp=(TextView)findViewById(R.id.temp_header);

        lblTemp.setText(String.valueOf(weatherInformation.getCurrentTemp()));

    }

    int getIconFromCode(int code) {
        switch (code) {
            case 1:
            case 2:
            case 3:
            case 4:
                return R.drawable.icon_11d;
            case 5:
            case 6:
                return R.drawable.icon_09d;
            case 7:
            case 8:
            case 9:
            case 10:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
                return R.drawable.icon_13d;
            case 11:
            case 12:
                return R.drawable.icon_10d;
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
                return R.drawable.icon_03d;
            case 25:
            case 26:
            case 27:
            case 28:
                return R.drawable.icon_04d;
            case 29:
            case 30:
                return R.drawable.icon_02d;
            case 31:
            case 32:
            case 33:
            case 34:
            case 36:
                return R.drawable.icon_01d;
            case 35:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
            case 43:
            case 44:
            case 45:
            case 46:
            case 47:
                return R.drawable.icon_09d;
            default:
                return R.drawable.icon_01d_small;
        }
    }

}
