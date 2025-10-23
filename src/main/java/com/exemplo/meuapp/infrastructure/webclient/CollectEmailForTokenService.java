package com.exemplo.meuapp.infrastructure.webclient;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
@Service
public class CollectEmailForTokenService {
    
    public String execute(HttpServletRequest servletRequest) throws JSONException {
        String authHeader = servletRequest.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Token ausente ou mal formado");
        }
        String token = authHeader.substring(7);

        String[] chunks = token.split("\\.");
        String payload = new String(java.util.Base64.getDecoder().decode(chunks[1]));
        JSONObject jsonObject = new JSONObject(payload);
        String sub = jsonObject.getString("sub");

        return sub;
    }
}
