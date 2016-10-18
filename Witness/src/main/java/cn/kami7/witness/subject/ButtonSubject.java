package cn.kami7.witness.subject;

import android.widget.Button;

import cn.kami7.witness.Subject;

public class ButtonSubject extends Subject<Button> {
    public ButtonSubject(Button button) {
        super(button);
    }

    @Override
    public void onReady(Button subject, boolean isReady) {
        subject.setEnabled(isReady);
    }
}
