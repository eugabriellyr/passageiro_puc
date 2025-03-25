package com.minasmob.passageiro.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("estudante")
    @Expose
    private int estudante;

    @SerializedName("p_aprova")
    @Expose
    private int p_aprova;
    @SerializedName("first_name")
    @Expose
    private String firstName;


    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("cpf")
    @Expose
    private String cpf;

    @SerializedName("fav")
    @Expose
    private String fav;

    @SerializedName("link_whts")
    @Expose
    public String link_whts;

    @SerializedName("aviso")
    @Expose
    public String aviso;

    @SerializedName("liga_aviso")
    @Expose
    public int liga_aviso;

    @SerializedName("liga_whts")
    @Expose
    public String liga_whts;

    @SerializedName("link_banner")
    @Expose
    public String link_banner;

    @SerializedName("liga_banner")
    @Expose
    private int liga_banner;

    @SerializedName("banner")
    @Expose
    private String banner;

    @SerializedName("liga_manutencao")
    @Expose
    private int liga_manutencao;

    @SerializedName("txt_manutencao")
    @Expose
    private String txt_manutencao;

    @SerializedName("payment_mode")
    @Expose
    private String paymentMode;
    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("idsosp")
    @Expose
    private String idsosp;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("country_code")
    @Expose
    private String countryCode;


    @SerializedName("picture")
    @Expose
    private String picture;

    @SerializedName("txt_educacao")
    @Expose
    private String txt_educacao;

    @SerializedName("img_educador")
    @Expose
    private String img_educador;

    @SerializedName("img_frente")
    @Expose
    private String img_frente;

    @SerializedName("img_verso")
    @Expose
    private String img_verso;
    @SerializedName("device_token")
    @Expose
    private String deviceToken;
    @SerializedName("device_id")
    @Expose
    private String deviceId;
    @SerializedName("device_type")
    @Expose
    private String deviceType;
    @SerializedName("login_by")
    @Expose
    private String loginBy;
    @SerializedName("social_unique_id")
    @Expose
    private String socialUniqueId;
    @SerializedName("latitude")
    @Expose
    private double latitude;
    @SerializedName("longitude")
    @Expose
    private double longitude;
    @SerializedName("stripe_cust_id")
    @Expose
    private String stripeCustId;
    @SerializedName("wallet_balance")
    @Expose
    private double walletBalance;
    @SerializedName("rating")
    @Expose
    private String rating;
    @SerializedName("otp")
    @Expose
    private double otp;
    @SerializedName("language")
    @Expose
    private String language;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("sos")
    @Expose
    private String sos;
    @SerializedName("app_contact")
    @Expose
    private String appContact;
    @SerializedName("measurement")
    @Expose
    private String measurement;
    @SerializedName("stripe_secret_key")
    @Expose
    private String stripeSecretKey;
    @SerializedName("stripe_publishable_key")
    @Expose
    private String stripePublishableKey;
    @SerializedName("referral_count")
    @Expose
    private String referral_count;
    @SerializedName("referral_unique_id")
    @Expose
    private String referral_unique_id;

    @SerializedName("total_corridas")
    @Expose
    private int total_corridas;

    @SerializedName("total_corridas_premio")
    @Expose
    public int total_corridas_premio;

    @SerializedName("cancp")
    @Expose
    public int cancp;

    @SerializedName("cpd")
    @Expose
    public int cpd;

    @SerializedName("mcp")
    @Expose
    public int mcp;

    @SerializedName("cancm")
    @Expose
    public int cancm;



    @SerializedName("txtaviso")
    @Expose
    private String txtaviso;

    @SerializedName("total_amigos")
    @Expose
    private String total_amigos;

    @SerializedName("taxa_liga")
    @Expose
    private int taxa_liga;

    @SerializedName("total_metas")
    @Expose
    private int total_metas;

    @SerializedName("liga_doc")
    @Expose
    private int liga_doc;

    @SerializedName("txt_documento")
    @Expose
    private String txt_documento;

    @SerializedName("titulo_indicado")
    @Expose
    private String titulo_indicado;

    @SerializedName("txt_indicado")
    @Expose
    private String txt_indicado;

    @SerializedName("total_ganhos")
    @Expose
    private float total_ganhos ;

    @SerializedName("total_pago")
    @Expose
    private float total_pago ;

    @SerializedName("meta")
    @Expose
    private String meta;

    @SerializedName("primeirameta")
    @Expose
    private String primeirameta;

    @SerializedName("segundameta")
    @Expose
    private String segundameta;

    @SerializedName("terceirameta")
    @Expose
    private String terceirameta;

    @SerializedName("primeirametacodigo")
    @Expose
    private String primeirametacodigo;

    @SerializedName("segundametacodigo")
    @Expose
    private String segundametacodigo;

    @SerializedName("terceirametacodigo")
    @Expose
    private String terceirametacodigo;

    @SerializedName("referral_total_amount")
    @Expose
    private String referral_total_amount;

    @SerializedName("referral_total_count")
    @Expose
    private String referral_total_count;

    @SerializedName("referral_total_text")
    @Expose
    private String referral_total_text;

    @SerializedName("referral_amount")
    @Expose
    private String referral_amount;

    @SerializedName("ride_otp")
    @Expose
    private String ride_otp;

    @SerializedName("qrcode_url")
    @Expose
    private String qrcode_url;

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getQrcode_url() {
        return qrcode_url;
    }

    public void setQrcode_url(String qrcode_url) {
        this.qrcode_url = qrcode_url;
    }

    public String getTotal_amigos() {
        return total_amigos;
    }

    public void setTotal_amigos(String total_amigos) {
        this.total_amigos = total_amigos;
    }



    public void setTxtaviso(String txtaviso) {
        this.txtaviso = txtaviso;
    }

    public String getTxtaviso() {
        return txtaviso;
    }
    public int getTotal_corridas() {
        return total_corridas;
    }
    public void setTotal_corridas(int total_corridas) {
        this.total_corridas = total_corridas;
    }

    public int getTotal_corridas_premio() {
        return total_corridas_premio;
    }
    public void setTotal_corridas_premio(int total_corridas_premio) {
        this.total_corridas_premio = total_corridas_premio;
    }

    public int getCancp() {
        return cancp;
    }
    public void setCancp(int cancp) {
        this.cancp = cancp;
    }

    public int getMcp() {
        return mcp;
    }
    public void setMcp(int mcp) {
        this.mcp = mcp;
    }

    public int getCpd() {
        return cpd;
    }
    public void setCpd(int cpd) {
        this.cpd = cpd;
    }

    public int getCancm() {
        return cancm;
    }
    public void setCancm(int cancm) {
        this.cancm = cancm;
    }


    public int getTotal_metas() {
        return total_metas;
    }
    public void setgetTotal_metas(int total_metas) {
        this.total_metas = total_metas;
    }

    public int getLigaDoc() {
        return liga_doc;
    }
    public void setgetLigaDoc(String txt_documento) {
        this.txt_documento = txt_documento;
    }

    public String getTxtDoc() {
        return txt_documento;
    }
    public void setTxtDoc(int liga_doc) {
        this.liga_doc = liga_doc;
    }

    public void setTituloIndica(String titulo_indicado) {
        this.titulo_indicado = titulo_indicado;
    }

    public String getTituloIndica() {
        return titulo_indicado;
    }

    public void setTxt_indica(String txt_indicado) {
        this.txt_indicado = txt_indicado;
    }

    public String getTxt_indica() {
        return txt_indicado;
    }




    public float getTotal_ganhos() {
        return total_ganhos;
    }

    public void setTotal_ganhos(float total_ganhos) {
        this.total_ganhos = total_ganhos;
    }

    public float getTotal_pago() {
        return total_pago;
    }

    public void setTotal_pago(float total_pago) {
        this.total_pago = total_pago;
    }

    public String getMetas() {
        return meta ;
    }

    public void setPmeta(String primeirameta) {
        this.primeirameta = primeirameta;
    }

    public String getPmeta() {
        return primeirameta ;
    }

    public void setSmeta(String segundameta) {
        this.segundameta = segundameta;
    }

    public String getSmeta() {
        return segundameta ;
    }

    public void setTmeta(String terceirameta) {
        this.terceirameta = terceirameta;
    }

    public String getTmeta() {
        return terceirameta ;
    }

    public void setPmetac(String primeirametacodigo) {
        this.primeirametacodigo = primeirametacodigo;
    }

    public String getPmetac() {
        return primeirametacodigo ;
    }

    public void setSmetac(String segundametacodigo) {
        this.segundametacodigo = segundametacodigo;
    }

    public String getSmetac() {
        return segundametacodigo ;
    }
    public void setTmetac(String terceirametacodigo) {
        this.terceirametacodigo = terceirametacodigo;
    }

    public String getTmetac() {
        return terceirametacodigo ;
    }

    public void setMetas(String meta) {
        this.meta = meta;
    }

    public String getReferral_total_amount() {
        return referral_total_amount;
    }

    public void setReferral_total_amount(String referral_total_amount) {
        this.referral_total_amount = referral_total_amount;
    }

    public String getReferral_total_count() {
        return referral_total_count;
    }

    public void setReferral_total_count(String referral_total_count) {
        this.referral_total_count = referral_total_count;
    }

    public String getReferral_total_text() {
        return referral_total_text;
    }

    public void setReferral_total_text(String referral_total_text) {
        this.referral_total_text = referral_total_text;
    }

    public String getReferral_amount() {
        return referral_amount;
    }

    public void setReferral_amount(String referral_amount) {
        this.referral_amount = referral_amount;
    }

    public String getReferral_count() {
        return referral_count;
    }

    public void setReferral_count(String referral_count) {
        this.referral_count = referral_count;
    }

    public String getReferral_unique_id() {
        return referral_unique_id;
    }

    public void setReferral_unique_id(String referral_unique_id) {
        this.referral_unique_id = referral_unique_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEducAtivo() {
        return estudante;
    }

    public void setEducAtivo(int estudante) {
        this.estudante = estudante;
    }


    public int getAprova() {
        return p_aprova;
    }

    public void setAprova(int p_aprova) {
        this.p_aprova = p_aprova;
    }


    public int getTaxa_liga() {

        return taxa_liga;
    }

    public void setTaxa_liga(int taxa_liga) {
        this.taxa_liga = taxa_liga;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getFav() {
        return fav;
    }

    public void setFav(String fav) {
        this.fav = fav;
    }

    public String getLinkwhts() {
        return link_whts ;
    }



    public void setLinkwts(String link_whts) {
        this.link_whts = link_whts;
    }

    public String getAviso() {
        return aviso ;
    }



    public void setAviso(String aviso) {
        this.aviso = aviso;
    }



    public int getLigaAviso() {
        return liga_aviso ;
    }

    public void setLigaAviso(int liga_aviso) {
        this.liga_aviso = liga_aviso;
    }



    public String getLingawhts() {
        return liga_whts ;
    }

    public void setLigawts(String link_whts) {
        this.liga_whts = liga_whts;
    }

    public String getLinkbanner() {
        return link_banner ;
    }

    public void setLinkbanner(String link_banner) {
        this.link_banner = link_banner;
    }

    public int getLigaBanner() {
        return liga_banner;
    }

    public void setLigaBanner(int liga_banner) {
        this.liga_banner = liga_banner;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public int getManutencao(){
        return liga_manutencao;
    }

    public void  setManutencao(int liga_manutencao){this.liga_manutencao = liga_manutencao;}

    public String getTxtmanutencao(){
        return txt_manutencao;
    }

    public void  setTxtmanutencao(String txt_manutencao){this.txt_manutencao = txt_manutencao;}


    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSos1() {
        return sos;
    }

    public void setSos1(String sos) {
        this.sos = sos;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPicture() {
        return picture;
    }
    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getTxt_educacao() {
        return txt_educacao;
    }
    public void setTxt_educacao(String txt_educacao) {
        this.txt_educacao = txt_educacao;
    }


    public void setImgEducador(String picture) {
        this.img_educador = img_educador;
    }

    public String getImgEducador() {
        return img_educador;
    }

    public void setImgFrente(String img_frente) {
        this.img_frente = img_frente;
    }

    public String getImgFrente() {
        return img_frente;
    }

    public void setImgVerso(String img_verso) {
        this.img_verso = img_verso;
    }

    public String getImgVerso() {
        return img_verso;
    }



    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getLoginBy() {
        return loginBy;
    }

    public void setLoginBy(String loginBy) {
        this.loginBy = loginBy;
    }

    public String getSocialUniqueId() {
        return socialUniqueId;
    }

    public void setSocialUniqueId(String socialUniqueId) {
        this.socialUniqueId = socialUniqueId;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getStripeCustId() {
        return stripeCustId;
    }

    public void setStripeCustId(String stripeCustId) {
        this.stripeCustId = stripeCustId;
    }

    public double getWalletBalance() {
        return walletBalance;
    }

    public void setWalletBalance(double walletBalance) {
        this.walletBalance = walletBalance;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public double getOtp() {
        return otp;
    }

    public void setOtp(double otp) {
        this.otp = otp;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getSos() {
        return sos;
    }

    public void setSos(String sos) {
        this.sos = sos;
    }

    public String getAppContact() {
        return appContact;
    }

    public void setAppContact(String appContact) {
        this.appContact = appContact;
    }

    public String getMeasurement() {
        return measurement;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }

    public String getStripeSecretKey() {
        return stripeSecretKey;
    }

    public void setStripeSecretKey(String stripeSecretKey) {
        this.stripeSecretKey = stripeSecretKey;
    }

    public String getStripePublishableKey() {
        return stripePublishableKey;
    }

    public void setStripePublishableKey(String stripePublishableKey) {
        this.stripePublishableKey = stripePublishableKey;
    }

    public String getRide_otp() {
        return ride_otp;
    }

    public void setRide_otp(String ride_otp) {
        this.ride_otp = ride_otp;
    }
}
