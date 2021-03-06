package com.cahue.resources;

import com.cahue.auth.UserService;
import com.cahue.model.User;
import com.cahue.model.transfer.RegistrationResult;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalMemcacheServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.google.inject.Inject;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.cache.AsyncCacheFilter;
import org.junit.After;
import org.junit.Before;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Francesco on 08/02/2015.
 */
public class TestHelper {

    public static final String EMAIL_ADDRESS = "582791978228-kl51c8scvc1ombariffo8bsnf25qf7st@developer.gserviceaccount.com";
    private static final List<String> SCOPES = Arrays.asList(
            "email",
            "profile");
    private final LocalServiceTestHelper helper = new LocalServiceTestHelper(
            new LocalDatastoreServiceTestConfig(),
            new LocalMemcacheServiceTestConfig());
    JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    HttpTransport httpTransport = new NetHttpTransport();
    Closeable session;
    @Inject
    UsersResource usersResource;

    UserService userService;

    @Inject
    public TestHelper(UserService userService) {
        this.userService = userService;
    }

    @Before
    public void setUp() {
        session = ObjectifyService.begin();
        helper.setUp();
    }

    @After
    public void tearDown() throws IOException {
        AsyncCacheFilter.complete();
        helper.tearDown();
        session.close();
        session = null;
    }

    public RegistrationResult registerUser() {

        RegistrationResult result = usersResource.createGoogleUser(getGoogleAuthToken(), "Test device");

        User user = result.getUser();
        userService.setCurrentUser(user);

        assertEquals(user.getGoogleUser().getEmail(), TestHelper.EMAIL_ADDRESS);
        return result;
    }

    protected String getGoogleAuthToken() {
        try {

            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource("Cahue-4d17cda7873b.p12").getFile());
            GoogleCredential credential = new GoogleCredential.Builder()
                    .setTransport(httpTransport)
                    .setJsonFactory(JSON_FACTORY)
                    .setServiceAccountId(EMAIL_ADDRESS)
                    .setServiceAccountPrivateKeyFromP12File(file)
                    .setServiceAccountScopes(SCOPES)
                    .build();

            credential.refreshToken();

            return credential.getAccessToken();

        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
