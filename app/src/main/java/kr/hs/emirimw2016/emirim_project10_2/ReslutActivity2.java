package kr.hs.emirimw2016.emirim_project10_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class ReslutActivity2 extends AppCompatActivity {
    int[] imgVIds = {R.id.imgv_01, R.id.imgv_02, R.id.imgv_03, R.id.imgv_04, R.id.imgv_05, R.id.imgv_06, R.id.imgv_07, R.id.imgv_08, R.id.imgv_09};
    ImageView[] imgv = new ImageView[imgVIds.length];
    ViewFlipper viewFlip;
    int[] imgSrcIds = {R.drawable.nara1, R.drawable.nara2, R.drawable.nara3, R.drawable.nara4, R.drawable.nara5, R.drawable.nara6, R.drawable.nara7, R.drawable.nara8, R.drawable.nara9};
    int[] voteCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reslut2);

        Intent intent = getIntent();
        voteCount = intent.getIntArrayExtra("voteCount");

        viewFlip = findViewById(R.id.view_flip);
        viewFlip.setFlipInterval(1000);
        sorDescImgSrc();
        for (int i=0; i < imgv.length; i++){
            imgv[i] = findViewById(imgVIds[i]);
            imgv[i].setImageResource(imgSrcIds[i]);
        }

        Button btnStart = findViewById(R.id.btn_start);
        Button btnStop = findViewById(R.id.btn_stop);

        btnStart.setOnClickListener(btnListener);
        btnStop.setOnClickListener(btnListener);
    }

    View.OnClickListener btnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_start:
                    viewFlip.startFlipping();
                    break;
                case R.id.btn_stop:
                    viewFlip.stopFlipping();
                    break;
            }
        }
    };

    protected void  sorDescImgSrc(){
        int tempVote = 0;
        int tempSrc = 0;
        for(int i=0; i< voteCount.length-1; i++){
            for(int j=i; j < voteCount.length-1; j++){
                if(voteCount[i] > voteCount[j]){
                    tempVote = voteCount[i];
                    tempSrc = imgSrcIds[i];
                    voteCount[i] = voteCount[j];
                    imgSrcIds[i] = imgSrcIds[j];
                    voteCount[j] = tempVote;
                    imgSrcIds[j] = tempSrc;

                }
            }
        }
        for (int i=0; i< voteCount.length; i++){
            Log.i("Sorting 결과 : ",voteCount[i]+"");
        }
    }
}