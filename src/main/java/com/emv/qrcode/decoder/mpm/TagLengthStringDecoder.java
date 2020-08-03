package com.emv.qrcode.decoder.mpm;

import com.emv.qrcode.core.model.TagLengthString;
import com.emv.qrcode.decoder.Decoder;

// @formatter:off
public final class TagLengthStringDecoder extends Decoder<TagLengthString> {

  public TagLengthStringDecoder(final String source) {
    super(source);
  }

  @Override
  protected TagLengthString decode() {
    final TagLengthString result = new TagLengthString();

    forEachRemaining(value -> {
      final String tag = value.substring(0, Decoder.ID_WORD_COUNT);
      final Integer length = Integer.valueOf(value.substring(Decoder.ID_WORD_COUNT, Decoder.ID_WORD_COUNT + Decoder.VALUE_LENGTH_WORD_COUNT));
      final String string = value.substring(Decoder.ID_WORD_COUNT + Decoder.VALUE_LENGTH_WORD_COUNT, Decoder.ID_WORD_COUNT + Decoder.VALUE_LENGTH_WORD_COUNT + length);
      result.setTag(tag);
      result.setLength(length);
      result.setValue(string);
    });

    return result;
  }

}

// @formatter:on
