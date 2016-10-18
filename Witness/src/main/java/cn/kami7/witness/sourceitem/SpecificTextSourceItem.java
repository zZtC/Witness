package cn.kami7.witness.sourceitem;

import android.text.Editable;
import android.widget.TextView;

import cn.kami7.witness.SourceItem;

public class SpecificTextSourceItem extends SourceItem<TextView> implements android.text.TextWatcher {
    private boolean isBeforeReady;
    private String emptiString;

    public SpecificTextSourceItem(TextView textView, String emptiString){
        super(textView);
        source.addTextChangedListener(this);
        this.emptiString = emptiString;
    }

    @Override
    public boolean isReady(TextView editText) {
        return !(source.getText().length() == 0 || emptiString.equals(source.getText().toString()));
    }

    @Override
    protected void unregister() {
        super.unregister();
        source.removeTextChangedListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        isBeforeReady = !(charSequence.length() == 0 || emptiString.equals(charSequence.toString()));
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if(((charSequence.length() == 0 || emptiString.equals(charSequence.toString())) && isBeforeReady)
                || (!(charSequence.length() == 0 || emptiString.equals(charSequence.toString())) && !isBeforeReady)){
            notifyChanged();
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {
    }
}
