// Generated by view binder compiler. Do not edit!
package fpoly.ph38531.lab01.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import fpoly.ph38531.lab01.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class MessagerowBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final LinearLayout mainmessageLayout;

  @NonNull
  public final TextView txtmessage;

  @NonNull
  public final TextView txtngay;

  private MessagerowBinding(@NonNull LinearLayout rootView, @NonNull LinearLayout mainmessageLayout,
      @NonNull TextView txtmessage, @NonNull TextView txtngay) {
    this.rootView = rootView;
    this.mainmessageLayout = mainmessageLayout;
    this.txtmessage = txtmessage;
    this.txtngay = txtngay;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static MessagerowBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static MessagerowBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.messagerow, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static MessagerowBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.mainmessageLayout;
      LinearLayout mainmessageLayout = ViewBindings.findChildViewById(rootView, id);
      if (mainmessageLayout == null) {
        break missingId;
      }

      id = R.id.txtmessage;
      TextView txtmessage = ViewBindings.findChildViewById(rootView, id);
      if (txtmessage == null) {
        break missingId;
      }

      id = R.id.txtngay;
      TextView txtngay = ViewBindings.findChildViewById(rootView, id);
      if (txtngay == null) {
        break missingId;
      }

      return new MessagerowBinding((LinearLayout) rootView, mainmessageLayout, txtmessage, txtngay);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
