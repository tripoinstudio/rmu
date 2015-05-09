package com.tripoin.rmu.model.DTO.print_message;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Achmad Fauzi on 5/7/2015 : 10:16 PM.
 * mailto : fauzi.knightmaster.achmad@gmail.com
 */
public class MessageItemDTO implements Parcelable {

    private String menuName;
    private String amount;

    public MessageItemDTO(Parcel in) {
        readFromParcel(in);
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    private void readFromParcel(Parcel in) {
        menuName = in.readString();
        amount = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(menuName);
        dest.writeString(amount);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        @Override
        public Object createFromParcel(Parcel source) {
            return new MessageItemDTO(source);
        }

        @Override
        public Object[] newArray(int size) {
            return new MessageItemDTO[size];
        }
    };
}
