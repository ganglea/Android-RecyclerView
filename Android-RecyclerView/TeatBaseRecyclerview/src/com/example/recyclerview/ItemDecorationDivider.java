package com.example.recyclerview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.util.Log;
import android.view.View;

/**
 * @author wen_er
 *
 */
public class ItemDecorationDivider extends ItemDecoration {

	private Drawable mDivider;
	private int mOritation;

	public ItemDecorationDivider(Context context, int resId, int oritation) {

		mDivider = context.getResources().getDrawable(resId);
		this.mOritation = oritation;
		Log.i("ItemDecorationDivider", "mOritation=" + mOritation);

	}

	@Override
	public void onDrawOver(Canvas c, RecyclerView parent) {

		if (mOritation == LinearLayoutManager.VERTICAL) {
			final int left = parent.getPaddingLeft();
			final int right = parent.getWidth() - parent.getPaddingRight();

			final int childCount = parent.getChildCount();
			for (int i = 0; i < childCount; i++) {
				final View child = parent.getChildAt(i);
				final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
						.getLayoutParams();

				final int top = child.getBottom() + params.bottomMargin;
				final int bottom = top + mDivider.getIntrinsicHeight();
				mDivider.setBounds(left, top, right, bottom);
				mDivider.draw(c);
			}
		} else if (mOritation == LinearLayoutManager.HORIZONTAL) {

			final int top = parent.getPaddingTop();
			// final int bottom = parent.getHeight() -
			// parent.getPaddingBottom();

			final int childCount = parent.getChildCount();
			for (int i = 0; i < childCount; i++) {
				final View child = parent.getChildAt(i);
				final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
						.getLayoutParams();
				final int left = child.getRight() + params.rightMargin;
				final int right = left + mDivider.getIntrinsicHeight();

				final int bottom = child.getBottom();
				mDivider.setBounds(left, top, right, bottom);
				mDivider.draw(c);
			}
		}

	}

	@Override
	public void getItemOffsets(Rect outRect, int position,
			RecyclerView parent) {
		if (mOritation == LinearLayoutManager.VERTICAL) {
			outRect.set(0, 0, 0, mDivider.getIntrinsicWidth());
			// outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
		} else if (mOritation == LinearLayoutManager.HORIZONTAL) {
			// outRect.set(0, 0, mDivider.getIntrinsicWidth(), 0);
			outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
		}

	}
}


