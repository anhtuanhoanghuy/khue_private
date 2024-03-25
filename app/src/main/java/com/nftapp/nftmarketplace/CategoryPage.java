package com.nftapp.nftmarketplace;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nftapp.nftmarketplace.adapter.CategoryAdapter;
import com.nftapp.nftmarketplace.adapter.ItemAdapter;
import com.nftapp.nftmarketplace.adapter.ItemAdapter_2;
import com.nftapp.nftmarketplace.model.Category;
import com.nftapp.nftmarketplace.model.Item;

import java.util.ArrayList;
import java.util.List;

public class CategoryPage extends AppCompatActivity {
    private TextView category_name;
    private ImageView back_button;
    private ImageView no_result;
    private TextView no_result_text;
    private RecyclerView rcvItem;
    private ItemAdapter_2 mItemAdapter;
    private SearchView searchView;
    private List<Item> item = getListItem();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_page);
        back_button = findViewById(R.id.back_button);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        no_result = findViewById(R.id.no_result_2);
        no_result_text = findViewById(R.id.no_result_text_2);
        rcvItem = findViewById(R.id.rcv_items);
        mItemAdapter = new ItemAdapter_2(this);
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // Màn hình đang ở chế độ ngang
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4);
            rcvItem.setLayoutManager(gridLayoutManager);
        } else {
            // Màn hình đang ở chế độ dọc
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
            rcvItem.setLayoutManager(gridLayoutManager);
        }

        Bundle bundle = getIntent().getExtras();
        if(bundle == null) {
            return;
        }
        Category category = (Category) bundle.get("object_category");
        category_name = findViewById(R.id.category_name);
        Intent intent = getIntent();
        category_name.setText(category.getNameCategory());
//        mItemAdapter.setData(category.getItems());
        mItemAdapter.setData(getListItem());
        rcvItem.setAdapter(mItemAdapter);
        searchView = findViewById(R.id.search_2);
        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CategoryPage.this, "hi",Toast.LENGTH_SHORT).show();
//                category_name.setVisibility(View.GONE);
            }
        });
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });

    }
    private void filterList(String newText) {
        List<Item> filterList = new ArrayList<>();
        item = getListItem();
        for (Item item : item) {
            if(item.getItem_name().toLowerCase().contains(newText.toLowerCase()) || item.getItem_place().toLowerCase().contains(newText.toLowerCase()) ) {
                filterList.add(item);
            }
        }
        if(filterList.isEmpty()) {
            rcvItem.setVisibility(View.GONE);
            no_result.setVisibility(View.VISIBLE);
            no_result_text.setVisibility(View.VISIBLE);

        } else {
            rcvItem.setVisibility(View.VISIBLE);
            no_result.setVisibility(View.GONE);
            no_result_text.setVisibility(View.GONE);
            mItemAdapter.setFilterList(filterList);
        }
    }
    private List<Item> getListItem(){
        List<Item> list = new ArrayList<>();
        list.add(new Item(1,R.drawable.thanhnhaho,"Thành nhà Hồ","Thanh Hóa"));
        list.add(new Item(2,R.drawable.avt2, "Cố đô Huế","Thừa Thiên Huế"));
        list.add(new Item(1,R.drawable.thanhnhaho,"Lăng chủ tịch","Hà Nội"));
        list.add(new Item(2,R.drawable.avt2, "Thành cổ Quảng Trị","Quảng Trị"));
        list.add(new Item(1,R.drawable.thanhnhaho,"Dinh độc lập","Tp.Hồ Chí Minh"));
        list.add(new Item(2,R.drawable.avt2, "Nhà tù Hỏa Lò","Hà Nội"));
        list.add(new Item(1,R.drawable.thanhnhaho,"Đền Hùng","Phú Thọ"));
        list.add(new Item(2,R.drawable.avt2, "Thành Cổ Loa","Đông Anh"));
        list.add(new Item(1,R.drawable.thanhnhaho,"Hồ Gươm","Hà Nội"));
        list.add(new Item(2,R.drawable.avt2, "Cố đô Hoa Lư","Ninh Bình"));
        list.add(new Item(1,R.drawable.thanhnhaho,"Chiến khu Tân Trào","Tuyên Quang"));
        list.add(new Item(2,R.drawable.avt2, "Khu di tích Pác Bó","Cao Bằng"));
        list.add(new Item(1,R.drawable.thanhnhaho,"Đền Ngọc Sơn","Hà Nội"));
        list.add(new Item(2,R.drawable.avt2, "Thiền viện Trúc lâm Yên Tử","Quảng Ninh"));
        list.add(new Item(1,R.drawable.thanhnhaho,"Đền Trần","Nam Định"));
        list.add(new Item(2,R.drawable.avt2, "Văn miếu - Quốc Tử Giám","Hà Nội"));
        list.add(new Item(1,R.drawable.thanhnhaho,"Vịnh Hạ Long","Quảng Ninh"));
        list.add(new Item(2,R.drawable.avt2, "Phong Nha Kẻ Bàng","Quảng Bình"));
        list.add(new Item(1,R.drawable.thanhnhaho,"Phố cổ Hội An","Quảng Nam"));
        list.add(new Item(2,R.drawable.avt2, "Thánh địa Mỹ Sơn","Quảng Nam"));
        list.add(new Item(1,R.drawable.thanhnhaho,"Phở xưa","Hà Nội"));
        list.add(new Item(2,R.drawable.avt2, "Nem chua","Thanh Hóa"));
        list.add(new Item(1,R.drawable.thanhnhaho,"Bánh cáy","Thái Bình"));
        list.add(new Item(2,R.drawable.avt2, "Bánh đậu xanh","Hải dương"));
        return list;
    }

}


