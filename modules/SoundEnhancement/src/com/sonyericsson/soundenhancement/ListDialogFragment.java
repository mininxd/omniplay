package com.sonyericsson.soundenhancement;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public final class ListDialogFragment extends DialogFragment {
    private static final String KEY_CURRENT_PRESET = "current_preset";
    private static final String KEY_PRESET_ICONS = "preset_icons";
    private static final String KEY_PRESET_NAMES = "preset_names";
    private static final String KEY_PRESET_SUMMARIES = "preset_summaries";
    private static final String KEY_TITLE = "title";
    private static final String KEY_TRANSPARENT_BACKGROUND = "transparent_background";
    private static final String KEY_USED_NEGATIVE_BUTTON = "used_negative_button";
    private static final String KEY_USED_POSITIVE_BUTTON = "used_positive_button";
    private static final String LOG_TAG = "SoundEnhancement";
    private ItemAdapter mItemAdapter = null;

    public interface Callbacks {
        void onCancel(DialogInterface dialogInterface, String str);

        void onDismiss(DialogInterface dialogInterface, String str);

        void onListItemClicked(DialogInterface dialogInterface, String str, int i);

        void onNegativeButtonClicked(DialogInterface dialogInterface, String str);

        void onPositiveButtonClicked(DialogInterface dialogInterface, String str);
    }

    @Override // android.app.DialogFragment
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder;
        Bundle args = getArguments();
        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() { // from class: com.sonyericsson.soundenhancement.ListDialogFragment.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialog, int which) {
                ComponentCallbacks2 targetFragment = ListDialogFragment.this.getTargetFragment();
                if (targetFragment != null && (targetFragment instanceof Callbacks)) {
                    Callbacks callbacks = (Callbacks) targetFragment;
                    if (which == -2) {
                        callbacks.onNegativeButtonClicked(dialog, ListDialogFragment.this.getTag());
                        return;
                    }
                    if (which != -1) {
                        if (ListDialogFragment.this.mItemAdapter != null) {
                            ListDialogFragment.this.mItemAdapter.mCheckedIndex = which;
                            ListDialogFragment.this.mItemAdapter.notifyDataSetChanged();
                        }
                        callbacks.onListItemClicked(dialog, ListDialogFragment.this.getTag(), which);
                        return;
                    }
                    callbacks.onPositiveButtonClicked(dialog, ListDialogFragment.this.getTag());
                }
            }
        };
        boolean transparentIsBackground = args.getBoolean(KEY_TRANSPARENT_BACKGROUND);
        if (transparentIsBackground) {
            builder = new AlertDialog.Builder(getActivity(), R.style.TranslucentDialogTheme);
        } else {
            builder = new AlertDialog.Builder(getActivity());
        }
        builder.setTitle(args.getString(KEY_TITLE));
        boolean usedPositiveButton = args.getBoolean(KEY_USED_POSITIVE_BUTTON);
        if (usedPositiveButton) {
            builder.setPositiveButton(android.R.string.ok, listener);
        }
        boolean usedNegativeButton = args.getBoolean(KEY_USED_NEGATIVE_BUTTON);
        if (usedNegativeButton) {
            builder.setNegativeButton(android.R.string.cancel, listener);
        }
        AnonymousClass1 anonymousClass1 = null;
        if (args.containsKey(KEY_PRESET_NAMES) && args.containsKey(KEY_PRESET_SUMMARIES)) {
            String[] names = args.getStringArray(KEY_PRESET_NAMES);
            String[] summaries = args.getStringArray(KEY_PRESET_SUMMARIES);
            int[] icons = args.getIntArray(KEY_PRESET_ICONS);
            ArrayList<LayoutItem> itemList = new ArrayList<>();
            int length = names.length;
            int i = 0;
            while (i < length) {
                LayoutItem item = new LayoutItem();
                item.name = names[i];
                item.summary = summaries[i];
                if (icons != null) {
                    item.icon = icons[i];
                }
                itemList.add(item);
                i++;
                anonymousClass1 = null;
            }
            this.mItemAdapter = new ItemAdapter(getActivity(), R.layout.list_dialog_with_summary, itemList, args.getInt(KEY_CURRENT_PRESET));
            builder.setSingleChoiceItems(this.mItemAdapter, args.getInt(KEY_CURRENT_PRESET), listener);
        } else {
            boolean transparentIsBackground2 = args.containsKey(KEY_PRESET_NAMES);
            if (transparentIsBackground2) {
                String[] names2 = args.getStringArray(KEY_PRESET_NAMES);
                ArrayList<LayoutItem> itemList2 = new ArrayList<>();
                for (String str : names2) {
                    LayoutItem item2 = new LayoutItem();
                    item2.name = str;
                    itemList2.add(item2);
                }
                this.mItemAdapter = new ItemAdapter(getActivity(), R.layout.list_dialog, itemList2, args.getInt(KEY_CURRENT_PRESET));
                builder.setSingleChoiceItems(this.mItemAdapter, args.getInt(KEY_CURRENT_PRESET), listener);
            }
        }
        return builder.create();
    }

    @Override // android.app.DialogFragment, android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        ComponentCallbacks2 targetFragment = getTargetFragment();
        if (targetFragment != null && (targetFragment instanceof Callbacks)) {
            Callbacks callbacks = (Callbacks) targetFragment;
            callbacks.onCancel(dialog, getTag());
        }
    }

    @Override // android.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        ComponentCallbacks2 targetFragment = getTargetFragment();
        if (targetFragment != null && (targetFragment instanceof Callbacks)) {
            Callbacks callbacks = (Callbacks) targetFragment;
            callbacks.onDismiss(dialog, getTag());
        }
    }

    private static class LayoutItem {
        public int icon;
        public String name;
        public String summary;

        private LayoutItem() {
            this.icon = -1;
        }
    }

    private static class ItemAdapter extends ArrayAdapter<LayoutItem> {
        public int mCheckedIndex;
        private final LayoutInflater mInflater;
        private final List<LayoutItem> mItemList;
        private final int mLayout;

        public ItemAdapter(Context context, int layout, List<LayoutItem> itemList, int checkedItem) {
            super(context, layout, itemList);
            this.mLayout = layout;
            this.mItemList = itemList;
            this.mCheckedIndex = checkedItem;
            this.mInflater = (LayoutInflater) context.getSystemService("layout_inflater");
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = this.mInflater.inflate(this.mLayout, (ViewGroup) null);
            }
            View view = convertView;
            if (position < 0 || position >= this.mItemList.size()) {
                return view;
            }
            LayoutItem item = this.mItemList.get(position);
            TextView nameTv = (TextView) view.findViewById(R.id.list_dialog_name);
            nameTv.setText(item.name);
            int left = 0;
            int right = 0;
            if (item.icon != -1) {
                if (getContext().getResources().getConfiguration().getLayoutDirection() == 0) {
                    right = item.icon;
                } else {
                    left = item.icon;
                }
            }
            nameTv.setCompoundDrawablesWithIntrinsicBounds(left, 0, right, 0);
            if (!TextUtils.isEmpty(item.summary)) {
                TextView tv = (TextView) view.findViewById(R.id.list_dialog_summary);
                tv.setText(item.summary);
                tv.setVisibility(0);
            }
            boolean isChecked = position == this.mCheckedIndex;
            ((RadioButton) view.findViewById(R.id.list_dialog_radio_btn)).setChecked(isChecked);
            return view;
        }
    }

    public static class Builder {
        private String mTitle;
        private String[] mPresetNames = null;
        private String[] mPresetSummaries = null;
        private int[] mPresetIcons = null;
        private Fragment mTargetFragment = null;
        private int mCurrentPosition = 0;
        private boolean mTransparentIsBackground = false;
        private boolean mUsedPositiveButton = true;
        private boolean mUsedNegativeButton = true;

        public Builder setTitle(String title) {
            this.mTitle = title;
            return this;
        }

        public Builder setPresetNames(String[] presetNames) {
            if (presetNames == null) {
                return this;
            }
            String[] sPresetNames = (String[]) presetNames.clone();
            this.mPresetNames = sPresetNames;
            return this;
        }

        public Builder setPresetNamesWithSummary(String[] names, String[] summaries, int[] icons) {
            if (names == null || summaries == null || names.length != summaries.length) {
                return this;
            }
            this.mPresetNames = (String[]) names.clone();
            this.mPresetSummaries = (String[]) summaries.clone();
            if (icons != null) {
                this.mPresetIcons = (int[]) icons.clone();
            }
            return this;
        }

        public Builder setCurrentPosition(int currentPosition) {
            this.mCurrentPosition = currentPosition;
            return this;
        }

        public Builder setTargetFragment(Fragment targetFragment) {
            this.mTargetFragment = targetFragment;
            return this;
        }

        public Builder setTransparentIsBackground(boolean enable) {
            this.mTransparentIsBackground = enable;
            return this;
        }

        public Builder setPositiveButton(boolean enable) {
            this.mUsedPositiveButton = enable;
            return this;
        }

        public Builder setNegativeButton(boolean enable) {
            this.mUsedNegativeButton = enable;
            return this;
        }

        public ListDialogFragment create() {
            ListDialogFragment listDialogFragment = new ListDialogFragment();
            Fragment fragment = this.mTargetFragment;
            if (fragment != null) {
                listDialogFragment.setTargetFragment(fragment, 0);
            }
            Bundle args = new Bundle();
            args.putString(ListDialogFragment.KEY_TITLE, this.mTitle);
            args.putInt(ListDialogFragment.KEY_CURRENT_PRESET, this.mCurrentPosition);
            args.putBoolean(ListDialogFragment.KEY_TRANSPARENT_BACKGROUND, this.mTransparentIsBackground);
            args.putBoolean(ListDialogFragment.KEY_USED_POSITIVE_BUTTON, this.mUsedPositiveButton);
            args.putBoolean(ListDialogFragment.KEY_USED_NEGATIVE_BUTTON, this.mUsedNegativeButton);
            String[] strArr = this.mPresetNames;
            if (strArr != null) {
                args.putStringArray(ListDialogFragment.KEY_PRESET_NAMES, strArr);
            }
            String[] strArr2 = this.mPresetSummaries;
            if (strArr2 != null) {
                args.putStringArray(ListDialogFragment.KEY_PRESET_SUMMARIES, strArr2);
            }
            int[] iArr = this.mPresetIcons;
            if (iArr != null) {
                args.putIntArray(ListDialogFragment.KEY_PRESET_ICONS, iArr);
            }
            listDialogFragment.setArguments(args);
            return listDialogFragment;
        }
    }
}
