package felix.libdemo.Bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by huangmf on 8/16/2017.
 */

public class AtyBean implements Parcelable {
    private String mName;

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mName);
    }

    public AtyBean() {
    }

    protected AtyBean(Parcel in) {
        this.mName = in.readString();
    }

    public static final Parcelable.Creator<AtyBean> CREATOR = new Parcelable.Creator<AtyBean>() {
        @Override
        public AtyBean createFromParcel(Parcel source) {
            return new AtyBean(source);
        }

        @Override
        public AtyBean[] newArray(int size) {
            return new AtyBean[size];
        }
    };
}
