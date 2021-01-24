package com.example.homeworker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    private ArrayList<String> kNames = new ArrayList<>();
    private ArrayList<String> kImageUrls = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initImageBitmaps();
    }

    private void initImageBitmaps() {
        kImageUrls.add("https://www.advantour.com/img/kyrgyzstan/symbolics/kyrgyzstan-flag_sm.jpg");
        kNames.add("Kyrgyzstan");

        kImageUrls.add("https://i.ebayimg.com/images/g/0~gAAMXQ9qpRTk04/s-l500.jpg");
        kNames.add("America");

        kImageUrls.add("https://upload.wikimedia.org/wikipedia/en/thumb/a/ae/Flag_of_the_United_Kingdom.svg/383px-Flag_of_the_United_Kingdom.svg.png");
        kNames.add("United Kingdom");

        kImageUrls.add("https://upload.wikimedia.org/wikipedia/commons/thumb/f/fa/Flag_of_the_People%27s_Republic_of_China.svg/240px-Flag_of_the_People%27s_Republic_of_China.svg.png");
        kNames.add("China");

        kImageUrls.add("https://upload.wikimedia.org/wikipedia/commons/thumb/b/ba/Flag_of_Germany.svg/240px-Flag_of_Germany.svg.png");
        kNames.add("Germany");

        kImageUrls.add("https://upload.wikimedia.org/wikipedia/commons/thumb/d/d9/Flag_of_Canada_%28Pantone%29.svg/240px-Flag_of_Canada_%28Pantone%29.svg.png");
        kNames.add("Canada");
        kImageUrls.add("https://upload.wikimedia.org/wikipedia/commons/thumb/0/09/Flag_of_South_Korea.svg/240px-Flag_of_South_Korea.svg.png");
        kNames.add("South Korea");

        kImageUrls.add("https://upload.wikimedia.org/wikipedia/ru/thumb/d/d5/%D0%9B%D0%BE%D0%B3%D0%BE%D1%82%D0%B8%D0%BF_%D0%BF%D0%B0%D1%80%D1%82%D0%B8%D0%B8_%22%D0%95%D0%B4%D0%B8%D0%BD%D0%B0%D1%8F_%D0%A0%D0%BE%D1%81%D1%81%D0%B8%D1%8F%22.svg/300px-%D0%9B%D0%BE%D0%B3%D0%BE%D1%82%D0%B8%D0%BF_%D0%BF%D0%B0%D1%80%D1%82%D0%B8%D0%B8_%22%D0%95%D0%B4%D0%B8%D0%BD%D0%B0%D1%8F_%D0%A0%D0%BE%D1%81%D1%81%D0%B8%D1%8F%22.svg.png");
        kNames.add("Russia");

        kImageUrls.add("https://upload.wikimedia.org/wikipedia/commons/thumb/b/b4/Flag_of_Turkey.svg/240px-Flag_of_Turkey.svg.png");
        kNames.add("Turkey");

        kImageUrls.add("https://upload.wikimedia.org/wikipedia/commons/thumb/c/c3/Flag_of_France.svg/240px-Flag_of_France.svg.png");
        kNames.add("France");

        kImageUrls.add("https://upload.wikimedia.org/wikipedia/commons/thumb/d/d3/Flag_of_Kazakhstan.svg/240px-Flag_of_Kazakhstan.svg.png");
        kNames.add("Kazakhstan");
        initRecycleView();
        new ItemTouchHelper(simpleCallback).attachToRecyclerView(recyclerView);

    }

    public void initRecycleView() {
        recyclerView = findViewById(R.id.recycler_view);
        recyclerViewAdapter = new RecyclerViewAdapter(kNames, kImageUrls, this);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }



    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP | ItemTouchHelper.DOWN,
            ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT
    ) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            int positionDrag = viewHolder.getAdapterPosition();
            int positionTarget = target.getAdapterPosition();
            Collections.swap(recyclerViewAdapter.getnImageNames(), positionDrag, positionTarget);
            recyclerViewAdapter.notifyItemMoved(positionDrag, positionTarget);
            return true;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            recyclerViewAdapter.getnImageNames().remove(viewHolder.getAdapterPosition());
            recyclerViewAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
        }
    };

}