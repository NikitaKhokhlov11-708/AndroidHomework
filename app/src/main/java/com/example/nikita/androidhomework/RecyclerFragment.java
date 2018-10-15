package com.example.nikita.androidhomework;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class RecyclerFragment extends Fragment {

    final ArrayList<Character> charsList = new ArrayList<>();
    CharactersAdapter mAdapter = new CharactersAdapter(charsList);
    private OnFragmentInteractionListener mListener;

    public RecyclerFragment() {
    }

    public static RecyclerFragment newInstance() {
        RecyclerFragment fragment = new RecyclerFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recycler, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        View v = getView();
        RecyclerView mRecyclerView = v.findViewById(R.id.rv_chars);

        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(itemDecoration);

        final ArrayList<Character> charsList = new ArrayList<>();
        RecyclerView.Adapter mAdapter = new CharactersAdapter(charsList);

        charsList.add(new Character("Джайна Праудмур", "Могущественная волшебница", 0));
        charsList.add(new Character("Артас Менетил", "Защитник Нер'зула, Король Лордерона, Король-лич", 1));
        charsList.add(new Character("Тралл", "Освободитель Орков, Повелитель Кланов", 2));
        charsList.add(new Character("Утер Светоносный", "Первый Паладин, Великий Мастер Серебряной Длани", 3));
        charsList.add(new Character("Кел'Тузад", "Верховный лич, Лич Лорд Чумных земель, Повелитель Наксрамаса", 4));
        charsList.add(new Character("Сильвана Ветрокрылая", "Королева банши", 5));

        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.item2) {
            for (int i = 0; i < charsList.size() - 1; i++) {
                Character p = charsList.get(i);
                Character next = charsList.get(i + 1);
                if (p.id > next.id) {
                    Character temp = p;
                    p = next;
                    next = temp;
                }
            }
            mAdapter.updateList(new ArrayList<Character>());

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
