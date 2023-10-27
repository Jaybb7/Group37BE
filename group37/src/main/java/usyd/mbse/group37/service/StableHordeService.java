package usyd.mbse.group37.service;

import lombok.extern.slf4j.Slf4j;

import org.apache.tomcat.util.json.JSONParser;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParser;
import com.google.gson.JsonObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;


@Slf4j
@Service
public class StableHordeService {

    public static String requestImage(String prompt) {
        String url = "https://aihorde.net/api/v2/generate/async";
        String base64Image = prompt;

        try {
            URL obj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("apikey", "0000000000");
            connection.setRequestProperty("Client-Agent", "unknown:0:unknown");
            connection.setRequestProperty("Content-Type", "application/json");
            
            String body = "{\"prompt\": \"Bright Cartoon portrait, trending on artstation, happy, masterpiece, bright, detailed portrait, symmetric, disney, kids cartoon, cartoon network, lego, emoji, digital painting###disfigured, ugly, bad anatomy, cubism, artifacts, jpeg artifacts, jagged, dark, photo\", \"params\": { \"sampler_name\": \"k_euler_a\", \"cfg_scale\": 7.5, \"denoising_strength\": 0.75, \"height\": 512, \"width\": 512, \"karras\": false, \"clip_skip\": 1, \"image_is_control\": false, \"return_control_map\": false, \"steps\": 25, \"n\": 1 }, \"nsfw\": false, \"trusted_workers\": false, \"slow_workers\": true, \"censor_nsfw\": false, \"worker_blacklist\": false, \"models\": [\"stable_diffusion\"], \"source_image\": \"" 
            + base64Image
            + "\", \"source_processing\": \"img2img\", \"r2\": true, \"dry_run\": false}";
            connection.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write(body);
            writer.flush();
            writer.close();
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = br.readLine();
            line = line.substring(line.indexOf(":") + 2, line.indexOf(",") - 1);
            br.close();
            return line;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String checkImageStatus(String id) {
        String url = "https://stablehorde.net/api/v2/generate/check/" + id;

        try {
            URL obj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Client-Agent", "unknown:0:unknown");
            connection.setRequestProperty("accept", "application/json");
            
            InputStream inputStream = (InputStream) connection.getContent();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String line = br.readLine();
            line = line.substring(line.indexOf(":")+1, line.indexOf(","));
            return line.trim();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String getImage(String id) {
        String url = "https://stablehorde.net/api/v2/generate/status/" + id;

        try {
            URL obj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Client-Agent", "unknown:0:unknown");
            connection.setRequestProperty("accept", "application/json");
            
            InputStream inputStream = (InputStream) connection.getContent();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String line = br.readLine();
            System.out.println(line);
            line = line.substring(line.indexOf("\"https")+1, line.indexOf("seed")-4);
            return line;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String generate(String base) {
        
        String id = requestImage(base);
        int seconds = 0;
        System.err.println("starting... " + id);
        String status = checkImageStatus(id);
        while (status.equals("0")) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            status = checkImageStatus(id);
        }
        return getImage(id);
    }

}
