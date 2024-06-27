package com.Nolercoster.user.kakao;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


import lombok.extern.slf4j.Slf4j;

@Slf4j
public class KakaoApi {

//	@Value("${kakao.ApiKey}")
//	private String kakaoApiKey;
//	
//	@Value("${kakao.RedirectUri}")
//	private String kakaoRedirectUri;
//	
//	@Value("${kakao.kakaoLogoutRedirectUri}")
//	private String kakaoLogoutRedirectUri;
//
//	public String getKakaoApiKey() {
//		return kakaoApiKey;
//	}
//
//	public void setKakaoApiKey(String kakaoApiKey) {
//		this.kakaoApiKey = kakaoApiKey;
//	}
//
//	public String getKakaoRedirectUri() {
//		return kakaoRedirectUri;
//	}
//
//	public void setKakaoRedirectUri(String kakaoRedirectUri) {
//		this.kakaoRedirectUri = kakaoRedirectUri;
//	}
//	
//	public String getKakaoLogoutRedirectUri() {
//		return kakaoLogoutRedirectUri;
//	}
//
//	public void setKakaoLogoutRedirectUri(String kakaoLogoutRedirectUri) {
//		this.kakaoLogoutRedirectUri = kakaoLogoutRedirectUri;
//	}
	
	@Value("${kakao.ApiKey}")
	private String kakaoApiKey;
	
	@Value("${kakao.RedirectUri}")
	private String kakaoRedirectUri;
	
	@Value("${kakao.kakaoLogoutRedirectUri}")
	private String kakaoLogoutRedirectUri;

	public String getAccessToken(String code) {
		String accessToken = "";
		String refreshToken = "";
		String reqUrl = "https://kauth.kakao.com/oauth/token";

		try {
			URL url = new URL(reqUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			// 필수 헤더 세팅
			conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
			conn.setDoOutput(true); // OutputStream으로 POST 데이터를 넘겨주겠다는 옵션.

			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			StringBuilder sb = new StringBuilder();

			// 필수 쿼리 파라미터 세팅
			sb.append("grant_type=authorization_code");
			sb.append("&client_id=").append(kakaoApiKey);
			sb.append("&redirect_uri=").append(kakaoRedirectUri);
			sb.append("&code=").append(code);

			bw.write(sb.toString());
			bw.flush();

			int responseCode = conn.getResponseCode();
			log.info("[KakaoApi.getAccessToken] responseCode = {}", responseCode);

			BufferedReader br;
			if (responseCode >= 200 && responseCode <= 300) {
				br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			} else {
				br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
			}

			String line = "";
			StringBuilder responseSb = new StringBuilder();
			while ((line = br.readLine()) != null) {
				responseSb.append(line);
			}
			String result = responseSb.toString();
			log.info("responseBody = {}", result);

//	        JsonParser parser = new JsonParser();
//	        JsonElement element = parser.parse(result);
//	        accessToken = element.getAsJsonObject().get("access_token").getAsString();
//	        refreshToken = element.getAsJsonObject().get("refresh_token").getAsString();

			// jackson objectmapper 객체 생성
			ObjectMapper objectMapper = new ObjectMapper();
			// JSON String -> Map
			Map<String, Object> jsonMap = objectMapper.readValue(result, new TypeReference<Map<String, Object>>() {
			});

			accessToken = jsonMap.get("access_token").toString();
			refreshToken = jsonMap.get("refresh_token").toString();

			br.close();
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return accessToken;
	}

	public HashMap<String, Object> getUserInfo(String accessToken) {
		HashMap<String, Object> userInfo = new HashMap<>();
		String reqUrl = "https://kapi.kakao.com/v2/user/me";
		try {
			URL url = new URL(reqUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Authorization", "Bearer " + accessToken);
			conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

			int responseCode = conn.getResponseCode();
			log.info("[KakaoApi.getUserInfo] responseCode : {}", responseCode);

			BufferedReader br;
			if (responseCode >= 200 && responseCode <= 300) {
				br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			} else {
				br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
			}

			String line = "";
			StringBuilder responseSb = new StringBuilder();
			while ((line = br.readLine()) != null) {
				responseSb.append(line);
			}
			String result = responseSb.toString();
			log.info("responseBody = {}", result);

//			JsonParser parser = new JsonParser();
//			JsonElement element = parser.parse(result);
//
//			JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
//			JsonObject kakaoAccount = element.getAsJsonObject().get("kakao_account").getAsJsonObject();
//
//			String nickname = properties.getAsJsonObject().get("nickname").getAsString();
//			String email = kakaoAccount.getAsJsonObject().get("email").getAsString();

//			// jackson objectmapper 객체 생성
			ObjectMapper objectMapper = new ObjectMapper();
			// JSON String -> Map
			Map<String, Object> jsonMap = objectMapper.readValue(result, new TypeReference<Map<String, Object>>() {
			});

			Map<String, Object> properties = (Map<String, Object>) jsonMap.get("properties");
			Map<String, Object> kakao_account = (Map<String, Object>) jsonMap.get("kakao_account");
			String userId = jsonMap.get("id").toString();
			String nickname = properties.get("nickname").toString();
			String profileImage = properties.get("profile_image").toString();

			//properties={nickname=김채연, profile_image=http://k.kakaocdn.net/dn/bFEkTd/btsGAz2Rp37/xsqhJt8vS4RrtIw6pcxRJK/img_640x640.jpg, thumbnail_image=http://k.kakaocdn.net/dn/bFEkTd/btsGAz2Rp37/xsqhJt8vS4RrtIw6pcxRJK/img_110x110.jpg}
			//kakao_account={profile_nickname_needs_agreement=false, profile_image_needs_agreement=false, profile={nickname=김채연, thumbnail_image_url=http://k.kakaocdn.net/dn/bFEkTd/btsGAz2Rp37/xsqhJt8vS4RrtIw6pcxRJK/img_110x110.jpg, profile_image_url=http://k.kakaocdn.net/dn/bFEkTd/btsGAz2Rp37/xsqhJt8vS4RrtIw6pcxRJK/img_640x640.jpg, is_default_image=false, is_default_nickname=false}}
			userInfo.put("userToken", userId);
			userInfo.put("nickname", nickname);
			userInfo.put("profileImage", profileImage);

			br.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return userInfo;
	}

	public void kakaoLogout(String accessToken) {
		String reqUrl = "https://kapi.kakao.com/v1/user/logout";

		try {
			URL url = new URL(reqUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Authorization", "Bearer " + accessToken);

			int responseCode = conn.getResponseCode();
			log.info("[KakaoApi.kakaoLogout] responseCode : {}", responseCode);

			BufferedReader br;
			if (responseCode >= 200 && responseCode <= 300) {
				br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			} else {
				br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
			}

			String line = "";
			StringBuilder responseSb = new StringBuilder();
			while ((line = br.readLine()) != null) {
				responseSb.append(line);
			}
			String result = responseSb.toString();
			log.info("kakao logout - responseBody = {}", result);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
	
}
