package de.jensd.addon.decoder.utils;


/**
 *
 * @author Jens Deters (jens.deters@softblade.de)
 */
public enum ContentType {

  PLAIN_TEXT("text/plain"), BASE64("text/base64"), JSON("application/json"), HEX("application/hex"), SPARKPLUG("application/sparkplug");

  private String mimeType;

  private ContentType(String mimeType){
    this.mimeType = mimeType;
  }

  public String getMimeType(){
    return mimeType;
  }


}
