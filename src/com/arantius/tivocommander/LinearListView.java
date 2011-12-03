/*
Open Commander for TiVo allows control of a TiVo Premiere device.
Copyright (C) 2011  Anthony Lieuallen (arantius@gmail.com)

This program is free software; you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation; either version 2 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License along
with this program; if not, write to the Free Software Foundation, Inc.,
51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
*/

package com.arantius.tivocommander;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.content.Context;
import android.database.DataSetObserver;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Adapter;
import android.widget.LinearLayout;

// http://stackoverflow.com/questions/3192595/android-listview-that-does-not-scroll/5646121#5646121
// Like a ListView, but it does not implicitly scroll.
public class LinearListView extends LinearLayout {
  private class Observer extends DataSetObserver {
    LinearListView context;

    public Observer(LinearListView context) {
      this.context = context;
    }

    @Override
    public void onChanged() {
      List<View> oldViews = new ArrayList<View>(context.getChildCount());

      for (int i = 0; i < context.getChildCount(); i++)
        oldViews.add(context.getChildAt(i));

      Iterator<View> iter = oldViews.iterator();

      context.removeAllViews();

      for (int i = 0; i < context.adapter.getCount(); i++) {
        View convertView = iter.hasNext() ? iter.next() : null;
        context.addView(context.adapter.getView(i, convertView, context));
      }
      super.onChanged();
    }

    @Override
    public void onInvalidated() {
      context.removeAllViews();
      super.onInvalidated();
    }
  }
  Adapter adapter;

  Observer observer = new Observer(this);

  public LinearListView(Context context) {
    super(context);
  }

  public LinearListView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public void setAdapter(Adapter adapter) {
    if (this.adapter != null)
      this.adapter.unregisterDataSetObserver(observer);

    this.adapter = adapter;
    adapter.registerDataSetObserver(observer);
    observer.onChanged();
  }
}
