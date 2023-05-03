package com.example.doann;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.ClipData;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.widget.LinearLayout;

import com.example.doann.R;
import com.example.doann.Song;

import java.util.ArrayList;
import java.util.List;

public class Activity2 extends AppCompatActivity {

    private RecyclerView rcvData;
    private SongAdapter songAdapter;

    private SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        rcvData = findViewById(R.id.rcv_data);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvData.setLayoutManager(linearLayoutManager);


        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rcvData.addItemDecoration(itemDecoration);

        songAdapter = new SongAdapter(this, getListSong());
        rcvData.setAdapter(songAdapter);
    }

    private List<Song> getListSong(){
        List<Song> list = new ArrayList<>();
        list.add(new Song(R.drawable.ic_launcher_foreground, "1. TO THE MOON - HOLIGAN", "Wise man call me the crazy... "));
        list.add(new Song(R.drawable.ic_launcher_foreground, "2. SAO ANH CHƯA VỀ NHÀ - AMEE", "Trời đã gần sáng rồi mà nỗi nhớ anh..."));
        list.add(new Song(R.drawable.ic_launcher_foreground, "3. PHẢI CHĂNG EM ĐÃ YÊU - JUKISAN", "Phải chăng em đã yêu từ cái nhìn đầu... "));
        list.add(new Song(R.drawable.ic_launcher_foreground, "4. CUA - HIEUTHUHAI", "Nhìn tụi anh thật cool, Lạnh như băng mà... "));
        list.add(new Song(R.drawable.ic_launcher_foreground, "5. ĐEN ĐÁ KHÔNG ĐƯỜNG - AMEE", "Trời đang nắng tự dưng lại mưa... "));
        list.add(new Song(R.drawable.ic_launcher_foreground, "6. DUYÊN PHẬN - NHƯ QUỲNH", "Phận làm con gái chưa một lần yêu ai... "));
        list.add(new Song(R.drawable.ic_launcher_foreground, "7. MỘT BƯỚC YÊU VẠN DẶM ĐAU - MR SIRO", "Một thế giới hư ảo, nhưng thật ấm áp... "));
        list.add(new Song(R.drawable.ic_launcher_foreground, "8. HEAD IN THE CLOUD - HAYD", "I miss the days when life was so simple... "));
        list.add(new Song(R.drawable.ic_launcher_foreground, "9. VỆ TINH - HIEUTHUHAI", "Baby em là một ngôi sao ? Vệ tinh luôn... "));
        list.add(new Song(R.drawable.ic_launcher_foreground, "10. PHI HÀNH GIA - LILWWUYN", "Màn đêm xuống gần chót, Có thằng uống... "));
        return list;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                songAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                songAdapter.getFilter().filter(newText);
                return false;
            }
        });
            return true;
        }
    }
