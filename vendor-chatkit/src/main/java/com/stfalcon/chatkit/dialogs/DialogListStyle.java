/*******************************************************************************
 * Copyright 2016 stfalcon.com
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/

package com.stfalcon.chatkit.dialogs;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;

import com.stfalcon.chatkit.R;
import com.stfalcon.chatkit.commons.Style;

/**
 * Style for DialogList customization by xml attributes
 */
@SuppressWarnings("WeakerAccess")
class DialogListStyle extends Style {

    private int dialogTitleTextColor;
    private int dialogTitleTextSize;
    private int dialogTitleTextStyle;
    private int dialogUnreadTitleTextColor;
    private int dialogUnreadTitleTextStyle;

    private int dialogMessageTextColor;
    private int dialogMessageTextSize;
    private int dialogMessageTextStyle;
    private int dialogUnreadMessageTextColor;
    private int dialogUnreadMessageTextStyle;

    private int dialogDateColor;
    private int dialogDateSize;
    private int dialogDateStyle;
    private int dialogUnreadDateColor;
    private int dialogUnreadDateStyle;

    private boolean dialogUnreadBubbleEnabled;
    private int dialogUnreadBubbleTextColor;
    private int dialogUnreadBubbleTextSize;
    private int dialogUnreadBubbleTextStyle;
    private int dialogUnreadBubbleBackgroundColor;

    private int dialogAvatarWidth;
    private int dialogAvatarHeight;

    private boolean dialogMessageAvatarEnabled;
    private int dialogMessageAvatarWidth;
    private int dialogMessageAvatarHeight;

    private boolean dialogDividerEnabled;
    private int dialogDividerColor;
    private int dialogDividerLeftPadding;
    private int dialogDividerRightPadding;

    private int dialogItemBackground;
    private int dialogUnreadItemBackground;

    static DialogListStyle parse(Context context, AttributeSet attrs) {
        DialogListStyle style = new DialogListStyle(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.DialogsList);

        //Item background
        style.dialogItemBackground = typedArray.getColor(R.styleable.DialogsList_dialogItemBackground,
                style.getColor(R.color.transparent));
        style.dialogUnreadItemBackground = typedArray.getColor(R.styleable.DialogsList_dialogUnreadItemBackground,
                style.getColor(R.color.transparent));

        //Title text
        style.dialogTitleTextColor = typedArray.getColor(R.styleable.DialogsList_dialogTitleTextColor,
                style.getColor(R.color.dialog_title_text));
        style.dialogTitleTextSize = typedArray.getDimensionPixelSize(R.styleable.DialogsList_dialogTitleTextSize,
                context.getResources().getDimensionPixelSize(R.dimen.dialog_title_text_size));
        style.dialogTitleTextStyle = typedArray.getInt(R.styleable.DialogsList_dialogTitleTextStyle, Typeface.NORMAL);

        //Title unread text
        style.dialogUnreadTitleTextColor = typedArray.getColor(R.styleable.DialogsList_dialogUnreadTitleTextColor,
                style.getColor(R.color.dialog_title_text));
        style.dialogUnreadTitleTextStyle = typedArray.getInt(R.styleable.DialogsList_dialogUnreadTitleTextStyle, Typeface.NORMAL);

        //Message text
        style.dialogMessageTextColor = typedArray.getColor(R.styleable.DialogsList_dialogMessageTextColor,
                style.getColor(R.color.dialog_message_text));
        style.dialogMessageTextSize = typedArray.getDimensionPixelSize(R.styleable.DialogsList_dialogMessageTextSize,
                context.getResources().getDimensionPixelSize(R.dimen.dialog_message_text_size));
        style.dialogMessageTextStyle = typedArray.getInt(R.styleable.DialogsList_dialogMessageTextStyle, Typeface.NORMAL);

        //Message unread text
        style.dialogUnreadMessageTextColor = typedArray.getColor(R.styleable.DialogsList_dialogUnreadMessageTextColor,
                style.getColor(R.color.dialog_message_text));
        style.dialogUnreadMessageTextStyle = typedArray.getInt(R.styleable.DialogsList_dialogUnreadMessageTextStyle, Typeface.NORMAL);

        //Date text
        style.dialogDateColor = typedArray.getColor(R.styleable.DialogsList_dialogDateColor,
                style.getColor(R.color.dialog_date_text));
        style.dialogDateSize = typedArray.getDimensionPixelSize(R.styleable.DialogsList_dialogDateSize,
                context.getResources().getDimensionPixelSize(R.dimen.dialog_date_text_size));
        style.dialogDateStyle = typedArray.getInt(R.styleable.DialogsList_dialogDateStyle, Typeface.NORMAL);

        //Date unread text
        style.dialogUnreadDateColor = typedArray.getColor(R.styleable.DialogsList_dialogUnreadDateColor,
                style.getColor(R.color.dialog_date_text));
        style.dialogUnreadDateStyle = typedArray.getInt(R.styleable.DialogsList_dialogUnreadDateStyle, Typeface.NORMAL);

        //Unread bubble
        style.dialogUnreadBubbleEnabled = typedArray.getBoolean(R.styleable.DialogsList_dialogUnreadBubbleEnabled, true);
        style.dialogUnreadBubbleBackgroundColor = typedArray.getColor(R.styleable.DialogsList_dialogUnreadBubbleBackgroundColor,
                style.getColor(R.color.dialog_unread_bubble));

        //Unread bubble text
        style.dialogUnreadBubbleTextColor = typedArray.getColor(R.styleable.DialogsList_dialogUnreadBubbleTextColor,
                style.getColor(R.color.dialog_unread_text));
        style.dialogUnreadBubbleTextSize = typedArray.getDimensionPixelSize(R.styleable.DialogsList_dialogUnreadBubbleTextSize,
                context.getResources().getDimensionPixelSize(R.dimen.dialog_unread_bubble_text_size));
        style.dialogUnreadBubbleTextStyle = typedArray.getInt(R.styleable.DialogsList_dialogUnreadBubbleTextStyle, Typeface.NORMAL);

        //Avatar
        style.dialogAvatarWidth = typedArray.getDimensionPixelSize(R.styleable.DialogsList_dialogAvatarWidth,
                context.getResources().getDimensionPixelSize(R.dimen.dialog_avatar_width));
        style.dialogAvatarHeight = typedArray.getDimensionPixelSize(R.styleable.DialogsList_dialogAvatarHeight,
                context.getResources().getDimensionPixelSize(R.dimen.dialog_avatar_height));

        //Last message avatar
        style.dialogMessageAvatarEnabled = typedArray.getBoolean(R.styleable.DialogsList_dialogMessageAvatarEnabled, true);
        style.dialogMessageAvatarWidth = typedArray.getDimensionPixelSize(R.styleable.DialogsList_dialogMessageAvatarWidth,
                context.getResources().getDimensionPixelSize(R.dimen.dialog_last_message_avatar_width));
        style.dialogMessageAvatarHeight = typedArray.getDimensionPixelSize(R.styleable.DialogsList_dialogMessageAvatarHeight,
                context.getResources().getDimensionPixelSize(R.dimen.dialog_last_message_avatar_height));

        //Divider
        style.dialogDividerEnabled = typedArray.getBoolean(R.styleable.DialogsList_dialogDividerEnabled, true);
        style.dialogDividerColor = typedArray.getColor(R.styleable.DialogsList_dialogDividerColor, style.getColor(R.color.dialog_divider));
        style.dialogDividerLeftPadding = typedArray.getDimensionPixelSize(R.styleable.DialogsList_dialogDividerLeftPadding,
                context.getResources().getDimensionPixelSize(R.dimen.dialog_divider_margin_left));
        style.dialogDividerRightPadding = typedArray.getDimensionPixelSize(R.styleable.DialogsList_dialogDividerRightPadding,
                context.getResources().getDimensionPixelSize(R.dimen.dialog_divider_margin_right));

        typedArray.recycle();

        return style;
    }

