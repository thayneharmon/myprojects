package org.sonatype.mavenbook.weather.yahoo;

public class Weather {
  private String city;
  private String region;
  private String country;
  private String condition;
  private String temp;
  private String windChill;
  private String windDir;
  private String windSpeed;
  private String airHumidity;
  private String airVisibility;
  private String airPressure;
  private String airRising;
  private String sunRise;
  private String sunSet;
  private String[] forcastDay;

  public Weather() {}


  public String getAirVisibility() {
    return airVisibility;
  }

  public void setAirVisibility(String airVisibility) {
    this.airVisibility = airVisibility;
  }

  public String getAirPressure() {
    return airPressure;
  }

  public void setAirPressure(String airPressure) {
    this.airPressure = airPressure;
  }

  public String getAirRising() {
    return airRising;
  }

  public void setAirRising(String airRising) {
    this.airRising = airRising;
  }

  public String getSunRise() {
    return sunRise;
  }

  public void setSunRise(String sunRise) {
    this.sunRise = sunRise;
  }

  public String getSunSet() {
    return sunSet;
  }

  public void setSunSet(String sunSet) {
    this.sunSet = sunSet;
  }

  public String[] getForcastDay() {
    return forcastDay;
  }

  public void setForcastDay(String[] forcastDay) {
    this.forcastDay = forcastDay;
  }

  public String getWindDir() {
    return windDir;
  }

  public void setWindDir(String windDir) {
    this.windDir = windDir;
  }

  public String getWindSpeed() {
    return windSpeed;
  }

  public void setWindSpeed(String windSpeed) {
    this.windSpeed = windSpeed;
  }

  public String getCity() { return city; }

  public void setCity(String city) {
    this.city = city;
  }

  public String getRegion() { return region; }
  public void setRegion(String region) {
    this.region = region;
  }

  public String getCountry() { return country; }
  public void setCountry(String country) {
    this.country = country;
  }

  public String getCondition() { return condition; }
  public void setCondition(String condition) {
    this.condition = condition;
  }

  public String getTemp() { return temp; }
  public void setTemp(String temp) {
    this.temp = temp;
  }

  public String getWindChill() { return windChill; }
  public void setWindChill(String chill) {
    this.windChill = chill;
  }

  public String getAirHumidity() { return airHumidity; }
  public void setAirHumidity(String humidity) {
    this.airHumidity = humidity;
  }
}