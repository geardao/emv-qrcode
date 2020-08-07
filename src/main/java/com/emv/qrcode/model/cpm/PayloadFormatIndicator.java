package com.emv.qrcode.model.cpm;

import com.emv.qrcode.core.model.BERTLString;
import com.emv.qrcode.model.cpm.constants.ConsumerPresentedModeFieldCodes;

import lombok.Getter;

@Getter
public class PayloadFormatIndicator extends BERTLString {

  private static final long serialVersionUID = 780284119561670846L;

  public PayloadFormatIndicator() {
    super(ConsumerPresentedModeFieldCodes.ID_PAYLOAD_FORMAT_INDICATOR, null);
  }

  public PayloadFormatIndicator(final String value) {
    super(ConsumerPresentedModeFieldCodes.ID_PAYLOAD_FORMAT_INDICATOR, value);
  }

}
