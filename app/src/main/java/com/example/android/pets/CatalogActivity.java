/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.pets;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/**
 * Displays list of pets that were entered and stored in the app.
 */
public class CatalogActivity extends AppCompatActivity {

    private ArrayList<PetEntity> pets;
    private PetAdapter mPetAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        //populate pets with dummy data
        pets = new ArrayList<>();
        pets.add(new PetEntity("Toto","Terrier",1,3));

        // Setup FAB to open EditorActivity
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CatalogActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });

        // Find the RecyclerView which will be populated with the pet data
        RecyclerView petRecyclerView = findViewById(R.id.list);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        petRecyclerView.setLayoutManager(manager);
        DividerItemDecoration divider = new DividerItemDecoration(this,
                manager.getOrientation());
        petRecyclerView.addItemDecoration(divider);

        // Find empty view on the RecyclerView, so that it only shows when the list has 0 items.
        View emptyView = findViewById(R.id.empty_view);

        //open editor activity with parcelable as an extra
        mPetAdapter = new PetAdapter(this, emptyView, new PetAdapter.OnClickListener() {
            @Override
            public void onItemClick(int position) {
                //open editor activity with parcelable as an extra
                Intent intent = new Intent(CatalogActivity.this, EditorActivity.class);
                intent.putExtra("petExtra", pets.get(position));
                startActivity(intent);
            }
        });
        mPetAdapter.setPets(pets);

        petRecyclerView.setAdapter(mPetAdapter);
    }

    /**
     * Helper method to insert hardcoded pet data into the database. For debugging purposes only.
     */
    private void insertDummyPet() {
        //Insert with dummy data to Array List
        pets.add(new PetEntity("Toto X","Terrier X",1,7));
        mPetAdapter.setPets(pets);
        Toast.makeText(this,getString(R.string.action_insert_dummy_data),Toast.LENGTH_SHORT).show();
    }

    /**
     * Helper method to delete all pets in the database.
     */
    private void deleteAllPets() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_catalog, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.action_insert_dummy_data:
                insertDummyPet();
                return true;
            // Respond to a click on the "Delete all entries" menu option
            case R.id.action_delete_all_entries:
                deleteAllPets();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
