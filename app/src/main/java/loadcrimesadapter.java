import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

import com.example.dushan.crimeandmissingrepoter.LoadCrimeReport;
import com.example.dushan.crimeandmissingrepoter.R;
import com.example.dushan.crimeandmissingrepoter.locrimedetails;

import java.util.ArrayList;

public class loadcrimesadapter extends ArrayAdapter {
    Context c;
    ArrayList<LoadCrimeReport> list;


    public loadcrimesadapter(Context context ,ArrayList<LoadCrimeReport> ar) {

        super(context, R.layout.loadcrimelistadapter,ar);
        c= context;
        list = ar;
    }
}
