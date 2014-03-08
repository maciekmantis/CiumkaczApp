package com.mks.ciumkacz.dao;

import android.content.Context;

public class FeedDao {

    private EntityManager em;

    public FeedDao(Context context) {
        em = new EntityManager(context);
    }

    public void create(Feed feed) {

    }

}
