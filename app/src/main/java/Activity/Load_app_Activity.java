package Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.igo.R;

public class Load_app_Activity extends AppCompatActivity {

    ProgressBar mProgressBar;
    TextView progressTextView;
    private static final int DELAY_TIME = 1000; // 3 giây
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_app);
        //setContentView(R.layout.activity_main);
        getSupportActionBar().hide();


//        // Tạo một đối tượng Handler
//        Handler handler = new Handler();
//
//        // Sử dụng Handler để chuyển đổi sang hoạt động mới sau 3 giây
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                // Tạo một Intent để chuyển sang hoạt động mới
//                Intent intent = new Intent(Load_app_Activity.this, DangNhap_Activity.class);
//                startActivity(intent);
//
//                // Đóng hoạt động hiện tại (nếu bạn muốn)
//                finish();
//            }
//        }, DELAY_TIME);

        progressTextView = (TextView) findViewById(R.id.progressTextView);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        int percent = 100; // Giá trị phần trăm bạn muốn hiển thị
        mProgressBar.setProgress(percent);
        final int progress = 0;
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int p = progress; p <= 100; p++) {
                        Thread.sleep(50); // Thời gian delay giữa các bước
                        int p1 = p;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                mProgressBar.setProgress(p1);
                                progressTextView.setText(p1 + "%");

                                if (p1 == 100) {
                                    // Chuyển sang Activity khác
                                    Intent intent = new Intent(Load_app_Activity.this, DangNhap_Activity.class);
                                    startActivity(intent);
                                    finish(); // Kết thúc Activity hiện tại
                                }
                            }
                        });
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
    }
}