// Generated by view binder compiler. Do not edit!
package fpoly.ph38531.lab01.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import fpoly.ph38531.lab01.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityLoginOtpBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final Button btngetotp;

  @NonNull
  public final Button btnloginotp;

  @NonNull
  public final EditText edotp;

  @NonNull
  public final EditText edphone;

  private ActivityLoginOtpBinding(@NonNull LinearLayout rootView, @NonNull Button btngetotp,
      @NonNull Button btnloginotp, @NonNull EditText edotp, @NonNull EditText edphone) {
    this.rootView = rootView;
    this.btngetotp = btngetotp;
    this.btnloginotp = btnloginotp;
    this.edotp = edotp;
    this.edphone = edphone;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityLoginOtpBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityLoginOtpBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_login_otp, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityLoginOtpBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btngetotp;
      Button btngetotp = ViewBindings.findChildViewById(rootView, id);
      if (btngetotp == null) {
        break missingId;
      }

      id = R.id.btnloginotp;
      Button btnloginotp = ViewBindings.findChildViewById(rootView, id);
      if (btnloginotp == null) {
        break missingId;
      }

      id = R.id.edotp;
      EditText edotp = ViewBindings.findChildViewById(rootView, id);
      if (edotp == null) {
        break missingId;
      }

      id = R.id.edphone;
      EditText edphone = ViewBindings.findChildViewById(rootView, id);
      if (edphone == null) {
        break missingId;
      }

      return new ActivityLoginOtpBinding((LinearLayout) rootView, btngetotp, btnloginotp, edotp,
          edphone);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
