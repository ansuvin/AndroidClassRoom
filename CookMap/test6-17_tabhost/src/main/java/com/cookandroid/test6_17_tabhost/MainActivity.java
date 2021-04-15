package com.cookandroid.test6_17_tabhost;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TabActivity;
import android.os.Bundle;
import android.widget.TabHost;

@SuppressWarnings("deprecation")    //아이스크림 샌드위치 이후를 사용하면 경고가 나오기때문에 막기위한 코드 (없어두 됨)
public class MainActivity extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabHost tabHost = getTabHost();

        TabHost.TabSpec tabspecSong = tabHost.newTabSpec("SONG").setIndicator("음악별");
        tabspecSong.setContent(R.id.tabSong);
        tabHost.addTab(tabspecSong);

        TabHost.TabSpec tabSpecArtist = tabHost.newTabSpec("ARTSIT").setIndicator("가수별");
        tabSpecArtist.setContent(R.id.tabArtist);
        tabHost.addTab(tabSpecArtist);

        TabHost.TabSpec tabSpecAlbum = tabHost.newTabSpec("ALBUM").setIndicator("앨범별");
        tabSpecAlbum.setContent(R.id.tabAlbum);
        tabHost.addTab(tabSpecAlbum);

        tabHost.setCurrentTab(0);
    }
}
