package fragment;

import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;
import static android.os.FileUtils.copy;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.ListFragment;

import Activity.ChitietItem_Activity;
import com.example.igo.R;

import java.util.ArrayList;
import java.util.HashMap;

import Activity.TrangChu_Activity;
import Adapter.SimpleAdapterCustom;

public class Fragment_Luu extends ListFragment {
    ArrayList<HashMap<String,String>> data =new ArrayList<HashMap<String,String>>();

    SimpleAdapter adapter;
    String[] name = {"Hinh 1","Hinh 2","Hinh 3","Hinh 4"};
    int[] image = {R.drawable.dulich1,R.drawable.dulich2,R.drawable.dulich3,R.drawable.dulich4};
    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.layout_fragmentluu, container, false);
        ListView listView = contentView.findViewById(R.id.lv_Save);


        TrangChu_Activity activity = (TrangChu_Activity) getActivity();
        SQLiteDatabase db = getActivity().openOrCreateDatabase("iGo_DB.db",android.content.Context.MODE_PRIVATE ,null);
        Cursor cursor = db.rawQuery("select * from Item,save where Item.IDItem = save.IDItem and save.IDAccount= ?",new String[]{activity.getIDUser()+""});
        //Cursor cursor = db.rawQuery("select * from save where IDAccount= ?",new String[]{"1"});
        HashMap<String,String> map = new HashMap<String,String>();
        cursor.moveToNext();
        while (cursor.isAfterLast() == false)
        {
            map = new HashMap<String,String>();
            map.put("Name",cursor.getString(1));
            map.put("Url",cursor.getString(6));//cursor.getString(6)
            map.put("IdItem",Integer.toString(cursor.getInt(8)));
            data.add(map);
            cursor.moveToNext();
        }
        cursor.close();
        String[] from= {"Name","Url","IdItem"};

        int[] to ={R.id.locate_Name, R.id.locate_img};

        adapter= new SimpleAdapterCustom(getActivity(),data,R.layout.item_save,from,to);
        setListAdapter(adapter);
        return super.onCreateView(inflater,container,savedInstanceState);
    }
    @Override
    public void onStart() {
        super.onStart();
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> av, View v, int pos, long id) {
                goToDetailItem(data.get(pos).get("IdItem"));
            }
        });
    }
    public void goToDetailItem(String iditem)
    {
        Intent intent = new Intent(getContext(), ChitietItem_Activity.class);
        intent.putExtra("idItem", iditem);
        getContext().startActivity(intent);
    }
}
