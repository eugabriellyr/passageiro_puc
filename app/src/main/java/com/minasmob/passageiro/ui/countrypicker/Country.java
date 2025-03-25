package com.minasmob.passageiro.ui.countrypicker;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import com.minasmob.passageiro.R;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class Country {
    private static final Country[] COUNTRIES = {

            new Country("BR", "Brazil", "+55", R.drawable.flag_br),

    };
    private static List<Country> allCountriesList;
    private String code;
    private String name;
    private String dialCode;
    private int flag = -1;

    public Country(String code, String name, String dialCode, int flag) {
        this.code = code;
        this.name = name;
        this.dialCode = dialCode;
        this.flag = flag;
    }


    public Country() {
    }

    public static List<Country> getAllCountries() {
        if (allCountriesList == null) {
            allCountriesList = Arrays.asList(COUNTRIES);
        }
        return allCountriesList;
    }

    private static Country getCountryByISO(String countryIsoCode) {
        countryIsoCode = countryIsoCode.toUpperCase();

        Country c = new Country();
        c.setCode(countryIsoCode);

        int i = Arrays.binarySearch(COUNTRIES, c, new ISOCodeComparator());

        if (i < 0) {
            return null;
        } else {
            return COUNTRIES[i];
        }
    }

    public static Country getCountryByName(String countryName) {
        for (Country c : COUNTRIES)
            if (countryName.equals(c.getName())) return c;
        return null;
    }

    public static Country getCountryByLocale(Locale locale) {
        String countryIsoCode = locale.getISO3Country().substring(0, 2).toLowerCase();
        return Country.getCountryByISO(countryIsoCode);
    }

    public static Country getCountryFromSIM(Context context) {
        TelephonyManager telephonyManager =
                (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (telephonyManager.getSimState() != TelephonyManager.SIM_STATE_ABSENT)
            return Country.getCountryByISO(telephonyManager.getSimCountryIso());
        return null;
    }

    public String getDialCode() {
        return dialCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
        if (TextUtils.isEmpty(name)) {
            name = new Locale("", code).getDisplayName();
        }
    }

    public String getName() {
        return name;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    void loadFlagByCode(Context context) {
        if (this.flag != -1)
            return;

        try {
            this.flag = context.getResources().getIdentifier(
                    "flag_" + this.code.toLowerCase(Locale.ENGLISH),
                    "drawable",
                    context.getPackageName());
        } catch (Exception e) {
            e.printStackTrace();
            this.flag = -1;
        }
    }

    public static class ISOCodeComparator implements Comparator<Country> {
        @Override
        public int compare(Country country, Country t1) {
            return country.code.compareTo(t1.code);
        }
    }


    public static class NameComparator implements Comparator<Country> {
        @Override
        public int compare(Country country, Country t1) {
            return country.name.compareTo(t1.name);
        }
    }
}