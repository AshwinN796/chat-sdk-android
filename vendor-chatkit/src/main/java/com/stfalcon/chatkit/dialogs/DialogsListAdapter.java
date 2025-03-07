/******************************************************************************
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

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.stfalcon.chatkit.R;
import com.stfalcon.chatkit.commons.ImageLoader;
import com.stfalcon.chatkit.commons.ViewHolder;
import com.stfalcon.chatkit.commons.models.IDialog;
import com.stfalcon.chatkit.commons.models.IMessage;
import com.stfalcon.chatkit.commons.models.IUser;
import com.stfalcon.chatkit.utils.DateFormatter;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Adapter for {@link DialogsList}
 */
@SuppressWarnings("WeakerAccess")
public class DialogsListAdapter<DIALOG extends IDialog>
        extends RecyclerView.Adapter<DialogsListAdapter.BaseDialogViewHolder> {

    public List<DIALOG> items = new ArrayList<>();
    private int itemLayoutId;
    private Class<? extends BaseDialogViewHolder> holderClass;
    private ImageLoader imageLoader;
    private OnDialogClickListener<DIALOG> onDialogClickListener;
    private OnDialogViewClickListener<DIALOG> onDialogViewClickListener;
    private OnDialogLongClickListener<DIALOG> onLongItemClickListener;
    private OnDialogViewLongClickListener<DIALOG> onDialogViewLongClickListener;
    private DialogListStyle dialogStyle;
    private DateFormatter.Formatter datesFormatter;

    /**
     * For default list item layout and view holder
     *
     * @param imageLoader image loading method
     */
    public DialogsListAdapter(ImageLoader imageLoader) {
        this(R.layout.item_dialog, DialogViewHolder.class, imageLoader);
    }

    public List<DIALOG> getItems() {
        return items;
    }

    /**
     * For custom list item layout and default view holder
     *
     * @param itemLayoutId custom list item resource id
     * @param imageLoader  image loading method
     */
    public DialogsListAdapter(@LayoutRes int itemLayoutId, ImageLoader imageLoader) {
        this(itemLayoutId, DialogViewHolder.class, imageLoader);
    }

    /**
     * For custom list item layout and custom view holder
     *
     * @param itemLayoutId custom list item resource id
     * @param holderClass  custom view holder class
     * @param imageLoader  image loading method
     */
    public DialogsListAdapter(@LayoutRes int itemLayoutId, Class<? extends BaseDialogViewHolder> holderClass,
                              ImageLoader imageLoader) {
        this.itemLayoutId = itemLayoutId;
        this.holderClass = holderClass;
        this.imageLoader = imageLoader;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onBindViewHolder(BaseDialogViewHolder holder, int position) {
        holder.setImageLoader(imageLoader);
        holder.setOnDialogClickListener(onDialogClickListener);
        holder.setOnDialogViewClickListener(onDialogViewClickListener);
        holder.setOnLongItemClickListener(onLongItemClickListener);
        holder.setOnDialogViewLongClickListener(onDialogViewLongClickListener);
        holder.setDatesFormatter(datesFormatter);
        holder.onBind(getItem(position));
    }

    @Override
    public BaseDialogViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(itemLayoutId, parent, false);
        //create view holder by reflation
        try {
            Constructor<? extends BaseDialogViewHolder> constructor = holderClass.getDeclaredConstructor(View.class);
            constructor.setAccessible(true);
            BaseDialogViewHolder baseDialogViewHolder = constructor.newInstance(v);
            if (baseDialogViewHolder instanceof DialogViewHolder) {
                ((DialogViewHolder) baseDialogViewHolder).setDialogStyle(dialogStyle);
            }
            return baseDialogViewHolder;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public DIALOG getItem(int position) {
        return getItems().get(position);
    }

    public DIALOG removeItem(int position) {
        return getItems().remove(position);
    }

    public int indexOf(DIALOG item) {
        return getItems().indexOf(item);
    }



    /**
     * @return size of dialogs list
     */
    @Override
    public int getItemCount() {
        return getItems().size();
    }

    /**
     * remove item with id
     *
     * @param id dialog i
     */
    public void deleteById(String id) {
        for (int i = 0; i < getItemCount(); i++) {
            if (getItem(i).getId().equals(id)) {
                removeItem(i);
                notifyItemRemoved(i);
            }
        }
    }

    /**
     * Returns {@code true} if, and only if, dialogs count in adapter is non-zero.
     *
     * @return {@code true} if size is 0, otherwise {@code false}
     */
    public boolean isEmpty() {
        return getItems().isEmpty();
    }

    /**
     * clear dialogs list
     */
    public void clear() {
        if (getItems() != null) {
            getItems().clear();
        }
        notifyDataSetChanged();
    }

    /**
     * Set dialogs list
     *
     * @param items dialogs list
     */
    public void setItems(List<DIALOG> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    /**
     * Add dialogs items
     *
     * @param newItems new dialogs list
     */
    public void addItems(List<DIALOG> newItems) {
        if (newItems != null) {
            if (items == null) {
                setItems(new ArrayList<>());
            }
            int curSize = getItemCount();
            getItems().addAll(newItems);
            notifyItemRangeInserted(curSize, getItemCount());
        }
    }

    /**
     * Add dialog to the end of dialogs list
     *
     * @param dialog dialog item
     */
    public void addItem(DIALOG dialog) {
        getItems().add(dialog);
        notifyItemInserted(getItemCount() - 1);
    }

    /**
     * Add dialog to dialogs list
     *
     * @param dialog   dialog item
     * @param position position in dialogs list
     */
    public void addItem(int position, DIALOG dialog) {
        getItems().add(position, dialog);
        notifyItemInserted(position);
    }

    /**
     * Move an item
     * @param fromPosition the actual position of the item
     * @param toPosition the new position of the item
     */
    public void moveItem(int fromPosition, int toPosition) {
        DIALOG dialog = removeItem(fromPosition);
        getItems().add(toPosition, dialog);
        notifyItemMoved(fromPosition, toPosition);
    }

    /**
     * Update dialog by position in dialogs list
     *
     * @param position position in dialogs list
     * @param item     new dialog item
     */
    public void updateItem(int position, DIALOG item) {
        if (items == null) {
            setItems(new ArrayList<>());
        }
        getItems().set(position, item);
        notifyItemChanged(position);
    }

    /**
     * Update dialog by dialog id
     *
     * @param item new dialog item
     */
    public void updateItemById(DIALOG item) {
        if (items == null) {
            setItems(new ArrayList<>());
        }
        for (int i = 0; i < getItemCount(); i++) {
            if (getItem(i).getId().equals(item.getId())) {
                getItems().set(i, item);
                notifyItemChanged(i);
                break;
            }
        }
    }

    /**
     * Upsert dialog in dialogs list or add it to then end of dialogs list
     *
     * @param item dialog item
     */
    public void upsertItem(DIALOG item) {
        boolean updated = false;
        for (int i = 0; i < getItemCount(); i++) {
            if (getItem(i).getId().equals(item.getId())) {
                getItems().set(i, item);
                notifyItemChanged(i);
                updated = true;
                break;
            }
        }
        if (!updated) {
            addItem(item);
        }
    }

    /**
     * Find an item by its id
     *
     * @param id the wanted item's id
     * @return the found item, or null
     */
    @Nullable
    public DIALOG getItemById(String id) {
        if (items == null) {
            setItems(new ArrayList<>());
        }
        for (DIALOG item : items) {
            if (item.getId() == null && id == null) {
                return item;
            } else if (item.getId() != null && item.getId().equals(id)) {
                return item;
            }
        }
        return null;
    }

    /**
     * Update last message in dialog and swap item to top of list.
     *
     * @param dialogId Dialog ID
     * @param message  New message
     * @return false if dialog doesn't exist.
     */
    @SuppressWarnings("unchecked")
    public boolean updateDialogWithMessage(String dialogId, IMessage message) {
        boolean dialogExist = false;
        for (int i = 0; i < getItemCount(); i++) {
            if (getItem(i).getId().equals(dialogId)) {
                getItem(i).setLastMessage(message);
                notifyItemChanged(i);
                if (i != 0) {
                    Collections.swap(items, i, 0);
                    notifyItemMoved(i, 0);
                }
                dialogExist = true;
                break;
            }
        }
        return dialogExist;
    }

    /**
     * Sort dialog by last message date
     */
    public void sortByLastMessageDate() {
        Collections.sort(items, new Comparator<DIALOG>() {
            @Override
            public int compare(DIALOG o1, DIALOG o2) {
                if (o1.getLastMessage() == null && o2.getLastMessage() == null) {
                    return 0;
                } else if (o1.getLastMessage() == null) {
                    return 1;
                } else if (o2.getLastMessage() == null) {
                    return -1;
                }
                else if (o1.getLastMessage().getCreatedAt().after(o2.getLastMessage().getCreatedAt())) {
                    return -1;
                } else if (o1.getLastMessage().getCreatedAt().before(o2.getLastMessage().getCreatedAt())) {
                    return 1;
                } else return 0;
            }
        });
        notifyDataSetChanged();
    }

    /**
     * Sort items with rules of comparator
     *
     * @param comparator Comparator
     */
    public void sort(Comparator<DIALOG> comparator) {
        Collections.sort(items, comparator);
        notifyDataSetChanged();
    }

    /**
     * @return registered image loader
     */
    public ImageLoader getImageLoader() {
        return imageLoader;
    }

    /**
     * Register a callback to be invoked when image need to load.
     *
     * @param imageLoader image loading method
     */
    public void setImageLoader(ImageLoader imageLoader) {
        this.imageLoader = imageLoader;
    }

    /**
     * @return the item click callback.
     */
    public OnDialogClickListener getOnDialogClickListener() {
        return onDialogClickListener;
    }

    /**
     * Register a callback to be invoked when item is clicked.
     *
     * @param onDialogClickListener on click item callback
     */
    public void setOnDialogClickListener(OnDialogClickListener<DIALOG> onDialogClickListener) {
        this.onDialogClickListener = onDialogClickListener;
    }

    /**
     * @return the view click callback.
     */
    public OnDialogViewClickListener getOnDialogViewClickListener() {
        return onDialogViewClickListener;
    }

    /**
     * Register a callback to be invoked when dialog view is clicked.
     *
     * @param clickListener on click item callback
     */
    public void setOnDialogViewClickListener(OnDialogViewClickListener<DIALOG> clickListener) {
        this.onDialogViewClickListener = clickListener;
    }

    /**
     * @return on long click item callback
     */
    public OnDialogLongClickListener getOnLongItemClickListener() {
        return onLongItemClickListener;
    }

    /**
     * Register a callback to be invoked when item is long clicked.
     *
     * @param onLongItemClickListener on long click item callback
     */
    public void setOnDialogLongClickListener(OnDialogLongClickListener<DIALOG> onLongItemClickListener) {
        this.onLongItemClickListener = onLongItemClickListener;
    }

    /**
     * @return on view long click callback
     */
    public OnDialogViewLongClickListener<DIALOG> getOnDialogViewLongClickListener() {
        return onDialogViewLongClickListener;
    }

    /**
     * Register a callback to be invoked when item view is long clicked.
     *
     * @param clickListener on long click item callback
     */
    public void setOnDialogViewLongClickListener(OnDialogViewLongClickListener<DIALOG> clickListener) {
        this.onDialogViewLongClickListener = clickListener;
    }

    /**
     * Sets custom {@link DateFormatter.Formatter} for text representation of last message date.
     */
    public void setDatesFormatter(DateFormatter.Formatter datesFormatter) {
        this.datesFormatter = datesFormatter;
    }

    //TODO ability to set style programmatically
    void setStyle(DialogListStyle dialogStyle) {
        this.dialogStyle = dialogStyle;
    }

    /**
    * @return the position of a dialog in the dialogs list.
    */
    public int getDialogPosition(DIALOG dialog) {
        return this.getItems().indexOf(dialog);
    }

    /*
    * LISTENERS
    * */
    public interface OnDialogClickListener<DIALOG extends IDialog> {
        void onDialogClick(DIALOG dialog);
    }

    public interface OnDialogViewClickListener<DIALOG extends IDialog> {
        void onDialogViewClick(View view, DIALOG dialog);
    }

    public interface OnDialogLongClickListener<DIALOG extends IDialog> {
        void onDialogLongClick(DIALOG dialog);
    }

    public interface OnDialogViewLongClickListener<DIALOG extends IDialog> {
        void onDialogViewLongClick(View view, DIALOG dialog);
    }

    /*
    * HOLDERS
    * */
    public abstract static class BaseDialogViewHolder<DIALOG extends IDialog>
            extends ViewHolder<DIALOG> {

        public ImageLoader imageLoader;
        public OnDialogClickListener<DIALOG> onDialogClickListener;
        public OnDialogLongClickListener<DIALOG> onLongItemClickListener;
        public OnDialogViewClickListener<DIALOG> onDialogViewClickListener;
        public OnDialogViewLongClickListener<DIALOG> onDialogViewLongClickListener;
        public DateFormatter.Formatter datesFormatter;

        public BaseDialogViewHolder(View itemView) {
            super(itemView);
        }

        public void setImageLoader(ImageLoader imageLoader) {
            this.imageLoader = imageLoader;
        }

        public void setOnDialogClickListener(OnDialogClickListener<DIALOG> onDialogClickListener) {
            this.onDialogClickListener = onDialogClickListener;
        }

        public void setOnDialogViewClickListener(OnDialogViewClickListener<DIALOG> onDialogViewClickListener) {
            this.onDialogViewClickListener = onDialogViewClickListener;
        }

        public void setOnLongItemClickListener(OnDialogLongClickListener<DIALOG> onLongItemClickListener) {
            this.onLongItemClickListener = onLongItemClickListener;
        }

        public void setOnDialogViewLongClickListener(OnDialogViewLongClickListener<DIALOG> onDialogViewLongClickListener) {
            this.onDialogViewLongClickListener = onDialogViewLongClickListener;
        }

        public void setDatesFormatter(DateFormatter.Formatter dateHeadersFormatter) {
            this.datesFormatter = dateHeadersFormatter;
        }
    }

    public static class DialogViewHolder<DIALOG extends IDialog> extends BaseDialogViewHolder<DIALOG> {
        public DialogListStyle dialogStyle;
        public ViewGroup container;
        public ViewGroup root;
        public TextView tvName;
        public TextView tvDate;
        public ImageView ivAvatar;
        public ImageView ivLastMessageUser;
        public TextView tvLastMessage;
        public TextView tvBubble;
        public ViewGroup dividerContainer;
        public View divider;

        public DialogViewHolder(View itemView) {
            super(itemView);
            root = (ViewGroup) itemView.findViewById(R.id.dialogRootLayout);
            container = (ViewGroup) itemView.findViewById(R.id.dialogContainer);
            tvName = (TextView) itemView.findViewById(R.id.dialogName);
            tvDate = (TextView) itemView.findViewById(R.id.dialogDate);
            tvLastMessage = (TextView) itemView.findViewById(R.id.dialogLastMessage);
            tvBubble = (TextView) itemView.findViewById(R.id.dialogUnreadBubble);
            ivLastMessageUser = (ImageView) itemView.findViewById(R.id.dialogLastMessageUserAvatar);
            ivAvatar = (ImageView) itemView.findViewById(R.id.dialogAvatar);
            dividerContainer = (ViewGroup) itemView.findViewById(R.id.dialogDividerContainer);
            divider = itemView.findViewById(R.id.dialogDivider);

        }

        private void applyStyle() {
            if (dialogStyle != null) {
                //Texts
                if (tvName != null) {
                    tvName.setTextSize(TypedValue.COMPLEX_UNIT_PX, dialogStyle.getDialogTitleTextSize());
                }

                if (tvLastMessage != null) {
                    tvLastMessage.setTextSize(TypedValue.COMPLEX_UNIT_PX, dialogStyle.getDialogMessageTextSize());
                }

                if (tvDate != null) {
                    tvDate.setTextSize(TypedValue.COMPLEX_UNIT_PX, dialogStyle.getDialogDateSize());
                }

                //Divider
                if (divider != null)
                    divider.setBackgroundColor(dialogStyle.getDialogDividerColor());
                if (dividerContainer != null)
                    dividerContainer.setPadding(dialogStyle.getDialogDividerLeftPadding(), 0,
                            dialogStyle.getDialogDividerRightPadding(), 0);
                //Avatar
                if (ivAvatar != null) {
                    ivAvatar.getLayoutParams().width = dialogStyle.getDialogAvatarWidth();
                    ivAvatar.getLayoutParams().height = dialogStyle.getDialogAvatarHeight();
                }

                //Last message user avatar
                if (ivLastMessageUser != null) {
                    ivLastMessageUser.getLayoutParams().width = dialogStyle.getDialogMessageAvatarWidth();
                    ivLastMessageUser.getLayoutParams().height = dialogStyle.getDialogMessageAvatarHeight();
                }

                //Unread bubble
                if (tvBubble != null) {
                    GradientDrawable bgShape = (GradientDrawable) tvBubble.getBackground();
                    bgShape.setColor(dialogStyle.getDialogUnreadBubbleBackgroundColor());
                    tvBubble.setVisibility(dialogStyle.isDialogDividerEnabled() ? VISIBLE : GONE);
                    tvBubble.setTextSize(TypedValue.COMPLEX_UNIT_PX, dialogStyle.getDialogUnreadBubbleTextSize());
                    tvBubble.setTextColor(dialogStyle.getDialogUnreadBubbleTextColor());
                    tvBubble.setTypeface(tvBubble.getTypeface(), dialogStyle.getDialogUnreadBubbleTextStyle());
                }
            }
        }


        private void applyDefaultStyle() {
            if (dialogStyle != null) {
                if (root != null) {
                    root.setBackgroundColor(dialogStyle.getDialogItemBackground());
                }

                if (tvName != null) {
                    tvName.setTextColor(dialogStyle.getDialogTitleTextColor());
                    tvName.setTypeface(Typeface.DEFAULT, dialogStyle.getDialogTitleTextStyle());
                }

                if (tvDate != null) {
                    tvDate.setTextColor(dialogStyle.getDialogDateColor());
                    tvDate.setTypeface(Typeface.DEFAULT, dialogStyle.getDialogDateStyle());
                }

                if (tvLastMessage != null) {
                    tvLastMessage.setTextColor(dialogStyle.getDialogMessageTextColor());
                    tvLastMessage.setTypeface(Typeface.DEFAULT, dialogStyle.getDialogMessageTextStyle());
                }
            }
        }

        private void applyUnreadStyle() {
            if (dialogStyle != null) {
                if (root != null) {
                    root.setBackgroundColor(dialogStyle.getDialogUnreadItemBackground());
                }

                if (tvName != null) {
                    tvName.setTextColor(dialogStyle.getDialogUnreadTitleTextColor());
                    tvName.setTypeface(Typeface.DEFAULT, dialogStyle.getDialogUnreadTitleTextStyle());
                }

                if (tvDate != null) {
                    tvDate.setTextColor(dialogStyle.getDialogUnreadDateColor());
                    tvDate.setTypeface(Typeface.DEFAULT, dialogStyle.getDialogUnreadDateStyle());
                }

                if (tvLastMessage != null) {
                    tvLastMessage.setTextColor(dialogStyle.getDialogUnreadMessageTextColor());
                    tvLastMessage.setTypeface(Typeface.DEFAULT, dialogStyle.getDialogUnreadMessageTextStyle());
                }
            }
        }


        @Override
        public void onBind(final DIALOG dialog) {
            if (dialog.getUnreadCount() > 0) {
                applyUnreadStyle();
            } else {
                applyDefaultStyle();
            }

            //Set Name
            tvName.setText(dialog.getDialogName());

            //Set Date
            String formattedDate = null;

            if (dialog.getLastMessage() != null) {
                Date lastMessageDate = dialog.getLastMessage().getCreatedAt();
                if (datesFormatter != null) formattedDate = datesFormatter.format(lastMessageDate);
                tvDate.setText(formattedDate == null
                        ? getDateString(lastMessageDate)
                        : formattedDate);
            } else {
                tvDate.setText(null);
            }

            //Set Dialog avatar
            if (imageLoader != null) {
                imageLoader.loadImage(ivAvatar, dialog.getDialogPhoto(), dialog);
            }

            //Set Last message user avatar with check if there is last message
            if (imageLoader != null && dialog.getLastMessage() != null) {
                IUser user = dialog.getLastMessage().getUser();
                imageLoader.loadImage(ivLastMessageUser, user.getAvatar(), user);
            }
            ivLastMessageUser.setVisibility(dialogStyle.isDialogMessageAvatarEnabled()
                    && dialog.getUsers().size() > 1
                    && dialog.getLastMessage() != null ? VISIBLE : GONE);

            //Set Last message text
            if (dialog.getLastMessage() != null) {
                tvLastMessage.setText(dialog.getLastMessage().getPreview());
            } else {
                tvLastMessage.setText(null);
            }

            //Set Unread message count bubble
            tvBubble.setText(String.valueOf(dialog.getUnreadCount()));
            tvBubble.setVisibility(dialogStyle.isDialogUnreadBubbleEnabled() &&
                    dialog.getUnreadCount() > 0 ? VISIBLE : GONE);

            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onDialogClickListener != null) {
                        onDialogClickListener.onDialogClick(dialog);
                    }
                    if (onDialogViewClickListener != null) {
                        onDialogViewClickListener.onDialogViewClick(view, dialog);
                    }
                }
            });


            container.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    if (onLongItemClickListener != null) {
                        onLongItemClickListener.onDialogLongClick(dialog);
                    }
                    if (onDialogViewLongClickListener != null) {
                        onDialogViewLongClickListener.onDialogViewLongClick(view, dialog);
                    }
                    return onLongItemClickListener != null || onDialogViewLongClickListener != null;
                }
            });
        }

        public String getDateString(Date date) {
            return DateFormatter.format(date, DateFormatter.Template.TIME);
        }

        public DialogListStyle getDialogStyle() {
            return dialogStyle;
        }

        public void setDialogStyle(DialogListStyle dialogStyle) {
            this.dialogStyle = dialogStyle;
            applyStyle();
        }
    }
}
