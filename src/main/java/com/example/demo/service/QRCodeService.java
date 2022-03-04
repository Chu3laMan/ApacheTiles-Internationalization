package com.example.demo.service;

public interface QRCodeService {
	
	byte[] generateQRCode(String qrContent, int width, int height);
	public String getTOTPCode(String secretKey);

}
