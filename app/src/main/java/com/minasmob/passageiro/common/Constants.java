package com.minasmob.passageiro.common;

public interface Constants {

    public static String Currency = "";




    public interface SharedPref {
        String LOGGGED_IN = "LOGGGED_IN";
        String EMAIL = "EMAIL";
        String TXT_EMAIL = "TXTEMAIL";
        String OTP = "OTP";
        String ID = "ID_";
        String USER_ID = "USER_ID";
        String USER_NAME = "USER_NAME";
        String USER_AVATAR = "USER_AVATAR";
        String DEVICE_TOKEN = "DEVICE_TOKEN";
        String DEVICE_ID = "DEVICE_ID";
        String ACCESS_TOKEN = "ACCESS_TOKEN";
        String DIAL_CODE = "DIAL_CODE";
        String MOBILE = "MOBILE";
        String CANCEL_ID = "CANCEL_ID";
        String REQUEST_ID = "REQUEST_ID";
        String CURRENCY = "CURRENCY";
        String SERVICE_TYPE = "SERVICE_TYPE";
        String STRIPE_PUBLISHABLE_KEY = "STRIPE_PUBLISHABLE_KEY";
        String LATITUDE = "LATITUDE";
        String LONGITUDE = "LONGITUDE";
        String PICTURE = "PICTURE";
        String USER_INFO = "USER_INFO";
    }



    interface RIDE_REQUEST {
        String DEST_ADD = "d_address";
        String DEST_LAT = "d_latitude";
        String DEST_LONG = "d_longitude";
        String SRC_ADD = "s_address";
        String SRC_LAT = "s_latitude";
        String SRC_LONG = "s_longitude";
        String PAYMENT_MODE = "payment_mode";
        String CARD_ID = "card_id";
        String CARD_LAST_FOUR = "card_last_four";
        String DISTANCE_VAL = "distance";
        String SERVICE_TYPE = "service_type";
    }

    interface BroadcastReceiver {
        String INTENT_FILTER = "INTENT_FILTER";
    }

    interface Language {
        String ENGLISH = "en";
        String ARABIC = "ar";
    }

    interface MeasurementType {
        String KM = "Kms";
        String MILES = "miles";
    }

    interface Status {
        String EMPTY = "EMPTY";
        String SERVICE = "SERVICE";
        String SEARCHING = "SEARCHING";
        String STARTED = "STARTED";
        String ARRIVED = "ARRIVED";
        String PICKED_UP = "PICKEDUP";
        String DROPPED = "DROPPED";
        String COMPLETED = "COMPLETED";
        String RATING = "RATING";
    }

    interface InvoiceFare {
        String MINUTE = "MIN";
        String HOUR = "HOUR";
        String DISTANCE = "DISTANCE";
        String DISTANCE_MIN = "DISTANCEMIN";
        String DISTANCE_HOUR = "DISTANCEHOUR";
    }
    //TODO MARCIO HAMASAKI PICPAY MERCADO PAGO
    interface PaymentMode {
        String CASH = "CASH";
        String CARD = "CARD";
        String DEBIT_MACHINE = "DEBIT_MACHINE";
        String PIC_PAY = "PIC_PAY";
        String M_P = "M_P";
        String PIX = "PIX";
        String WALLET = "WALLET";
    }

    interface LocationActions{
        String SELECT_SOURCE ="select_source";
        String SELECT_DESTINATION ="select_destination";
        String CHANGE_DESTINATION ="change_destination";
        String SELECT_HOME ="select_home";
        String SELECT_WORK ="select_work";
    }
}
