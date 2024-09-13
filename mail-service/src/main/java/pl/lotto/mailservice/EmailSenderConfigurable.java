package pl.lotto.mailservice;

public interface EmailSenderConfigurable {

    String getAddress();

    void setAddress(String address);
}
