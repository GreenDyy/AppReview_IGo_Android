package Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.igo.R;

import java.util.ArrayList;
import java.util.List;

import Adapter.*;
import Model.model_itemSpiner_dangki;

public class DangKy_Activity extends AppCompatActivity {
    EditText txtUserName,txtPassword,txtRePassword;
    Button cmdRegister;
    SQLiteDatabase database;
    TextView kiemtra, txtdangnhap;
    Spinner spinner;

    SpinerDangKiAdapter mSpinerDangKiAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        getSupportActionBar().hide();
        txtdangnhap = (TextView) findViewById(R.id.txtdangnhap);
        kiemtra = (TextView) findViewById(R.id.txtkiemtra);
        txtUserName = (EditText) findViewById(R.id.edt_usernamedk);
        txtPassword = (EditText) findViewById(R.id.edt_passworddk);
        txtRePassword = (EditText) findViewById(R.id.edt_nhaplaipassworddk);
        cmdRegister = (Button) findViewById(R.id.btn_Register);
        //pinner = (Spinner) findViewById(R.id.spinnercauhoibaomat);
        //cmdGoToLogin = (TextView)findViewById(R.id.cmdGoToLogin) ;
        txtUserName.requestFocus();
        database = openOrCreateDatabase("iGo_DB.db",MODE_PRIVATE,null);

        cmdRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("UserName", txtUserName.getText().toString().trim());
                contentValues.put("PassWord", txtPassword.getText().toString().trim());

                if (TextUtils.isEmpty(txtUserName.getText()) || txtUserName.getText().equals(txtUserName.getHint()) == true){
                    kiemtra.setVisibility(View.VISIBLE);
                    kiemtra.setTextColor(Color.RED);
                    kiemtra.setText("Tên đăng nhập không được bỏ trống!");
                    txtUserName.requestFocus();
                    return;
                }else if(TextUtils.isEmpty(txtPassword.getText()) || txtPassword.getText().equals(txtPassword.getHint()) == true){
                    kiemtra.setVisibility(View.VISIBLE);
                    kiemtra.setTextColor(Color.RED);
                    kiemtra.setText("Mật khẩukhông được bỏ trống!");
                    txtPassword.requestFocus();
                }else if( TextUtils.isEmpty(txtRePassword.getText()) || txtRePassword.getText().equals(txtRePassword.getHint()) == true){
                    kiemtra.setVisibility(View.VISIBLE);
                    kiemtra.setTextColor(Color.RED);
                    kiemtra.setText("Mật khẩu không được bỏ trống!");
                    txtRePassword.requestFocus();
                }
                 else if (!txtPassword.getText().toString().trim().equals(txtRePassword.getText().toString().trim())) {
                    kiemtra.setVisibility(View.VISIBLE);
                    kiemtra.setTextColor(Color.RED);
                    kiemtra.setText("Mật khẩu không khớp!");
                } else {
                    if (database.insert("AccountUser", null, contentValues) == -1) {
                        kiemtra.setVisibility(View.VISIBLE);
                        kiemtra.setTextColor(Color.RED);
                        kiemtra.setText("Tên đăng nhập đã tồn tại!");
                        txtUserName.requestFocus();
                        //Toast.makeText(DangKy_Activity.this, txtUserName.getText().toString().trim() + "tài khoản đã tồn tại !", Toast.LENGTH_SHORT).show();
                    } else {
                        Cursor cursor = database.query("AccountUser", null, "UserName = ? and PassWord = ?", new String[]{txtUserName.getText().toString().trim(), txtPassword.getText().toString().trim()}, null, null, null);
                        cursor.moveToNext();
                        Intent intent = new Intent(DangKy_Activity.this, TrangChu_Activity.class);
                        intent.putExtra("IDUser", cursor.getInt(0));
                        Toast.makeText(DangKy_Activity.this, "Đăng ký thàng công !", Toast.LENGTH_SHORT).show();
                        cursor.close();
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        Intent intent1 = new Intent(DangKy_Activity.this,DangNhap_Activity.class);
                        startActivity(intent1);
                    }
                }
            }
        });

        txtdangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DangKy_Activity.this,DangNhap_Activity.class);
                startActivity(intent);
                finish();
            }
        });

        //Spinner spinner = findViewById(R.id.spinnercauhoibaomat);
//        mSpinerDangKiAdapter = new SpinerDangKiAdapter(this, R.layout.item_spiner_selected, getList());
//        spinner.setAdapter(mSpinerDangKiAdapter);

//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                //Toast.makeText(DangKy_Activity.this, mSpinerDangKiAdapter.getItemId(i).getName(), Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
//        spinner.setSelection(0); // Chọn mục "a0" làm mục đầu tiên được hiển thị
    }

    private List<model_itemSpiner_dangki> getList() {
        List<model_itemSpiner_dangki> md = new ArrayList<>();
        md.add(new model_itemSpiner_dangki("Sở thích của bạn?"));
        md.add(new model_itemSpiner_dangki("Tên gọi khác?"));
        md.add(new model_itemSpiner_dangki("Ba bạn tên gì?"));
        md.add(new model_itemSpiner_dangki("Mẹ bạn tên gì?"));
        return md;
    }
}