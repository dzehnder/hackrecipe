package ch.hackathon.recipe.billscanning;

import com.google.api.gax.paging.Page;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.cloud.vision.v1.AnnotateImageRequest;
import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.BatchAnnotateImagesResponse;
import com.google.cloud.vision.v1.Feature;
import com.google.cloud.vision.v1.Image;
import com.google.cloud.vision.v1.ImageAnnotatorClient;
import com.google.common.collect.Lists;
import com.google.protobuf.ByteString;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Billscanning {

    public Map<String, Integer> ingredients = new HashMap<>();

    public Billscanning() {
        ingredients.put("Whole Wheat Bread", 2);
        ingredients.put("Cereal", 1);
        ingredients.put("Kidney Beans", 3);
        ingredients.put("Apples", 3);
        ingredients.put("Avocado", 2);
        ingredients.put("Berry Medley", 1);
        ingredients.put("Butter", 2);
        ingredients.put("Milk", 2);
        ingredients.put("Nonfat Yogurt", 2);
        ingredients.put("Olive Oil", 1);

        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        URL json = classloader.getResource("dienst-code.json");
        try {
            authExplicit(json.getPath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        URL receipt = classloader.getResource(BillInput.RECEIPT_NAME);

        // Instantiates a client
        try (ImageAnnotatorClient vision = ImageAnnotatorClient.create()) {

            // The path to the image file to annotate

            // Reads the image file into memory
            Path path = Paths.get(receipt.getPath());
            byte[] data = Files.readAllBytes(path);
            ByteString imgBytes = ByteString.copyFrom(data);

            // Builds the image annotation request
            List<AnnotateImageRequest> requests = new ArrayList<>();
            Image img = Image.newBuilder().setContent(imgBytes).build();
            Feature feat = Feature.newBuilder().setType(Feature.Type.DOCUMENT_TEXT_DETECTION).build();
            AnnotateImageRequest request = AnnotateImageRequest.newBuilder()
                    .addFeatures(feat)
                    .setImage(img)
                    .build();
            requests.add(request);

            // Performs label detection on the image file
            BatchAnnotateImagesResponse response = vision.batchAnnotateImages(requests);
            List<AnnotateImageResponse> responses = response.getResponsesList();

            String[] scannerHits = new String[0];
            for (AnnotateImageResponse res : responses) {
                if (res.hasError()) {
                    System.out.printf("Error: %s\n", res.getError().getMessage());
                    return;
                }


                scannerHits = res.getTextAnnotationsList().get(0).getDescription().split("\n");

            }

            String ingredientRegex = "(\\d) (\\w+.)+";
            Pattern regexPattern = Pattern.compile("(\\d)|\\S(.\\w+.)+");

            for (String ingredient : scannerHits) {
                if (ingredient.matches(ingredientRegex)) {
                    Matcher regexMatcher = regexPattern.matcher(ingredient);
                    if (regexMatcher.find()) {
                        regexMatcher.groupCount();
                    }
                }


            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void authExplicit(String jsonPath) throws IOException {
        // You can specify a credential file by providing a path to GoogleCredentials.
        // Otherwise credentials are read from the GOOGLE_APPLICATION_CREDENTIALS environment variable.
        GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream(jsonPath))
                .createScoped(Lists.newArrayList("https://www.googleapis.com/auth/cloud-platform"));
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();

        System.out.println("Buckets:");
        Page<Bucket> buckets = storage.list();
        for (Bucket bucket : buckets.iterateAll()) {
            System.out.println(bucket.toString());
        }
    }
}
