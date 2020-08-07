package com.emv.qrcode.decoder.mpm;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.emv.qrcode.core.model.TagLengthString;
import com.emv.qrcode.decoder.Decoder;
import com.emv.qrcode.model.mpm.MerchantAccountInformationTemplate;
import com.emv.qrcode.model.mpm.MerchantAccountInformation;

public class MerchantAccountInformationTemplateDecoderTest {

  @Test
  public void testSuccessDecode() {
    final MerchantAccountInformationTemplate merchantAccountInformation = Decoder.decode("02160004hoge0104abcd", MerchantAccountInformationTemplate.class);

    assertThat(merchantAccountInformation.getValue(), not(nullValue()));

    assertThat(merchantAccountInformation.getTag(), equalTo("02"));
    assertThat(merchantAccountInformation.getLength(), equalTo(16));

    assertThat(merchantAccountInformation.getValue().getGloballyUniqueIdentifier(), not(nullValue()));
    assertThat(merchantAccountInformation.getValue().getPaymentNetworkSpecific(), hasSize(1));

    assertThat(merchantAccountInformation.getValue().getGloballyUniqueIdentifier().getTag(), equalTo("00"));
    assertThat(merchantAccountInformation.getValue().getGloballyUniqueIdentifier().getLength(), equalTo(4));
    assertThat(merchantAccountInformation.getValue().getGloballyUniqueIdentifier().getValue(), equalTo("hoge"));

    assertThat(merchantAccountInformation.getValue().getPaymentNetworkSpecific().get(0).getTag(), equalTo("01"));
    assertThat(merchantAccountInformation.getValue().getPaymentNetworkSpecific().get(0).getLength(), equalTo(4));
    assertThat(merchantAccountInformation.getValue().getPaymentNetworkSpecific().get(0).getValue(), equalTo("abcd"));

  }

  @Test
  public void testSuccessDecodeEncode() {
    final MerchantAccountInformationTemplate merchantAccountInformation = Decoder.decode("02160004hoge0104abcd", MerchantAccountInformationTemplate.class);

    assertThat(merchantAccountInformation.toString(), equalTo("02160004hoge0104abcd"));
  }

  @Test
  public void testSuccessEncode() {

    final TagLengthString globallyUniqueIdentifier = new TagLengthString();
    globallyUniqueIdentifier.setTag("00");
    globallyUniqueIdentifier.setValue("hoge");

    final TagLengthString tagLengthString = new TagLengthString();
    tagLengthString.setTag("01");
    tagLengthString.setValue("abcd");

    final MerchantAccountInformation value = new MerchantAccountInformation();
    value.setGloballyUniqueIdentifier(globallyUniqueIdentifier);
    value.addPaymentNetworkSpecific(tagLengthString);

    final MerchantAccountInformationTemplate merchantAccountInformation = new MerchantAccountInformationTemplate();
    merchantAccountInformation.setValue(value);
    merchantAccountInformation.setTag("02");

    assertThat(merchantAccountInformation.toString(), equalTo("02160004hoge0104abcd"));
  }

}