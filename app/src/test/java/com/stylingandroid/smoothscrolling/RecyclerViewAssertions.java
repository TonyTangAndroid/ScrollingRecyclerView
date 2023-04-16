package com.stylingandroid.smoothscrolling;

import static android.view.View.FIND_VIEWS_WITH_TEXT;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.ViewAssertion;
import com.google.common.truth.Truth;
import java.util.ArrayList;
import java.util.Objects;
import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;

public final class RecyclerViewAssertions {

  public static ViewAssertion hasItemsCount(final int count) {
    return new ViewAssertion() {
      @Override
      public void check(View view, NoMatchingViewException e) {
        if (!(view instanceof RecyclerView)) {
          throw e;
        }
        RecyclerView rv = (RecyclerView) view;
        Truth.assertThat(Objects.requireNonNull(rv.getAdapter()).getItemCount()).isEqualTo(count);
      }
    };
  }

  public static ViewAssertion hasHolderItemAtPosition(
      final int index, final Matcher<RecyclerView.ViewHolder> viewHolderMatcher) {
    return new ViewAssertion() {
      @Override
      public void check(View view, NoMatchingViewException e) {
        if (!(view instanceof RecyclerView)) {
          throw e;
        }
        RecyclerView rv = (RecyclerView) view;
        MatcherAssert.assertThat(rv.findViewHolderForAdapterPosition(index), viewHolderMatcher);
      }
    };
  }

  public static ViewAssertion hasViewWithTextAtPosition(final int index, final CharSequence text) {
    return new ViewAssertion() {
      @Override
      public void check(View view, NoMatchingViewException e) {
        if (!(view instanceof RecyclerView)) {
          throw e;
        }
        RecyclerView rv = (RecyclerView) view;
        ArrayList<View> outviews = new ArrayList<>();
        rv.findViewHolderForAdapterPosition(index)
            .itemView
            .findViewsWithText(outviews, text, FIND_VIEWS_WITH_TEXT);
        Truth.assert_()
            .withMessage(
                "There's no view at index " + index + " of recyclerview that has text : " + text)
            .that(outviews)
            .isNotEmpty();
      }
    };
  }

  public static ViewAssertion doesNotHaveViewWithText(final String text) {
    return new ViewAssertion() {
      @Override
      public void check(View view, NoMatchingViewException e) {
        if (!(view instanceof RecyclerView)) {
          throw e;
        }
        RecyclerView rv = (RecyclerView) view;
        ArrayList<View> outviews = new ArrayList<>();
        for (int index = 0; index < rv.getAdapter().getItemCount(); index++) {
          ViewHolder viewHolderForAdapterPosition = rv.findViewHolderForAdapterPosition(index);
          if (viewHolderForAdapterPosition == null) {
            continue;
          }
          viewHolderForAdapterPosition.itemView.findViewsWithText(
              outviews, text, FIND_VIEWS_WITH_TEXT);
          if (outviews.size() > 0) {
            break;
          }
        }
        Truth.assertThat(outviews).isEmpty();
      }
    };
  }
}
