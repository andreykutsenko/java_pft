package ru.stqa.pft.soap;

import com.buzzbuzhome.BBHLocation;
import com.buzzbuzhome.GeoIP;
import com.buzzbuzhome.GeoIPSoap;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GeoIpServiceTests {

  @Test
  public void testMyIp() {
    BBHLocation userLocation = new GeoIP().getGeoIPSoap12().getUserLocation("50.206.130.158");
    assertEquals(userLocation.getCountryCode(), "US");
  }
//
//  @Test
//  public void testInvalidIp() {
//    BBHLocation userLocation = new GeoIP().getGeoIPSoap12().getUserLocation("50.206.130.xxx");
//    assertEquals(userLocation.getCountryCode(), "US");
//  }
}
