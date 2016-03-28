package com.nekoscape.android.ntc.data.operator;

import java.util.Iterator;
import java.util.NoSuchElementException;

import android.database.Cursor;
import android.util.Log;

import com.nekoscape.android.ntc.data.operator.GraphDataIterator.Entity;

public class GraphDataIterator implements Iterator<Entity> {

	private Cursor cursor = null;
	private boolean init_flag = true;

	public GraphDataIterator(Cursor cursor) {
		this.cursor = cursor;

		Log.d("Debug", "cusror count : " + cursor.getCount());
	}

	@Override
	public boolean hasNext() {
		if (init_flag) {
			init_flag = false;
			return cursor.moveToFirst();
		} else {
			return cursor.moveToNext();
		}
	}

	@Override
	public Entity next() throws NoSuchElementException{
		if(cursor == null){
			throw new NoSuchElementException();
		}
		return new Entity(cursor.getDouble(0), cursor.getDouble(1));
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException("unavailable");
	}

	public static class Entity {
		private double x;
		private double y;

		Entity(double x, double y) {
			this.x = x;
			this.y = y;
		}

		public double getX() {
			return this.x;
		}

		public double getY() {
			return this.y;
		}

		@Override
		public String toString() {
			return String.format("x = %s, y = %s", x, y);
		}
	}
}