    private DialogListStyle(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public int getDialogTitleTextColor() {
        return dialogTitleTextColor;
    }

    public int getDialogTitleTextSize() {
        return dialogTitleTextSize;
    }

    public int getDialogTitleTextStyle() {
        return dialogTitleTextStyle;
    }

    public int getDialogUnreadTitleTextColor() {
        return dialogUnreadTitleTextColor;
    }

    public int getDialogUnreadTitleTextStyle() {
        return dialogUnreadTitleTextStyle;
    }

    public int getDialogMessageTextColor() {
        return dialogMessageTextColor;
    }

    public int getDialogMessageTextSize() {
        return dialogMessageTextSize;
    }

    public int getDialogMessageTextStyle() {
        return dialogMessageTextStyle;
    }

    public int getDialogUnreadMessageTextColor() {
        return dialogUnreadMessageTextColor;
    }

    public int getDialogUnreadMessageTextStyle() {
        return dialogUnreadMessageTextStyle;
    }

    public int getDialogDateColor() {
        return dialogDateColor;
    }

    public int getDialogDateSize() {
        return dialogDateSize;
    }

    public int getDialogDateStyle() {
        return dialogDateStyle;
    }

    public int getDialogUnreadDateColor() {
        return dialogUnreadDateColor;
    }

    public int getDialogUnreadDateStyle() {
        return dialogUnreadDateStyle;
    }

    public boolean isDialogUnreadBubbleEnabled() {
        return dialogUnreadBubbleEnabled;
    }

    public int getDialogUnreadBubbleTextColor() {
        return dialogUnreadBubbleTextColor;
    }

    public int getDialogUnreadBubbleTextSize() {
        return dialogUnreadBubbleTextSize;
    }

    public int getDialogUnreadBubbleTextStyle() {
        return dialogUnreadBubbleTextStyle;
    }

    public int getDialogUnreadBubbleBackgroundColor() {
        return dialogUnreadBubbleBackgroundColor;
    }

    public int getDialogAvatarWidth() {
        return dialogAvatarWidth;
    }

    public int getDialogAvatarHeight() {
        return dialogAvatarHeight;
    }

    public boolean isDialogDividerEnabled() {
        return dialogDividerEnabled;
    }

    public int getDialogDividerColor() {
        return dialogDividerColor;
    }

    public int getDialogDividerLeftPadding() {
        return dialogDividerLeftPadding;
    }

    public int getDialogDividerRightPadding() {
        return dialogDividerRightPadding;
    }

    public int getDialogItemBackground() {
        return dialogItemBackground;
    }

    public int getDialogUnreadItemBackground() {
        return dialogUnreadItemBackground;
    }

    public boolean isDialogMessageAvatarEnabled() {
        return dialogMessageAvatarEnabled;
    }

    public int getDialogMessageAvatarWidth() {
        return dialogMessageAvatarWidth;
    }

    public int getDialogMessageAvatarHeight() {
        return dialogMessageAvatarHeight;
    }
}
