package com.example.lab04;

import android.os.Parcel;
import android.os.Parcelable;

public class MotorBike implements Parcelable {
    private String brand;
    private String category;
    private boolean isElectric;
    private boolean hasABS;
    private String engineType;
    private float rating;
    private int engineCapacityCc;
    private double weightKg;

    public MotorBike(String brand, String category, boolean isElectric, boolean hasABS,
                     String engineType, float rating, int engineCapacityCc, double weightKg) {
        this.brand = brand;
        this.category = category;
        this.isElectric = isElectric;
        this.hasABS = hasABS;
        this.engineType = engineType;
        this.rating = rating;
        this.engineCapacityCc = engineCapacityCc;
        this.weightKg = weightKg;
    }

    protected MotorBike(Parcel in) {
        brand = in.readString();
        category = in.readString();
        isElectric = in.readByte() != 0;
        hasABS = in.readByte() != 0;
        engineType = in.readString();
        rating = in.readFloat();
        engineCapacityCc = in.readInt();
        weightKg = in.readDouble();
    }

    public static final Creator<MotorBike> CREATOR = new Creator<MotorBike>() {
        @Override
        public MotorBike createFromParcel(Parcel in) {
            return new MotorBike(in);
        }

        @Override
        public MotorBike[] newArray(int size) {
            return new MotorBike[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(brand);
        dest.writeString(category);
        dest.writeByte((byte) (isElectric ? 1 : 0));
        dest.writeByte((byte) (hasABS ? 1 : 0));
        dest.writeString(engineType);
        dest.writeFloat(rating);
        dest.writeInt(engineCapacityCc);
        dest.writeDouble(weightKg);
    }

    @Override
    public String toString() {
        return brand + " (" + category + ") - " + engineCapacityCc + " cm³, " + weightKg + " kg" +
                "\nEngine: " + engineType + " | Electric: " + isElectric + " | ABS: " + hasABS +
                "\nRating: " + rating + "/5.0";
    }
}